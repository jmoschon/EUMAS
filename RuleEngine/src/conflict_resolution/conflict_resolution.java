/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conflict_resolution;

import heuristics.heuristic_set1;
import java.util.HashMap;

/**
 *
 * @author jmoschon
 */
public class conflict_resolution {
    
    private final rule_engine.RuleEngine originalKB ;
    private final heuristics.heuristic_set1 heuristics;
//    private final HashMap <rule_engine.RuleEngine,heuristic_set1> d;
    
    public conflict_resolution(rule_engine.RuleEngine KB){
        this.originalKB=KB;
        this.heuristics= new heuristic_set1();
        
//        this
        
        //edw isws tha prepei na exw kati to opoio na krataei ola ta pithana CR kathos kai mia vathmologika apo ta HS
        this.CRP(KB);
    }
    
    
    public  void CRP(rule_engine.RuleEngine KB){
        
        HashMap<rule_engine.RuleEngine, HashMap<Integer,Integer>> list= new HashMap<>();
        HashMap <rule_engine.RuleEngine,Integer> heuristicMap ;
        if (originalKB.isConsistentGeneral()== false){
//           this.heuristics.callRules(KB)
//            for ()
//            this.heuristics.callRules(KB);
//            System.out.println("ppp");
            heuristicMap=this.heuristics.h1(KB, 2);
//            System.out.println(heuristicMap.keySet().size());
       
       
       }
        
    }
}
