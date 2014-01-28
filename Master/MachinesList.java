/**
 *
 * @author cmdalbem
 */

// A simple thread-safe array of machines 
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
