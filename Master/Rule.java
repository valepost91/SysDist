
import java.util.ArrayList;

// Structure of a Makefile rule
public class Rule {
    String name;
    ArrayList< String > dependencies;

    public ArrayList<String> getDependencies() {
        return dependencies;
    }
    ArrayList< String > commands;

    public Rule(String name) {
        dependencies = new ArrayList<>();
        commands = new ArrayList<>();
        this.name = name;
    }

    public Rule(String name, ArrayList<String> dependencies, ArrayList<String> commands) {
        this(name);
        this.dependencies = dependencies;
        this.commands = commands;
    }

    public int getDepsCount() { return dependencies.size(); }
    public int getCommandsCount() { return commands.size(); }
};