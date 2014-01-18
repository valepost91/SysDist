/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

    public String doTask(String command) {
        
        StringBuffer output = new StringBuffer();
        
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    public static void main(String args[]) {

        try {
            Slave obj = new Slave();
            Task stub = (Task) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Task", stub);

            System.err.println("Slave ready");
        } catch (Exception e) {
            System.err.println("Slave exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
