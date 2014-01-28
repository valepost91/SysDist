
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
       
       // Sleep for 1000 ms
       /*try {
           Thread.currentThread().sleep(1000);
       } catch (InterruptedException ex) {
           Logger.getLogger(RuleRunner.class.getName()).log(Level.SEVERE, null, ex);
       }*/
       
       ArrayList<Rule> deps = r.getDependencies();
       
       // If it's not a leaf, check for the dependencies
       if (deps.size()>0) {
           ArrayList<RuleRunner> depRunners = new ArrayList<>();
           
           // Launch a new thread for each dependency not satisfied AND not taken
           //   yet (a synchronized function will make sure there won't be two threads
           //   taking the same dependency at the same time.
           for (int i = 0; i < deps.size(); i++) {
               if (!deps.get(i).isDone && deps.get(i).takeIt()) {
                   depRunners.add( new RuleRunner(deps.get(i), machs, m) );
                   depRunners.get(i).start();                  
               }
           }
           
           // Sync everyone
           for (RuleRunner depRunner : depRunners) {
               try {
                   depRunner.join();
               }catch (InterruptedException ex) {
                   Logger.getLogger(RuleRunner.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       } 
       
        // Sends this rule's commands to the slave.   
        ArrayList<String> commands = r.getCommands();
        
        for(String c : commands){
            String response = null;
            try {
                // Ask for a free machine. Keeps trying until finds one.
                int aMach = -1;
                while (aMach==-1)
                    aMach = machs.getFreeMachine();
                
                // Send command
                System.out.println("Sending to the slave " + aMach + " the command " + c);
                Machine aux = machs.machs[aMach];
                response = aux.doTask(c);
                
                // Job is done, set that machines as "free"
                machs.setMachineFree(aMach);
            } catch (RemoteException ex) {
                Logger.getLogger(RuleRunner.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(response);
        }
        
        // Set this rule as DONE
        r.isDone = true;
       
   }

   public static void main(String[] args) {
      
   }
}