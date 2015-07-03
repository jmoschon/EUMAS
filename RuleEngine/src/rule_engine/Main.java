/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rule_engine;

import java.io.IOException;

/**
 *
 * @author jmoschon
 */
public class Main {
    
        
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic her
        
        
        RuleEngine engine;
        engine = new RuleEngine();
        engine.readKB("/home/jmoschon/Desktop/praktikh/test");
//        engine.add_rules("a => b");
//        engine.add_rules("a => k");
//        engine.add_rules("b => c");
//        engine.add_rules("d => -c");
//        engine.add_rules("-d => c");
//        engine.add_rules("=> a");
//        engine.add_rules("=> e");
//        engine.add_rules("=> b");
//        engine.add_rules("e => -d");
//        engine.add_rules("b => d");
//        engine.add_rules("d > b");
//        engine.add_rules("a > -d");
////        
        
        
        engine.print_facts();
        engine.print_rules();
        engine.print_preferences();
        engine.init_reasoner();
        engine.print_inferted();
        
        //ask if the KB is consistent
        System.out.println(engine.isConsistent("go_party"));
                
        
    }
    
}
