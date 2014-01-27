
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nrnia.dalbem
 */
public final class MakefileStruct {    
    
    ////////////////
    // Attributes //
    ////////////////

    // A makefile structure is nothing more than a list of rules
    private ArrayList< Rule > rules;
    
    
    ////////////////
    // Contructor //
    ////////////////
    
    public MakefileStruct() { 
        rules = new ArrayList<>();
    }
    
    public MakefileStruct(String path) throws FileNotFoundException, IOException
    {
        this();
        
        FileReader f = new FileReader(path);
        BufferedReader b = new BufferedReader(f);
        
        // Reads the file line by line  
        String s;
        int ruleIndex=-1;
        while (true) {    
            // Check file's end
            if ((s = b.readLine()) == null)
                break;
            
            if(s.length()>0) {
                // Split using the rule delimiter ':'
                String[] tokens = s.split("[:]+");
                
                // CASE: this line has a rule with dependencies
                if (tokens.length > 1) {
                    ruleIndex++;
                    
                    //System.out.println("got a rule with dependencies:\nrule: " + tokens[0]);
                    String name = tokens[0];

                    // Split dependencies
                    String depsStrings[] = tokens[1].split("[ ]+");
                    ArrayList<String> deps = new ArrayList<>();
                    for (String d : depsStrings) {
                        if (d.length() > 0) {
                            deps.add(d);
                        }
                    }
                    
                    this.insertRuleWithDeps(name, deps);
                }
                else {
                    // CASE: this line is a command
                    if (s.charAt(0)=='\t') {
                        //System.out.println("got command:\n" + s);
                        this.insertRuleCommand(ruleIndex, s);
                    }
                    // CASE: this line is a rule with no dependencies at all
                    else {
                        //System.out.println("got a rule with no dependencies:\n" + s);
                        ruleIndex++;
                        this.insertRule(s);
                    }
                }

                //System.out.println();
                
           }// end if
            
        }// end while
    
    }// end function
    
    ////////////////////////////////////////////////
    // Utility methods. Feel free to add your own //
    ////////////////////////////////////////////////
    
    public void print() {
        System.out.println("Printing makefile structure: (" + this.getRulesCount() + ")");
        
        for (int r=0; r < this.getRulesCount(); r++) {
            // Print rule name
            System.out.println("Rule #" + r + ": " + this.getRuleName(r));
            
            // Prints list of dependencies
            ArrayList<String> deps = this.getRuleDependencies(r);
            if (deps.size()>0) {
                System.out.print("Dependencies (" + deps.size() + ") : ");
                for (int i = 0; i < deps.size(); i++) {
                    System.out.print(deps.get(i) + ", ");
                }
                System.out.println();
            }
            
            // Prints list of commands
            ArrayList<String> coms = this.getRuleCommands(r);
            System.out.println("Commands (" + coms.size() + ") : ");
            for (int i = 0; i < coms.size(); i++) {
                System.out.println("#" + i + ": " + coms.get(i));           
            }
        }
    }

    public ArrayList<Rule> getRules() {
        return rules;
    }
    
    public int getRulesCount() {
        return rules.size();
    }
    
    public void insertRule(String name) {
        rules.add(new Rule(name));
    }
    
    public void insertRule(String name, ArrayList<String> dependencies, ArrayList<String> commands) {
        rules.add(new Rule(name, dependencies, commands));
    }
    
    public void insertRuleWithDeps(String name, ArrayList<String> dependencies) {
        rules.add(new Rule(name, dependencies, new ArrayList<String>()));
    }
    
    public void setRuleDependencies(int index, ArrayList<String> dependencies) {
        rules.get(index).dependencies = dependencies;
    }
    
    public void setRuleCommands(int index, ArrayList<String> commands) {
        rules.get(index).commands = commands;
    }
    
    public void insertRuleDependencie(int index, String d) {
        rules.get(index).dependencies.add(d);
    }
    
    public void insertRuleCommand(int index, String c) {
        rules.get(index).commands.add(c);
    }
    
    public ArrayList<String> getRuleDependencies(int index) {
        return rules.get(index).dependencies;
    }
    
    public ArrayList<String> getRuleCommands(int index) {
        return rules.get(index).commands;
    }
    
    public String getRuleName(int index) {
        return rules.get(index).name;
    }
    
    
};
