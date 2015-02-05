// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.UUID;

import javax.swing.JOptionPane;

import model.PlayerImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.ClientWindow;

import com.lazokin.util.SwingInput;

public class AddPlayerActionListener implements ActionListener {
    
    private ClientWindow clientWindow;
    private GameEngine gameEngine;

    public AddPlayerActionListener(ClientWindow clientWindow, GameEngine gameEngine) {
        this.clientWindow = clientWindow;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Collection<Player> players = gameEngine.getAllPlayers();

        String[] input = SwingInput.showTextDialog(clientWindow, "Add Player",
            200, new String[] { "Player Name", "Points" });

        if (input != null) {
            String playerId = UUID.randomUUID().toString();
            String playerName = input[0];
            if (playerNameExists(players, playerName)) {
                JOptionPane.showMessageDialog(clientWindow,
                    "Player name already exists. Player not added.",
                    "Add Player Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    int points = Integer.parseInt(input[1]);
                    if (points < 0) {
                        JOptionPane.showMessageDialog(clientWindow,
                            "Points cannot be negative. Player not added.",
                            "Add Player Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Player player = new PlayerImpl(playerId, playerName,
                            points, 0, 0);
                        gameEngine.addPlayer(player);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(clientWindow,
                        "Points not a number. Player not added.",
                        "Add Player Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    private boolean playerNameExists(Collection<Player> players, String name) {
        boolean result = false;
        for (Player p : players) {
            if (p.getPlayerName().equals(name)) {
                return true;
            }
        }
        return result;
    }

}
