/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dog.View;

import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author kev
 */
public class View {

    Gui gui;

    public View() {
        gui = new Gui();
    }

    public void repaint() {
        gui.repaint();
    }

    public void setCommandText(String text) {
        gui.setCommandText(text);
    }
    
    public void setSelectedSquares(String square1, String square2){
        gui.setSelectedSquares(square1, square2);
    }
    
    public void setPlayer(String player){
        gui.setPlayer(player);
    }

    public void setOccupiedSquares(List<String> tokens, int player) {
        gui.setOccupiedSquares(tokens, player);
    }

    public void setCards(List<String> cards) {
        gui.setCards(cards);
    }

    public void addSquareListener(ActionListener actionListener) {
        gui.addSquareListener(actionListener);
    }

    public void addCardListener(ActionListener actionListener) {
        gui.addCardListener(actionListener);
    }

    public void addDoTurnListener(ActionListener actionListener) {
        gui.addExecuteButtonListener(actionListener);
    }

    public void addDiscardCardsListener(ActionListener actionListener) {
        gui.addDiscardButtonListener(actionListener);
    }

    public void addStartGameListener(ActionListener actionListener) {
        gui.addStartMenuItemListener(actionListener);
    }
}
