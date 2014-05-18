/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dog.Model;

import java.util.List;

/**
 *
 * @author kev
 */
public class Model {

    private Game game;

    public Model() {
    }

    public void createNewGame() {

        game = new Game();
    }

    public List<String> getStringOccupiedSquares(int playerNumber) {
        return game.getStringOccupiedSquares(playerNumber);
    }

    public List<Player> getPlayers() {
        return game.getPlayers();
    }

    public void setSelectedSquare(String squareName) {
        game.setSelectedSquare(squareName);
    }

    public void setSelectedCard(String cardName) {
        game.setSelectedCard(cardName);
    }

    public String getSelectedSquare(int number) {
        return game.getSelectedSquare(number);
    }

    public String getSelectedCard() {
        return game.getSelectedCard();
    }
    
    public void discardCards(){
        game.discardCards();
    }
    
    public int getCurrentPlayerNumber(){
        return game.getCurrentPlayerNumber();
    }

    public void doTurn() {
        game.doTurn();
    }
    
    private boolean checkVictoryConditions(){
        return game.checkVictoryConditions();
    }
}
