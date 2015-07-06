/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;


/**
 *
 * @author jmoschon
 */
public class heuristic_set1 {
   
    private final HashMap <Integer,Integer> heuristicMap ;
    
    public heuristic_set1(){
        this.heuristicMap= new HashMap<>();
        
    }
    
    public void callRules(rule_engine.RuleEngine KB){
        this.totalRules(KB);
    }
    //h1
    public void totalRules (rule_engine.RuleEngine KB){
        
        //count false => facts , rules, preference
        HashMap<String,HashMap<String,Boolean>>rules= KB.return_rules();
        HashMap<String,HashMap<String,Boolean>>preferences= KB.return_preference();
        HashMap <String,Boolean> facts= KB.return_facts();
        int count=0;
        for (String str : rules.keySet()){
            System.out.println("to str einai :"+str);
            //cheks if the rules is active or not and count the false values
            for (Boolean active : rules.get(str).values()){
//                System.out.println(active);
                if (!active){
                count++;
                }
            
            }
        
        }
        for (String str : preferences.keySet()){
            System.out.println("to str einai :"+str);
            //cheks if the rules is active or not and count the false values
            for (Boolean active : preferences.get(str).values()){
//                System.out.println(active);
                if (!active){
                count++;
                }
            }
        
        }    
        for (Boolean active : facts.values()){
//                System.out.println(active);
                if (!active){
                count++;
                }
        }   
        
        this.heuristicMap.put(1, count);
    }
    
}
