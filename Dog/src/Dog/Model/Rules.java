/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dog.Model;

/**
 *
 * @author kev
 */
public class Rules {
    
    
    public void fromHomeToNormalSquare(Square from, Square to, Player player){
        
    }
    
    public static boolean fromNormalToNormalSquare(Square from, Square to, int valueToGo){
        int difference = Int.getDifference(from.getNumber(), to.getNumber());
        return difference == valueToGo;
    }
    
    public boolean fromNormalToFinishSquare(Square from, Square to, int valueToGo, Player player){
        int difference = Int.getDifference(from.getNumber(), player.startSquare.getNumber());
        return difference + to.getNumber()+1 == valueToGo;
    }
    
    public void getAceRules(Square from, Square to, Player player){
        if(from.getType() == Square.Type.HOME && to.getType() == Square.Type.STANDART){
            
        }
        if(from.getType() == Square.Type.STANDART && to.getType() == Square.Type.STANDART){
            
        }
        if(from.getType() == Square.Type.STANDART && to.getType() == Square.Type.FINISH){
            
        }
    }
    
    static class Int{
        
        public static int getDifference(int i1, int i2){
            int diff = i2 - i1;
            if(diff < 0) diff += 48;
            return diff;
        }

    }
}
