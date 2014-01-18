/**
 *
 * @author Valerio
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

        String host = (args.length < 1) ? null : args[0];

        //FileReader f = new FileReader("./makefiles/premier/Makefile");
        /*FileReader f = new FileReader("./makefile_test");
        BufferedReader b = new BufferedReader(f);*/
        
        // Parse Makefile
        MakefileStruct m = new MakefileStruct("./makefile_test");
        m.print(); //debug

        // Retrieves the Registry and the remote function
        Registry registry = LocateRegistry.getRegistry(host);
        Task stub = null;
        try {
            stub = (Task) registry.lookup("Task");
        } catch (NotBoundException e) {
            System.err.println
            ("ERROR: not bound. Are you running the Registry and the Slave already?\n");
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
