
import java.util.ArrayList;

// Structure of a Makefile rule
public class Rule {
    
    private ArrayList< Rule > dependencies;
    private ArrayList< String > commands;
    private String name;
    public boolean isDone;
    private boolean taken;
     
    public Rule(String name) {
        this.name=name;
        dependencies = new ArrayList<>();
        commands = new ArrayList<>();
        isDone = false;
    }

    public Rule(String name, ArrayList<Rule> dependencies, ArrayList<String> commands) {
        this(name);
        this.dependencies = dependencies;
        this.commands = commands;
    }
    
    synchronized public boolean takeIt() {
        System.out.println("TAKEN of " + this.name + " is " + taken);
        
        if (!taken) 
            taken = true;
        
        return taken;
    }
    
    public ArrayList< Rule> getDependencies() {
        return dependencies;
    }

    public void setDependencies(ArrayList<Rule> dependencies) {
        this.dependencies = dependencies;
    }

    public ArrayList<String> getCommands() {
        return commands;
    }

    public void setCommands(ArrayList<String> commands) {
        this.commands = commands;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepsCount() { 
        return dependencies.size(); 
    }
    
    public int getCommandsCount() { 
        return commands.size(); 
    }
}