
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
public class MachinesList {
    
    public Machine[] machs;
    
    MachinesList(Machine machs[]) {
        if (machs!=null)
            this.machs = machs;
    }
    
    synchronized public int getFreeMachine() {
        //System.out.println("--searching for a free machine...");
        for (int i = 0; i < machs.length; i++) {
            if (!machs[i].isBusy) {
                machs[i].isBusy = true;
                //System.out.println("--found one! it's the " + i);
                return i;
            }                    
        }

        return -1;
    }
    
    synchronized public boolean getMachineState(int i) {
        return machs[i].isBusy;
    }
    
    synchronized public void setMachineFree(int i) {
        machs[i].isBusy = false;
    }
    
}
