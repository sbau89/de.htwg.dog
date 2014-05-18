/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dog.Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author kev
 */
class Game {

    List<Player> players = new ArrayList<>();
    Deck deck;
    int cardsPerHand;
    Player currentPlayer;
    Card selectedCard;
    Square selectedSquare1;
    Square selectedSquare2;
    Board board;
    final Square defaultSquare;

    void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    void setSelectedSquare(Square square) {

        if (selectedSquare1 == defaultSquare && square != selectedSquare2) {
            selectedSquare1 = square;
        } else if (selectedSquare1 == square) {
            selectedSquare1 = defaultSquare;
        } else if (selectedSquare2 == defaultSquare && square != selectedSquare1) {
            selectedSquare2 = square;
        } else if (selectedSquare2 == square) {
            selectedSquare2 = defaultSquare;
        }
    }

    Game() {

        board = new Board();

        players.add(new Player(Color.yellow, board.getSquareByName("S0"), 0));
        players.add(new Player(Color.blue, board.getSquareByName("S12"), 1));
        players.add(new Player(Color.green, board.getSquareByName("S24"), 2));
        players.add(new Player(Color.cyan, board.getSquareByName("S36"), 3));

        for (Player player : players) {
            board.squares.addAll(player.finishSquares);
            board.squares.addAll(player.homeSquares);
        }

        defaultSquare = new Square(" ");
        deck = new Deck();
        cardsPerHand = 6;
        currentPlayer = players.get(0);
        selectedSquare1 = defaultSquare;
        selectedSquare2 = defaultSquare;

        newRound();
    }

    void newRound() {


        if (cardsPerHand == 1) cardsPerHand = 6;
        
        if(cardsPerHand * 4 > deck.undealedCards.size()) deck = new Deck();

        for (Player player : players) {
            for (int i = 0; i < cardsPerHand; i++) {
                Card card = deck.undealedCards.get(new Random().nextInt(deck.undealedCards.size() - 1));
                deck.undealedCards.remove(card);
                player.cards.add(card);
            }
        }

        cardsPerHand--;
    }

    boolean nextPlayer() {
        int playerNumber = currentPlayer.playerNumber;
        Player nextPlayer;

        if (playerNumber == 3) playerNumber = 0;
        else playerNumber++;

        nextPlayer = players.get(playerNumber);

        while (nextPlayer.cards.isEmpty()) {
            if (nextPlayer == currentPlayer) return false;
            
            if (playerNumber == 3) playerNumber = 0;
            else playerNumber++;
            
            nextPlayer = players.get(playerNumber);
        }

        currentPlayer = nextPlayer;
        return true;
    }

    void setOccupiedSquares() {
        for (Player player : players) {
            for (Square square : player.occupiedSquares) {
                square.setOccupation(true);
            }
        }
    }

    int getCurrentPlayer() {
        return currentPlayer.playerNumber;
    }
    
    
    List<String> getStringOccupiedSquares(int playerNumber) {
        Player player = getPlayers().get(playerNumber);

        return player.getStringOccupiedSquares();
    }

    List<Player> getPlayers() {
        return players;
    }

    void setSelectedSquare(String squareName) {
        Square s = board.getSquareByName(squareName);
        
        setSelectedSquare(s);
    }

    void setSelectedCard(String cardName) {
        Card c = currentPlayer.getCardByName(cardName);
        setSelectedCard(c);
    }

    String getSelectedSquare(int number) {
        if (number == 1) {
            return selectedSquare1.getName();
        } else if (number == 2){
            return selectedSquare2.getName();
        } else return "";
    }

    String getSelectedCard() {
        if (selectedCard == null) {
            return " ";
        } else {
            return selectedCard.getName();
        }
    }
    
    void discardCards(){
        currentPlayer.cards = new ArrayList<>();
        if(!nextPlayer()) {
            newRound();
            nextPlayer();
        }
    }
    
    int getCurrentPlayerNumber(){
        return currentPlayer.playerNumber;
    }

    void doTurn() {
        if (selectedSquare1.isOccupied()) {
            if (Draw.isDrawAllowed(selectedSquare1, selectedSquare2, selectedCard, currentPlayer)) {
                currentPlayer.occupiedSquares.remove(selectedSquare1);
                currentPlayer.occupiedSquares.add(selectedSquare2);
                selectedSquare1.setOccupation(false);
                selectedSquare2.setOccupation(true);
                setSelectedSquare(selectedSquare1);
                setSelectedSquare(selectedSquare2);
                currentPlayer.cards.remove(selectedCard);
                setSelectedCard(selectedCard);
                if(checkVictoryConditions());
                if(!nextPlayer()) newRound();
            }
        }
        
    }
    
    boolean checkVictoryConditions(){
        
        boolean hasWon = true;
        
        for(Square square : currentPlayer.finishSquares){
            hasWon &= square.isOccupied();
        }
        
        return hasWon;
    }
}
