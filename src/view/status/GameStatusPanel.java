// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package view.status;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.interfaces.Player;

@SuppressWarnings("serial")
public class GameStatusPanel extends JPanel {

    // Child Component References
    private JTextArea gameStatusTextArea;
    private JScrollPane gameStatusScrollPane;

    // Constructor
    public GameStatusPanel() {
        super();
        this.setupThisComponent();
        this.createChildComponents();
        this.setupChildComponents();
        this.addChildComponents();
    }
    
    // Construction Methods

    private void setupThisComponent() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(300, 1));
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));    
    }

    private void createChildComponents() {
        this.gameStatusTextArea = new JTextArea();
        this.gameStatusScrollPane = new JScrollPane(gameStatusTextArea);
    }

    private void setupChildComponents() {
        this.gameStatusTextArea.setEditable(false);
        this.gameStatusScrollPane.setViewportBorder(null);  
    }

    private void addChildComponents() {
        this.add(gameStatusScrollPane);  
    }
    
    // GameStatusPanel Methods

    public void printPlayerAdded(Player player) {
        String status = " " + player.getPlayerName() + " joined the game with "
            + player.getPoints() + " points\n";
        this.updateGameStatusTextArea(status);
    }

    public void printPlayerRemoved(Player player) {
        String status = " " + player.getPlayerName() + " left the game with "
            + player.getPoints() + " points\n";
        this.updateGameStatusTextArea(status);
    }

    public void printBetPlaced(Player player) {
        String status = " " + player.getPlayerName() + " placed a bet of "
            + player.getBet() + " on number " + player.getNumberPick() + "\n";
        this.updateGameStatusTextArea(status);
    }

    public void printSpinComplete() {
        String status = " Spin Complete. Results generated." + "\n";
        this.updateGameStatusTextArea(status);
    }
    
    public void printServerDisconnected() {
        String status = " Server Disconnected..." + "\n";
        this.updateGameStatusTextArea(status);
    }

    private void updateGameStatusTextArea(String status) {
        this.gameStatusTextArea.append(status);
        this.gameStatusScrollPane.getVerticalScrollBar().setValue(100);
    }

}
