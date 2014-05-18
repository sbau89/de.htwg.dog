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
public class Card {

    private ValueEnum card;
    private SuitEnum suit;

    private Card() {

    }

    public Card(ValueEnum card, SuitEnum suit) {
        this.card = card;
        this.suit = suit;
    }

    public Card(String name) {
        this.suit = SuitEnum.fromString(name.substring(0, name.indexOf("_")));
        this.card = ValueEnum.fromString(name.substring(name.indexOf("_") + 1, name.length()));
    }

    void changeCard(ValueEnum card, SuitEnum suit) {
        this.card = card;
        this.suit = suit;
    }

    String getName() {
        return suit.getName() + "_" + card.getName();
    }

    ValueEnum getCard() {
        return card;
    }

    SuitEnum getSuit() {
        return suit;
    }
}
