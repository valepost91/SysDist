/**
 *
 * @author Valerio
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Compiler.command;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Slave implements Task {

    public Slave() {
    }

    // Remote function which is called to execute remotely a command
    public String doTask(String command) {
        
        StringBuffer output = new StringBuffer();
        
        Process p;
        try {
            p = Runtime.getRuntime().exec("cmd.exe /c" + command);
            p.waitFor();
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            System.err.println("Got exception " + e.toString());
            System.err.println
            ("ERROR: failed to execute the following command received: " + command);
            //e.printStackTrace();
        }

        return output.toString();
    }

    public static void main(String args[]) {

        try {
            Slave obj = new Slave();
            Task stub = (Task) UnicastRemoteObject.exportObject(obj, 0);

            // Retrieves the remote Registry
            Registry registry = LocateRegistry.getRegistry();
            // Binds the Slave even if there was already another one registered with the same
            //   same, so we don't have to reestar the Registry each time.
            registry.rebind("Task", stub);

            System.err.println("Slave ready, sir.");
        } catch (Exception e) {
            System.err.println("Slave exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
