/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;


/**
 *
 * @author jmoschon
 */
public class heuristic_set1 {
   
    private final HashMap <rule_engine.RuleEngine,Integer> heuristicMap ;
//    private final ArrayList<> heuristics;
    
    public heuristic_set1(){
        this.heuristicMap= new HashMap<>();
        
    }
    
//    public void callRules(rule_engine.RuleEngine KB){
//        this.totalRules(KB);
//    }
    //h1 as first rule
    /**
     * @param KB original KB
     * @param depth
     * @return HashMap with less compromise KB's
     */
    public HashMap<rule_engine.RuleEngine,Integer> h1 (rule_engine.RuleEngine KB, int depth){
        
        //count false => facts , rules, preference
        //original KB
        
        HashMap<String,HashMap<String,Boolean>>rules= KB.return_rules();
        HashMap<String,HashMap<String,Boolean>>preferences= KB.return_preference();
        HashMap <String,Boolean> facts= KB.return_facts();
        // END of original KB 
        if ( KB.isConsistentGeneral()== false){
            
            for ( String str: rules.keySet()){
                for (String str1 : rules.get(str).keySet()){
                    if (rules.get(str).get(str1)){
                       rules.get(str).replace(str1, false);
                        KB.print_rules();
                        KB.print_preferences();
                        KB.print_facts();
                        KB.init_reasoner();
                        System.out.println(KB.isConsistentGeneral());
                        KB.print_inferted();
                        
                                
                       if (KB.isConsistentGeneral()){
                           //add to KB sta ipopsifiat
                           System.out.println("peeeeeeos");
                           heuristicMap.put(KB, depth);
                           if(this.h1_count(KB, depth)>=depth){
                            rules.get(str).replace(str1, true);
                           }
                           
//                           return heuristicMap;                           
                       }
                       else{
                            if(this.h1_count(KB, depth)>=depth){
                                rules.get(str).replace(str1, true);
                           }
                       }
                    }
                }            
            }
            for ( String str: facts.keySet()){
            
                if (facts.get(str)){
                   facts.replace(str, false);
                        KB.print_rules();
                        KB.print_preferences();
                        KB.print_facts();
                        KB.init_reasoner();
                        System.out.println(KB.isConsistentGeneral());
                        KB.print_inferted();
                   if (KB.isConsistentGeneral()){
                       //add to KB sta ipopsifia
                       System.out.println("aaaaa");
                       heuristicMap.put(KB, depth);
                       if(this.h1_count(KB, depth)>=depth){
                           facts.replace(str,true);
                       }
                       
//                       return heuristicMap;
                   }
                   else{
                       if(this.h1_count(KB, depth)>=depth){
                           facts.replace(str,true);
                       }
                   }
                }
            }
            if (heuristicMap.isEmpty()){
//                depth++;
                this.h1(KB, depth+1);
            }

            
            
//        this.heuristicMap.put(1, count);
            System.out.println(heuristicMap);
//        return heuristicMap;
    }
        return heuristicMap;
    }
    
    public int h1_count (rule_engine.RuleEngine KB,int depth){
        
        HashMap<String,HashMap<String,Boolean>>rules= KB.return_rules();
        HashMap<String,HashMap<String,Boolean>>preferences= KB.return_preference();
        HashMap <String,Boolean> facts= KB.return_facts();
        
            int count=0;
            for (String str : rules.keySet()){
//                System.out.println("to str einai :"+str);
            //cheks if the rules is active or not and count the false values
                for (Boolean active : rules.get(str).values()){
//                System.out.println(active);
                    if (!active){
                    count++;
                    }            
                }        
            }
        
            for (String str : preferences.keySet()){
//                System.out.println("to str einai :"+str);
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
            
//            if (count < depth){
//                return false;
//            }
//            else{
//                return true;
//            }
            return count;
    }
    
    
}
