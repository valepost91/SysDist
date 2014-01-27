/**
 *
 * @author Valerio
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Slave implements SlaveStub {

    public Slave() {
    }

    // Remote function which is called to execute remotely a command
    public String doTask(String command) {
        
        StringBuffer output = new StringBuffer();
        
        System.out.println("Executing command: "+ command );
        
        Process p;
        try {
            p = Runtime.getRuntime().exec(new String[]{"bash","-c",command});
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
        
        System.out.println("Processing OK. Result: " + output.toString());

        return output.toString();
    }
    
    public boolean receiveFile(byte[] b, String filename) {
        
        for (int i = 0; i < b.length; i++) {
            System.out.print((char)b[i]);
        }
        
        try {
             FileOutputStream fos = new FileOutputStream(filename);
             fos.write(b);
             fos.close();
        }
        catch(FileNotFoundException ex)   {
             System.out.println("FileNotFoundException : " + ex);
        }
        catch(IOException ioe)  {
             System.out.println("IOException : " + ioe);
        }
        
        return true;
    }

    public static void receiveFile(String path) {
        
    }
    public static void main(String args[]) {
        
        if (args.length < 1 || args.length > 2) {
            System.out.println("ERROR: expected at least 1 argument, found "
                    + args.length
                    + ". Usage: java Slave <slave_id> (<registry_host>)");
        }
        else {
            // Gets the ID of this slave by command line argument. This ID is 
            //   used to  diferentiate different slaves in the registry (se the 
            //   rebind() call below).
            int myId = Integer.parseInt(args[0]);
            
            String host = (args.length == 2) ? args[1] : null;
        
            try {
                Slave obj = new Slave();
                SlaveStub stub = (SlaveStub) UnicastRemoteObject.exportObject(obj, 0);

                // Retrieves the remote Registry
                Registry registry = LocateRegistry.getRegistry(host);
                // Binds the Slave even if there was already another one registered with the same
                //   same, so we don't have to reestart the Registry each time.
                registry.rebind("slave" + myId, stub);
                
                System.err.println("Slave " + myId + " ready, sir.");
            } catch (Exception e) {
                System.err.println("Slave exception: " + e.toString());
                System.err.println("Have you executed \"start remiregistry\" already?");
                //e.printStackTrace();
            }
        }
    }
}
