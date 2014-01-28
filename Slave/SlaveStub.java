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
