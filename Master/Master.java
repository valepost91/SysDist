/**
 *
 * @author Valerio
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;


public class Master {

    private Master() {
    }
        
    private static Machine[] retrieveRemoteSlaves(String host, int slavesCount) throws RemoteException {
        Machine machs[] = new Machine[slavesCount];
        SlaveStub stubs[] = new SlaveStub[slavesCount];
        
        Registry registry = LocateRegistry.getRegistry(host);
        
        boolean allOk = true;
        for (int i = 0; i < slavesCount; i++) {
            try {
                //stubs[i].s = (SlaveStub) registry.lookup("slave" + i);
                machs[i] = new Machine();
                machs[i].setStub( (SlaveStub) registry.lookup("slave" + i) );
                
                
            } catch (NotBoundException e) {
                System.err.println
                ("ERROR: Slave " + i + " not bound. Are you running the Registry and this Slave?\n");
                allOk = false;
            }
        }
        
        if (!allOk)
            return null;
        else
            return machs;
    }
    
    private static void runDistributedMakefile(MakefileStruct m, MachinesList machs) throws RemoteException {
        
        RuleRunner rootRunner = new RuleRunner(0, machs, m);
        rootRunner.start();
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

            MachinesList machs = new MachinesList( retrieveRemoteSlaves(host, slavesCount) );
            //stubs.toString();
            if (machs!=null) {
                System.out.println("All " + slavesCount + " slaves bounded successfully.");
                
                MakefileStruct m = new MakefileStruct("./makefile_test_simple");
                m.print(); //debug
                
                runDistributedMakefile(m,machs);
            }
            else
                System.out.println("Couldn't bind all Slaves, exiting now...");
            
            
        }

    }
}
