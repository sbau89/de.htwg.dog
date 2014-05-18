/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dog.Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kev
 */
public class Board {

    public final List<Square> squares;

    public Board() {
        this.squares = new ArrayList<>();

        for (int i = 0; i < 48; i++) {
            squares.add(new Square("S" + i));
        }
    }

    public Square getSquareByName(String name) {
        for (Square square : squares) {
            if (square.getName().equals(name)) {
                return square;
            }
        }

        return null;
    }
}
