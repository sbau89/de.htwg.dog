/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dog.View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JPanel;

/**
 *
 * @author kev
 */
public final class CardPanel extends JPanel implements MouseListener {

    List<ActionListener> listeners = new ArrayList<>();
    public List<Card> cards = new ArrayList<>();

    public void setCards(List<String> cards) {
        this.cards = new ArrayList<>();
        for (String card : cards) {
            Card c = new Card(card);
            this.cards.add(c);
            if (clickedCard.value.equals(card)) {
                clickedCard = c;
            }
        }

    }

   
    Images images = new Images();
    private Card clickedCard;
    
    public CardPanel() {

        clickedCard = new Card("DIAMOND_A");
        setBackground(Color.lightGray);
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (!cards.isEmpty()) {

            int start = 10;
            int step = (this.getHeight() - 2 * start - cards.get(0).getHeight()) / (cards.size() - 1);
            int x = start;
            int y = start;

            for (Card card : cards) {

                card.setRect(new Rectangle2D.Double(x, y, card.getWidth(), card.getHeight()));

                if (card == clickedCard) {
                    g2d.setColor(Color.RED);
                } else {
                    g2d.setColor(Color.BLACK);
                }
                g2d.setStroke(new BasicStroke(3));
                g2d.drawImage(card.getImage(), card.getX(),card.getY(), null);
                g2d.drawRect(card.getX(), card.getY(), card.getWidth(), card.getHeight());
                g2d.setStroke(new BasicStroke());
                y += step;

            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if ((e.getButton() == 1)) {
            ListIterator iter = cards.listIterator(cards.size());
            while (iter.hasPrevious()) {
                Card card = (Card) iter.previous();
                if (card.getRect().contains(e.getX(), e.getY())) {
                    clickedCard = card;
                    for (ActionListener l : listeners) {
                        l.actionPerformed(new ActionEvent(this, 0, card.value));
                    }
                    return;
                }
            }
        }
    }

    public void addActionListener(ActionListener listener) {
        listeners.add(listener);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private class Card {

        private BufferedImage image = null;
        private Rectangle2D rect;
        private String value;

        public BufferedImage getImage() {
            return image;
        }

        public String getValue() {
            return value;
        }

        public int getX() {
            return (int) rect.getX();
        }

        public int getY() {
            return (int) rect.getY();
        }

        public int getWidth() {
            return (int) rect.getWidth();
        }

        public int getHeight() {
            return (int) rect.getHeight();
        }

        public void setRect(Rectangle2D rectangle) {
            rect = rectangle;
        }

        public Rectangle2D getRect() {
            return rect;
        }

        public Card(String value) {
            this.value = value;

            this.image = images.images.get(value);
            this.rect = new Rectangle2D.Double(0, 0, image.getWidth(), image.getHeight());

        }
    }
}
