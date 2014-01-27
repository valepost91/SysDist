
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class RuleRunner extends Thread {
   MakefileStruct m;
   MachinesList machs;
   int ruleId;

   RuleRunner(int ruleId, MachinesList machs, MakefileStruct m) {
      this.ruleId = ruleId;
      this.m = m;      
      this.machs = machs;
   }

   @Override
   public void run() {
       Rule r = m.getRules().get(ruleId);
       
       System.out.println("Starting RuleRunner thread for rule : " + r.name);
       
       // Sleep for 1000 ms
       try {
           Thread.currentThread().sleep(1000);
       } catch (InterruptedException ex) {
           Logger.getLogger(RuleRunner.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       int randomIndex = ruleId+1;
       
       ArrayList<String> deps = r.getDependencies();
       
       // If it's not a leaf, check for the dependencies
       if (deps.size()>0) {
           RuleRunner depRunners[] = new RuleRunner[deps.size()];
           
           // Launch a new thread for each dependency
           for (int i = 0; i < deps.size(); i++) {
               depRunners[i] = new RuleRunner(randomIndex++, machs, m);
               depRunners[i].start();               
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
        ArrayList<String> commands = m.getRuleCommands(ruleId);
        for(String c : commands){
            String response = null;
            try {
                // Ask for a free machine. Keeps trying until finds one.
                int aMach = -1;
                while (aMach==-1)
                    aMach = machs.getFreeMachine();
                
                // Send command
                System.out.println("Sending to the slave the command " + c);
                Machine aux = machs.machs[aMach];
                response = aux.doTask(c);
                
                // Job is done, set that machines as "free"
                machs.setMachineFree(aMach);
            } catch (RemoteException ex) {
                Logger.getLogger(RuleRunner.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(response);
        }
       
   }

   public static void main(String[] args) {
      
   }
}