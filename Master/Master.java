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
import java.util.Scanner;


public class Master {

    
    private static final boolean debug = true;
    
    public Master() {
    }
        
    private static Machine[] retrieveRemoteSlaves(ArrayList<String> hosts) throws RemoteException {
        int slavesCount = hosts.size();
        Machine machs[] = new Machine[slavesCount];
        SlaveStub stubs[] = new SlaveStub[slavesCount];
        
        boolean allOk = true;
        for (int i = 0; i < slavesCount; i++) {
            try {
                machs[i] = new Machine();
                Registry registry = LocateRegistry.getRegistry(hosts.get(i));
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
        
        if (args.length != 1) {
            System.out.println("ERROR: expected at least 1 argument, found "
                    + args.length
                    + ". Usage: java Master <makefile_path> < hostFile");    
        }
        else {
            // Process command line arguments
            String makefilePath = args[0];
            
            // Get hosts from INPUT
            ArrayList<String> hosts = new ArrayList<>();
            Scanner reader = new Scanner(System.in);
            while (reader.hasNext())
                hosts.add( reader.next() );

            MachinesList machs = new MachinesList( retrieveRemoteSlaves(hosts) );
            //stubs.toString();
            if (machs.machs!=null) {
                System.out.println("All " + hosts.size() + " slaves bounded successfully.");
                
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
