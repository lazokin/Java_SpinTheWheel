// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package view.playerlist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.GameEngineImpl;
import model.interfaces.Player;
import view.ClientWindow;

@SuppressWarnings("serial")
public class PlayerInfoPanel extends JPanel {

    // Child Component References
    private JTextArea playerInfoTextArea;

    // Constants
    private final int WIDTH = ClientWindow.WIDTH / GameEngineImpl.MAX_PLAYERS;
    private final int HEIGHT = ClientWindow.WIDTH / GameEngineImpl.MAX_PLAYERS - 2;

    // Constructor
    public PlayerInfoPanel(Player player) {
        super();
        this.setupThisComponent();
        this.createChildComponents();
        this.setupChildComponents(player);
        this.addChildComponents();   
    }
    
    // Construction Methods

    private void setupThisComponent() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    
    private void createChildComponents() {
        this.playerInfoTextArea = new JTextArea();   
    }
    
    private void setupChildComponents(Player player) {
        this.playerInfoTextArea.setBackground(Color.WHITE);
        this.playerInfoTextArea.append(" Player: ");
        this.playerInfoTextArea.append(player.getPlayerName() + "\n");
        this.playerInfoTextArea.append("\n");
        this.playerInfoTextArea.append(" Bet: ");
        this.playerInfoTextArea.append(player.getBet() + "\n");
        this.playerInfoTextArea.append(" Lucky Number: ");
        this.playerInfoTextArea.append(player.getNumberPick() + "\n");
        this.playerInfoTextArea.append("\n");
        this.playerInfoTextArea.append(" Points: ");
        this.playerInfoTextArea.append(String.valueOf(player.getPoints()));
    }

    private void addChildComponents() {
        this.add(playerInfoTextArea);
    }
}
