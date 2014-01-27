/**
 *
 * @author Valerio
 */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Master {

    
    static private boolean debug = true;
    
    public Master() {
    }
    
    private static byte[] convertFileToBytes(String path) {
        File file = new File(path);

        byte[] b = new byte[(int) file.length()];
        try {
              FileInputStream fileInputStream = new FileInputStream(file);
              fileInputStream.read(b);
              for (int i = 0; i < b.length; i++) {
                  System.out.print((char)b[i]);
              }
         } catch (FileNotFoundException e) {
                     System.out.println("File Not Found.");
                     e.printStackTrace();
         }
         catch (IOException e1) {
                  System.out.println("Error Reading The File.");
                   e1.printStackTrace();
         }
        
        return b;
    }

    
    private static Task[] retrieveRemoteSlaves(String host, int slavesCount) throws RemoteException {
        Task stubs[] = new Task[slavesCount];
        
        Registry registry = LocateRegistry.getRegistry(host);
        
        boolean allOk = true;
        for (int i = 0; i < slavesCount; i++) {
            try {
                stubs[i] = (Task) registry.lookup("slave" + i);
                
            } catch (NotBoundException e) {
                System.err.println
                ("ERROR: Slave " + i + " not bound. Are you running the Registry and this Slave?\n");
                allOk = false;
            }
        }
        
        if (!allOk)
            return null;
        else
            return stubs;
    }
    
    private static void runDistributedMakefile(MakefileStruct m, Task stubs[]) throws RemoteException {
        
    /*    Map<String,Rule> rules = m.getRules();
        
        // Executes rules from the end of the Makefile to the beggining, so dependencies 
        //   should be automatically satisfied.
        for (int i = rules.size()-1; i>0; i--) {
            ArrayList<Rule> dep = rules.get(i).getDependencies();
            
            // Send file from dependency, if there is one
            for(Rule d : dep){
                System.out.println("Sending file " + d.getName());
                stubs[0].receiveFile(convertFileToBytes(d.getName()), d.getName());
            }
            
            // Sends this rule's commands to the slave            
            ArrayList<String> commands = m.getRuleCommands(i);
            for(String c : commands){
                System.out.println("Sending to the slave the command " + c);
                String response = stubs[0].doTask(c);
                System.out.println(response);
            }
        }*/
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        if (args.length < 1 || args.length > 2) {
            System.out.println("ERROR: expected at least 1 argument, found "
                    + args.length
                    + ". Usage: java Master <slaves_count> (<registry_host>)");
        }
        else {
            // Process command line arguments
            int slavesCount = Integer.parseInt(args[0]);
            String host = (args.length == 2) ? args[1] : null;

            Task stubs[] = retrieveRemoteSlaves(host, slavesCount);
            //stubs.toString();
            if (stubs!=null) {
                System.out.println("All " + slavesCount + " slaves bounded successfully.");
                
                MakefileStruct m = new MakefileStruct("./makefile_test");
                if(debug)
                    m.print(); //debug
                
                runDistributedMakefile(m,stubs);
            }
            else
                System.out.println("Couldn't bind all Slaves, exiting now...");
            
            
        }

    }
}
