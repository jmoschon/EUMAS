/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;
import rule_engine.RuleEngine;


/**
 *
 * @author jmoschon
 */
public class heuristic_set {
   
//    private final HashMap <rule_engine.RuleEngine,Integer> heuristicMap ;
//    private final ArrayList<> heuristics;
    private final h1_TotalRules h1;
    private final rule_engine.RuleEngine originalKB;
    private  ArrayList<RuleEngine> candidatesKB;
    
    public heuristic_set(rule_engine.RuleEngine originalKB){
       this.candidatesKB= new ArrayList<>();
       this.h1 = new h1_TotalRules(originalKB);
       this.originalKB= originalKB; 
        
    }
    /**
     * The call method is 
     */
    public void call(){
        this.candidatesKB=this.h1.core(originalKB);
        
        
        System.out.println(candidatesKB);
        for (rule_engine.RuleEngine KB : candidatesKB) {
                
                KB.print_facts();
                KB.print_rules();
                KB.print_inferted();
                System.out.println(KB.isConsistentGeneral());
                System.out.println("______________________________");
            
        }
    }
    
}
