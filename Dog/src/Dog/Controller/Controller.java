/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dog.Controller;

import Dog.Model.Model;
import Dog.View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kev
 */
public class Controller {

    private final View theView;
    private final Model theModel;

    public Controller(View theView, Model theModel) {
        
        this.theView = theView;
        this.theModel = theModel;

        theView.addSquareListener(new SquareListener());
        theView.addCardListener(new CardListener());
        theView.addStartGameListener(new StartGameListener());
        theView.addDoTurnListener(new DoTurnListener());
        theView.addDiscardCardsListener(new DiscardCardsListener());
    }

    void updateView() {
        updateCards();
        updateTexts();
        updateSquares();
        
        theView.repaint();
    }

    void updateTexts() {
        theView.setPlayer("Player " + theModel.getCurrentPlayerNumber());
        theView.setCommandText("1. Gewähltes Feld: " + theModel.getSelectedSquare(1) + "\n2. Gewähltes Feld: " + theModel.getSelectedSquare(2)
                + "\n2. Gewählte Karte: " + theModel.getSelectedCard());
    }

    void updateCards() {
        List<String> cards = theModel.getPlayers().get(theModel.getCurrentPlayerNumber()).getStringCards();
        Collections.sort(cards);
        theView.setCards(cards);
    }
    
    

    void updateSquares() {

        for (int i = 0; i < 4; i++) {
            theView.setOccupiedSquares(theModel.getStringOccupiedSquares(i), i);
        }
        
        theView.setSelectedSquares(theModel.getSelectedSquare(1), theModel.getSelectedSquare(2));
    }

    class DiscardCardsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            theModel.discardCards();
            updateView();

        }
    }

    class DoTurnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            theModel.doTurn();
            updateView();
        }
    }

    class StartGameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            theModel.createNewGame();
            updateView();
        }
    }

    class SquareListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            theModel.setSelectedSquare(e.getActionCommand());
            updateView();
        }
    }

    class CardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            theModel.setSelectedCard(e.getActionCommand());
            updateView();
        }
    }
}
