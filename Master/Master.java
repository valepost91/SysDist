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
    
    private static boolean runDistributedMakefile(MakefileStruct m, MachinesList machs, String ruleName) throws RemoteException, InterruptedException {
        
        long startTime = System.currentTimeMillis();
        
        Rule r;
        if (ruleName!=null) {
            System.out.println("ruleName = " + ruleName);
            r = m.getRules().get(ruleName);
            if (r==null)
                return false;
        }
        else
            r = m.root;
        
        System.out.println("rule = " + r.getName());
        
        RuleRunner rootRunner = new RuleRunner(r, machs, m);
        rootRunner.start();
        rootRunner.join();
        
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
        
        return true;
    }

    public static void main(String[] args) throws IOException, RemoteException, InterruptedException {
        
        if (args.length < 1 || args.length > 2) {
            System.out.println("ERROR: expected at least 1 argument, found "
                    + args.length
                    + ". Usage: java Master <makefile_path> [<rule_name>] < hostFile");    
        }
        else {
            // Process command line arguments
            String makefilePath = args[0];
            
            String rule = null;
            if (args.length==2)
                rule = args[1];
            
            // Get hosts from INPUT
            ArrayList<String> hosts = new ArrayList<>();
            Scanner reader = new Scanner(System.in);
            while (reader.hasNext())
                hosts.add( reader.next() );

            // Retrieve all stubs
            MachinesList machs = new MachinesList( retrieveRemoteSlaves(hosts) );
            if (machs.machs!=null) {
                System.out.println("All " + hosts.size() + " slaves bounded successfully.");
                
                // Parse Makefile and generate the graph
                MakefileStruct m = new MakefileStruct(makefilePath);
                m.print();
                
                // Run it
                runDistributedMakefile(m,machs,rule);
            }
            else
                System.out.println("Couldn't bind all Slaves, exiting now...");
            
            
        }
    }
}
