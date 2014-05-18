/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dog.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 *
 * @author kev
 */
public final class Gui extends JFrame {

    final JSplitPane splitPaneV;
    final JSplitPane splitPaneH;
    final BoardPanel boardPanel;
    final CardPanel cardPanel;
    final JPanel controlPanel;
    final JTextArea commandLabel = new JTextArea();
    final JButton executeButton = new JButton("Zug ausführen");
    final JButton discardButton = new JButton("Karten verwerfen");
    final JMenuBar menuBar = new JMenuBar();
    final JMenu menu = new JMenu("Menu");
    final JMenuItem startMenuItem = new JMenuItem("Start");
    final JLabel label = new JLabel("Player 1");

    public Gui() {

        setTitle("Dog");

        ImageIcon img = new ImageIcon("Images/tailchase.png");
        this.setIconImage(img.getImage());

        JPanel topPanel = new JPanel();
        topPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        topPanel.setLayout(new BorderLayout());
        setJMenuBar(menuBar);
        getContentPane().add(topPanel);
        menuBar.add(menu);
        menu.add(startMenuItem);

        /*Border raisedbevel = BorderFactory.createRaisedBevelBorder();
         Border loweredbevel = BorderFactory.createLoweredBevelBorder();
         Border comp = BorderFactory.createLineBorder(Color.BLACK, 10);
         Border c = BorderFactory.createCompoundBorder(raisedbevel,comp);
        
         boardPanel = new BoardPanel();
         boardPanel.setBorder(BorderFactory.createCompoundBorder(c, loweredbevel));*/
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();

        boardPanel = new BoardPanel();
        boardPanel.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
        boardPanel.setPreferredSize(new Dimension(500, 500));
        boardPanel.setMinimumSize(new Dimension(200, 200));

        cardPanel = new CardPanel();
        cardPanel.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
        cardPanel.setMinimumSize(new Dimension(200, 200));
        cardPanel.setPreferredSize(new Dimension(200, 300));

        controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        JPanel panel = new JPanel(new BorderLayout(0, 5));
        JPanel panel2 = new JPanel(new BorderLayout(0, 5));
        panel2.setBackground(Color.lightGray);
        panel.setBorder(new EmptyBorder(new Insets(3, 3, 3, 3)));

        panel2.add(label, BorderLayout.CENTER);
        buttonPanel.add(discardButton);
        buttonPanel.add(executeButton);
        label.setFont(new Font("Helvetica", Font.BOLD, 32));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        discardButton.setBorder(raisedbevel);
        executeButton.setBorder(raisedbevel);
        JScrollPane scrollPane = new JScrollPane(commandLabel);
        scrollPane.setBorder(loweredbevel);
        panel2.add(buttonPanel, BorderLayout.PAGE_END);
        panel.add(panel2, BorderLayout.PAGE_START);
        panel.add(scrollPane, BorderLayout.CENTER);
        commandLabel.setLineWrap(true);
        commandLabel.setEditable(false);
        controlPanel.setBackground(Color.lightGray);
        panel.setBackground(Color.lightGray);
        controlPanel.add(panel, BorderLayout.CENTER);

        splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneH.setBorder(null);
        BasicSplitPaneUI ui = (BasicSplitPaneUI) splitPaneH.getUI();
        BasicSplitPaneDivider divider = ui.getDivider();
        divider.setBorder(new EmptyBorder(new Insets(2, 2, 2, 2)));
        //divider.setBorder(BorderFactory.createEmptyBorder());
        //divider.setDividerSize(7);
        topPanel.add(splitPaneH, BorderLayout.CENTER);

        splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneV.setBorder(null);
        /*splitPaneV.setUI(new BasicSplitPaneUI() {
         @Override
         public BasicSplitPaneDivider createDefaultDivider() {
         return new BasicSplitPaneDivider(this) {
         @Override
         public void setBorder(Border b) {
         super.setBorder(new EmptyBorder(new Insets(2, 2, 2, 2)));
         }
        
         @Override
         public void paint(Graphics g) {
         g.setColor(Color.BLACK);
         g.fillRect(0, 0, getSize().width, getSize().height);
         super.paint(g);
         }
         };
         }
         });*/
        ui = (BasicSplitPaneUI) splitPaneV.getUI();
        divider = ui.getDivider();
        divider.setBorder(new EmptyBorder(new Insets(2, 2, 2, 2)));
        //divider.setBorder(BorderFactory.createEmptyBorder());
        //divider.setDividerSize(7);

        splitPaneV.setMinimumSize(new Dimension(175, 600));
        splitPaneV.setLeftComponent(cardPanel);
        splitPaneV.setRightComponent(controlPanel);

        splitPaneH.setLeftComponent(splitPaneV);
        splitPaneH.setRightComponent(boardPanel);

        setMinimumSize(new Dimension(500, 500));
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    

    public void setCommandText(String text) {
        commandLabel.setText(text);
    }
    
    public void setSelectedSquares(String square1, String square2){
        boardPanel.setCheckedSquare1(square1);
        boardPanel.setCheckedSquare2(square2);
        repaint();
    }
    
    public void setPlayer(String player){
        label.setText(player);
    }

    public void setOccupiedSquares(List<String> tokens, int player) {
        boardPanel.board.players.get(player).setOccupiedSquares(tokens);
    }

    public void setCards(List<String> cards) {
        cardPanel.setCards(cards);
    }

    public void addSquareListener(ActionListener actionListener) {
        boardPanel.addActionListener(actionListener);
    }

    public void addCardListener(ActionListener actionListener) {
        cardPanel.addActionListener(actionListener);
    }


    public void addExecuteButtonListener(ActionListener actionListener) {
        executeButton.addActionListener(actionListener);
    }

    public void addDiscardButtonListener(ActionListener actionListener) {
        discardButton.addActionListener(actionListener);
    }

    public void addStartMenuItemListener(ActionListener actionListener) {
        startMenuItem.addActionListener(actionListener);
    }
    
}
