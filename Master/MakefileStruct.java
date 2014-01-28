
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    private static String lastRuleName;
    private static boolean debug = false;
    public Rule root;
    
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
        boolean foundRoot = false;
        while (true) {    
            // Check file's end
            if ((s = b.readLine()) == null)
                break;
            
            if (s.length()>0) {
                // Split using the rule delimiter ':'
                String[] tokens = s.split("[:]+");
               
                // CASE: this line has a rule with dependencies
                if (tokens.length > 1) {   
                    if(debug) System.out.println("got a rule with dependencies:\nrule: " + tokens[0]);
                    lastRuleName = tokens[0];

                    // Split dependencies
                    String[] depsStrings = tokens[1].split("[ \t]+");
                    ArrayList<Rule> deps = new ArrayList<>();
                    for (String d : depsStrings) {
                        if(debug) System.out.println("got a dependency: " + d);
                        if(d.length()>0) {
                            Rule tmp = this.insertRule(d);
                            deps.add(tmp);
                            rules.put(d, tmp);                            
                        }
                    }
                    
                    Rule tmp = insertRuleWithDeps(lastRuleName, deps);
                    if (!foundRoot) {
                        root = tmp;
                        foundRoot = true;
                    }
                        
                }
                else {
                    // CASE: this line is a command
                    if (s.charAt(0)=='\t') {
                        if(debug) System.out.println("got command:\n" + s);
                        this.insertRuleCommand(lastRuleName, s);
                    }
                    // CASE: this line is a rule with no dependencies at all
                    else {
                        if(debug) System.out.println("got a rule with no dependencies:\n" + s);
                        lastRuleName = tokens[0];
                        Rule tmp = this.insertRule(lastRuleName);
                        if (!foundRoot) {
                            root = tmp;
                            foundRoot = true;
                        }
                    }
                }

                if(debug) System.out.println();
                
           }// end if
            
        }// end while
    
    }// end function
    
    ////////////////////////////////////////////////
    // Utility methods. Feel free to add your own //
    ////////////////////////////////////////////////
    
    private void printRec(Rule r, int depth) {
        char[] chars = new char[depth];
        Arrays.fill(chars, '\t');
        String spaces = new String(chars);
        
        System.out.println(spaces + "--\"" + r.getName() + "\"");
        ArrayList<Rule> deps = r.getDependencies();

        for (Rule d : deps)
            System.out.println(spaces + "  " + "dep: " + d.getName());
        
        // Prints list of commands
        ArrayList<String> coms = r.getCommands();
        if(coms.size()!=0)
            System.out.println(spaces + "  " + "Commands (" + coms.size() + ") : ");
        for (int i = 0; i < coms.size(); i++) {
            System.out.println(spaces + "  " + "#" + i + ": " + coms.get(i));           
        }
        
        for (Rule d : deps) {
            printRec(d,depth+1);
        }
    }
    
    public void print() {
        printRec(root,0);
    }
    

    public Map<String,Rule> getRules() {
        return rules;
    }
    
    public int getRulesCount() {
        return rules.size();
    }
    
    public Rule insertRule(String name) {
        Rule tmp;
        
        if (!rules.containsKey(name)) {
            tmp = new Rule(name);
            rules.put(name,tmp);
        }
        else
            tmp = rules.get(name);
        
        return tmp;
    }
    
    public void insertRule(String name, ArrayList<Rule> dependencies, ArrayList<String> commands) {
        rules.put(name, new Rule(name, dependencies, commands));
    }
    
    public Rule insertRuleWithDeps(String name, ArrayList<Rule> dependencies) {
        Rule tmp;
        
        //System.out.println("Asked to create new rule " + name);
        
        if (!rules.containsKey(name)) {
            //System.out.println(name + " didnt exist before");
            tmp = new Rule(name, dependencies, new ArrayList<String>());
            rules.put(name, tmp);
        }
        else {
            //System.out.println(name + " already existed before!");
            for( Rule r : dependencies )
                this.insertRuleDependency(name, r);
            tmp = rules.get(name);
        }       
        
        return tmp;
    }
    
    public void setRuleDependencies(String name, ArrayList<Rule> dependencies) {
        if (rules.get(name)==null);
            System.out.println("ERROR @ setRuleDependencies! Couldn't find rule " + name);
        rules.get(name).setDependencies(dependencies);
    }
    
    public void setRuleCommands(String name, ArrayList<String> commands) {
        if (rules.get(name)==null)
            System.out.println("ERROR @ setRuleCommands! Couldn't find rule " + name);
        rules.get(name).setCommands(commands);
    }
    
    public void insertRuleDependency(String name, Rule d) {
        if (rules.get(name)==null)
            System.out.println("ERROR @ insertRuleDependency! Couldn't find rule " + name);
        rules.get(name).getDependencies().add(d);
            
    }
    
    public void insertRuleCommand(String name, String c) {
        if (rules.get(name)==null)
            System.out.println("ERROR @ insertRuleCommand! Couldn't find rule " + name);
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
