/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Valerio
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SlaveStub extends Remote {
    String doTask(String command) throws RemoteException;
    boolean saveFile(byte[] file, String filename) throws RemoteException;
    byte[] readFile(String filename) throws RemoteException ;
}
