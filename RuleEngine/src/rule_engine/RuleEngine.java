/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rule_engine;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 *
 * @author jmoschon
 */

public class RuleEngine {

    
    private final HashMap<String,HashMap<String,Boolean>>rules;
    private final HashMap<String,HashMap<String,Boolean>>preferences;
    private final HashMap <String,Boolean> facts;
    private final HashSet <String> inferted;
    
           
    public RuleEngine(){
        rules= new HashMap<>();
        facts= new HashMap<>();
        preferences= new HashMap<>();
        inferted= new HashSet<>();
    }
    
    public void print_rules(){
        System.out.println("rules are : ");
        System.out.println(rules);
    
    }
    
    public void print_preferences(){
        System.out.println("preferences are : ");
        System.out.println(preferences);
    }
    
    public void print_facts(){
        System.out.println("facts are : ");
        System.out.println(facts);
    
    }
    
    //TODO: error checking 
    public void add_rules(String rule){
        StringTokenizer st = new StringTokenizer(rule);
        String a;
        String b;
        String c;
        // if st.countTokens ==2 we have a fact 
        if (st.countTokens()==2){
            st.nextToken();
            facts.put(st.nextToken(),true);
        }
        else{
            a=st.nextToken(); // first token
            b=st.nextToken(); // second token
            c=st.nextToken(); // third token
            if (b.equals("=>")){
              if (rules.containsKey(a))
                    rules.get(a).put(c,true);       
                else{ 
                    HashMap<String,Boolean> list=new  HashMap<>();
                    list.put(c,true);
                    rules.put(a,list);
                }
            }
            else{
              if (preferences.containsKey(a))
                    preferences.get(a).put(c,true);       
                else{ 
                    HashMap<String,Boolean> list=new  HashMap<>();
                    list.put(c,true);
                    preferences.put(a,list);
                }            
            
            }
        }
    }
    
  
    
    /**
     * 
     * @param path  the path of the file 
     */    
    public void readKB (String path) throws FileNotFoundException, IOException{
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = in.readLine()) != null)
            {
                this.add_rules(line);
            }
        }
    
    }
    
    
    //this function will provide all the inferted states and rules
    public void reasoner(String str){
//            System.out.println(str);
            if (rules.containsKey(str)){
                for (String value : rules.get(str).keySet()){ //keySet->set with the keys
//                    System.out.println(value);
//                    System.out.println(rules.get(str).get(value));
                    if (rules.get(str).get(value)){
                        if (!inferted.contains(value)){
                            this.inferted.add(value);
                        }
                        if(rules.containsKey(value)){
                            this.inferted.add(value);
                            this.reasoner(value);
                    
                        }
                    }
                }
            }
    }
    
  
    public void print_inferted(){
        System.out.println("inferted : ");
        System.out.println(inferted);
    }    

    

    
    public void init_reasoner(){
        for (String str : facts.keySet()) {
            //vale mesa ola ta facts
//            System.out.println(facts.get(str));
            if (facts.get(str)){
                if (inferted.isEmpty() || !inferted.contains(str)){
                
                    this.inferted.add(str);
                    this.reasoner(str);
                }
            }
        }
    }
    
    
    public boolean isConsistentGeneral(){
       
       for(String question :inferted){
        StringTokenizer st2 = new StringTokenizer(question, "-");
        char c = question.charAt(0);
        
        if (c=='-'){
            String question1= st2.nextToken("-");
            if(this.inferted.contains(question) && 
                    this.inferted.contains(question1)){
                return false;
            }
        }
        else{
            if(this.inferted.contains(question) && 
                    this.inferted.contains("-"+question)){
                return false;
            }
            
        }
       }
       return true;
    }
    //check if the KB is consistent 
    public boolean isConsistent(String question){
        StringTokenizer st2 = new StringTokenizer(question, "-");
        char c = question.charAt(0);
        
        if (c=='-'){
            String question1= st2.nextToken("-");
            if(this.inferted.contains(question) && 
                    this.inferted.contains(question1)){
                return false;
            }
        }
        else{
            if(this.inferted.contains(question) && 
                    this.inferted.contains("-"+question)){
                return false;
            }
            
        }
        return true;
    }


    
  // for the CR
    public HashMap<String,HashMap<String,Boolean>> return_rules(){
        return rules;  
    }
    
    public HashMap<String,HashMap<String,Boolean>> return_preference(){
        return preferences;
    }
    
    public HashMap <String,Boolean> return_facts(){
        return facts;
    }
}
