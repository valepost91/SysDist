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


public class Master {

    private Master() {
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

    public void sendRequest(String s) {

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
        
        if (allOk)
            return null;
        else
            return stubs;
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
            
            if (stubs!=null) {
                System.out.println("All " + slavesCount + " slaves bounded successfully.");
                
                //boolean ret = stubs[0].transferFile(convertFileToBytes("README.md") ,"test.md");
                        
                // Parse Makefile
                MakefileStruct m = new MakefileStruct("./makefile_test");
                //m.print(); //debug
            }
            else
                System.out.println("Couldn't bind all Slaves, exiting now...");
            
            
        }

    }
}
