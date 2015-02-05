// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package view.playerlist;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.GameEngineImpl;
import model.interfaces.Player;
import view.ClientWindow;

@SuppressWarnings("serial")
public class PlayerListPanel extends JPanel {

    // Constants
    private final int HEIGHT = ClientWindow.WIDTH / GameEngineImpl.MAX_PLAYERS;

    // Constructor
    public PlayerListPanel() {
        super();
        this.setupThisComponent();   
    }

    // Construction Methods
    
    private void setupThisComponent() {
        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, HEIGHT));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));    
    }
    
    // PlayerListPanel Methods

    public void refreshPlayers(Collection<Player> players) {
        this.removeAll();
        this.repaint();

        Player[] playersArray = new Player[players.size()];
        players.toArray(playersArray);
        int numberOfPlayers = playersArray.length;

        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = playersArray[i];
            PlayerInfoPanel pip = new PlayerInfoPanel(player);

            if (i == 0) {
                if (numberOfPlayers == GameEngineImpl.MAX_PLAYERS) {
                    pip.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,
                            Color.BLACK));
                } else {
                    pip.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1,
                            Color.BLACK));
                }

            } else if (i == numberOfPlayers - 1) {
                if (numberOfPlayers == GameEngineImpl.MAX_PLAYERS) {
                    pip.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0,
                            Color.BLACK));
                } else {
                    pip.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,
                            Color.BLACK));
                }

            } else {
                pip.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,
                        Color.BLACK));
            }

            this.add(pip);

        }

        this.revalidate();
        this.repaint();
    }

}
