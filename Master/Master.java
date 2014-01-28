/**
 *
 * @author Valerio
 */
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import java.util.ArrayList;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Timer;
import java.util.TimerTask;

public class Master {

    
    static private boolean debug = true;
    
    public Master() {
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
        
        RuleRunner rootRunner = new RuleRunner(m.root, machs, m);
        rootRunner.start();
    }

    public static void main(String[] args) throws IOException {

        long startTime = System.currentTimeMillis();
        
        if (args.length < 1 || args.length > 3) {
            System.out.println("ERROR: expected at least 2 argument, found "
                    + args.length
                    + ". Usage: java Master <makefile_path> <slaves_count> (<registry_host>)");    
        }
        else {
            // Process command line arguments
            String makefilePath = args[0];
            int slavesCount = Integer.parseInt(args[1]);
            String host = (args.length == 3) ? args[2] : null;

            MachinesList machs = new MachinesList( retrieveRemoteSlaves(host, slavesCount) );
            //stubs.toString();
            if (machs!=null) {
                System.out.println("All " + slavesCount + " slaves bounded successfully.");
                
                MakefileStruct m = new MakefileStruct(makefilePath);
                m.print();
                
                runDistributedMakefile(m,machs);
            }
            else
                System.out.println("Couldn't bind all Slaves, exiting now...");
            
            
        }
	long endTime = System.currentTimeMillis();
	System.out.println("That took " + (endTime - startTime) + " milliseconds");


    }
}
