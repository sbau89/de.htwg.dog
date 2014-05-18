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
public class Draw {

    public static boolean isDrawAllowed(Square from, Square to, Card card, Player player) {

        if (card.getCard() == ValueEnum.ACE) {
            if (from.getType() == Square.Type.HOME && to == player.startSquare) {
                return true;
            }
        }
        
        boolean bool = false;
        

        bool |= Rules.fromNormalToNormalSquare(from, to, card.getCard().getI1());
        bool |= Rules.fromNormalToNormalSquare(from, to, card.getCard().getI2()); 

        return bool;
    }
}
