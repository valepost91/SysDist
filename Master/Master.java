/**
 *
 * @author Valerio
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.NotBoundException;


public class Master {

    private Master() {
    }

    public void sendRequest(String s) {

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

            // Retrieves the Registry and the slaves
            Registry registry = LocateRegistry.getRegistry(host);
            Task stubs[] = new Task[slavesCount];
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
            
            if (allOk) {
                System.out.println("All " + slavesCount + " slaves bounded successfully.");
            
                // Parse Makefile
                MakefileStruct m = new MakefileStruct("./makefile_test");
                //m.print(); //debug
            }
            else
                System.out.println("Couldn't bind all Slaves, exiting now...");
            
            
        }

        /*
        while (true) {
            // Read line by line and prints it
            String s;
            if ((s = b.readLine()) == null)
                break;
            System.out.println("[LINE] " + s);

            // Call remote function which will process the line
            try {                
                String response = stub.doTask(s);
                System.out.println("[RESPONSE] " + response);
            
            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        }
        */

    }
}
