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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Master {

    private Master() {
    }

    public void sendRequest(String s) {

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String host = (args.length < 1) ? null : args[0];

        FileReader f;
        f = new FileReader("./makefiles/premier/Makefile");

        BufferedReader b;
        b = new BufferedReader(f);

        String s;

        while (true) {
            s = b.readLine();
            if (s == null) {
                break;
            }
            System.out.println(s);

            try {
                Registry registry = LocateRegistry.getRegistry(host);
                Task stub = (Task) registry.lookup("Task");
                String response = stub.doTask(s);
                System.out.println("response: " + response);
            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        }

    }
}
