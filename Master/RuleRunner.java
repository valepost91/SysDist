
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class RuleRunner extends Thread {
   MakefileStruct m;
   MachinesList machs;
   Rule r;

   RuleRunner(Rule myRule, MachinesList machs, MakefileStruct m) {
      this.r = myRule;
      this.m = m;      
      this.machs = machs;
   }

   @Override
   public void run() {
        System.out.println("Starting RuleRunner thread for rule : " + r.getName());

        // Check for the dependencies
        ///////
        ///////

        ArrayList<Rule> deps = r.getDependencies();
        if (deps.size()>0) {
            ArrayList<RuleRunner> depRunners = new ArrayList<>();

            // Launch a new thread for each dependency not satisfied AND not taken
            //   yet (a synchronized function will make sure there won't be two threads
            //   handling the same dependency at the same time.
            for (int i = 0; i < deps.size(); i++) {
                // Check if dependency rule is not done neither was taken already
                if (!deps.get(i).isDone && deps.get(i).takeIt()) {
                    RuleRunner tmp =new RuleRunner(deps.get(i), machs, m); 
                    depRunners.add( tmp );
                    tmp.start();            
                }
            }

            // Wait until all threads are finished (dependencies are executed)
            for (RuleRunner depRunner : depRunners) {
                try {
                    depRunner.join();
                }catch (InterruptedException ex) {
                    Logger.getLogger(RuleRunner.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 

        // All dependencies satisfied, let's execute the rule
        ///////
        ///////
        
        ArrayList<String> commands = r.getCommands(); 
        if (!commands.isEmpty()) {
            
            // Ask for a free machine. Keeps trying until finds one.
            //System.out.println("waiting for a free machine...");
            int aMach = -1;
            while (aMach==-1)
                aMach = machs.getFreeMachine();
            Machine thisMach = machs.machs[aMach];
            

            // If any of the dependencies were files, send the files to the slave.
            //System.out.println("sending files...");
            for (Rule dep : deps) {
                try {
                    thisMach.sendFile(dep.getName());
                } catch (RemoteException ex) {
                    Logger.getLogger(RuleRunner.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            
            // Execute the rule's commands
            for (String c : commands){
               try {
                   // Send command
                   System.out.println("Sending to the slave " + aMach + " the command " + c);
                   String response = thisMach.doTask(c);

                   // Job is done, set that machines as "free"
                   machs.setMachineFree(aMach);

                   System.out.println(response);

               } catch (RemoteException ex) {
                   Logger.getLogger(RuleRunner.class.getName()).log(Level.SEVERE, null, ex);
               }
            }

            // Retrieve the file that was the product of the slave's computation. If
            //   the file doesn't exist then ça va, we continue as normal.
            try {
               thisMach.receiveFile(this.r.getName());
            } catch (RemoteException ex) {
               Logger.getLogger(RuleRunner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Everything is done! Set this rule as DONE
        ///////
        ///////

        r.isDone = true;
       
   }

   public static void main(String[] args) {
      
   }
}