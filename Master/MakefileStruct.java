
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

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
    private Map< String,Rule > rules;
    private static String name;
    private static boolean debug = true;
    ////////////////
    // Contructor //
    ////////////////
    
    public MakefileStruct() { 
        rules = new HashMap<>();
    }
    
    public MakefileStruct(String path) throws FileNotFoundException, IOException
    {
        this();
        
        FileReader f = new FileReader(path);
        BufferedReader b = new BufferedReader(f);
        
        // Reads the file line by line  
        String s;
        while (true) {    
            // Check file's end
            if ((s = b.readLine()) == null)
                break;
            
            if(s.length()>0) {
                // Split using the rule delimiter ':'
                String[] tokens = s.split("[:]+");
                
                // CASE: this line has a rule with dependencies
                if (tokens.length > 1) {   
                    //System.out.println("got a rule with dependencies:\nrule: " + tokens[0]);
                    name = tokens[0];

                    // Split dependencies
                    String[] depsStrings = tokens[1].split("[ ]+");
                    if (debug)
                        System.out.println(tokens[1]);
                    
                    ArrayList<Rule> deps = new ArrayList<Rule>();
                    int i = 0;
                    for (String depStr : depsStrings) {
                            deps.add(i, new Rule(depStr));
                            if (debug)
                                System.out.println(depStr);
                            i++;
                         }
                    insertRuleWithDeps(name, deps);
                }
                else {
                    // CASE: this line is a command
                    if (s.charAt(0)=='\t') {
                        //System.out.println("got command:\n" + s);
                        this.insertRuleCommand(name, s);
                    }
                    // CASE: this line is a rule with no dependencies at all
                    else {
                        //System.out.println("got a rule with no dependencies:\n" + s);
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
    
    public void printRec(Rule r) {
        System.out.println("The name of the rule " + r.getName());
        ArrayList<Rule> deps = r.getDependencies();
        for (Rule d : deps) {
            // Print rule name
            printRec(d);
            
            // Prints list of dependencies
        /*    ArrayList<Rule> deps = this.getRuleDependencies(r);
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
            }*/
        }
    }
    
    public void print() {
        Rule r = rules.get("list.txt");
        System.out.println("Printing makefile structure!");
        printRec(r);
    }
    

    public Map<String,Rule> getRules() {
        return rules;
    }
    
    public int getRulesCount() {
        return rules.size();
    }
    
    public void insertRule(String name) {
        rules.put(name,new Rule(name));
    }
    
    public void insertRule(String name, ArrayList<Rule> dependencies, ArrayList<String> commands) {
        rules.put(name, new Rule(name, dependencies, commands));
    }
    
    public void insertRuleWithDeps(String name, ArrayList<Rule> dependencies) {
        rules.put(name, new Rule(name, dependencies, new ArrayList<String>()));
    }
    
    public void setRuleDependencies(String name, ArrayList<Rule> dependencies) {
        rules.get(name).setDependencies(dependencies);
    }
    
    public void setRuleCommands(String name, ArrayList<String> commands) {
        rules.get(name).setCommands(commands);
    }
    //probably to erase
    public void insertRuleDependency(String name, Rule d) {
        rules.get(name).getDependencies().add(d);
            
    }
    
    public void insertRuleCommand(String name, String c) {
        rules.get(name).getCommands().add(c);
    }
    
    public ArrayList<Rule> getRuleDependencies(String name) {
        return rules.get(name).getDependencies();
    }
    
    public ArrayList<String> getRuleCommands(String name) {
        return rules.get(name).getCommands();
    }
    
    public String getRuleName(String name) {
        return rules.get(name).getName();
    }
    
};
