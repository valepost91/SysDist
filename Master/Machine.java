
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
         }
        
        return b;
    }
    
    public boolean sendFile(String filename) throws RemoteException {
        byte b[] = convertFileToBytes(filename);
        if (b!=null) {
            return s.saveFile(b, filename);
        }
        else
            return false;
    }
    
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
    
    String doTask(String command) throws RemoteException {
        return s.doTask(command);
    }
    
}
