
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cmdalbem
 */
public class Machine {
    public SlaveStub s;
    public boolean isBusy;
    
    Machine() {
        isBusy = false;
    }
    
    public void setStub(SlaveStub s) {
        this.s = s;
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
    
    public boolean receiveFile(String filename) throws RemoteException {
        return s.receiveFile(convertFileToBytes(filename), filename);
    }
    
    String doTask(String command) throws RemoteException {
        return s.doTask(command);
    }
    
}
