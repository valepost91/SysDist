
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    
    // Prepare a file to be sent by RMI, converting it to a simple array of bytes.
    private static byte[] convertFileToBytes(String path) {
        File file = new File(path);
        
        if (!file.exists())
            return null;

        byte[] b = new byte[(int) file.length()];
        try {
              FileInputStream fileInputStream = new FileInputStream(file);
              fileInputStream.read(b);
         } catch (IOException e1) {
            //System.out.println("Error Reading The File.");
            //return null;
         }
        
        return b;
    }
    
    // Send a file to the remote machine
    public boolean sendFile(String filename) throws RemoteException {
        byte b[] = convertFileToBytes(filename);
        if (b!=null) {
            return s.saveFile(b, filename);
        }
        else
            return false;
    }
    
    // Send a file form the remote machine
    public boolean receiveFile(String filename) throws RemoteException {
        byte b[] = s.readFile(filename);
        
        if (b==null)
            return false;
        
        try {
             FileOutputStream fos = new FileOutputStream(filename);
             fos.write(b);
             fos.close();
        }
        catch(IOException ioe)  {
             //System.out.println("IOException : " + ioe);
            return false;
        }
        
        return true;
    }
    
    // Sends command to be executed in the remote machine
    String doTask(String command) throws RemoteException {
        return s.doTask(command);
    }
    
}
