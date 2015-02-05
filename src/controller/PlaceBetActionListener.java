// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.ClientWindow;
import application.client.GameEngineClientStub;

import com.lazokin.util.SwingInput;

public class PlaceBetActionListener implements ActionListener {
    
    private ClientWindow clientWindow;
    private GameEngine gameEngine;

    public PlaceBetActionListener(ClientWindow clientWindow, GameEngine gameEngine) {
        this.clientWindow = clientWindow;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        Collection<Player> players =
            ((GameEngineClientStub) gameEngine).getClientPlayers();
        
        ArrayList<String[]> comboBoxContents = new ArrayList<String[]>();
        comboBoxContents.add(createPlayerComboBoxContents(players));
        String[] input = SwingInput.showComboTextDialog(clientWindow,
            "Place Bet",300, new String[] { "Player" }, new String[] {
            "Bet Amount", "Wheel Number" }, comboBoxContents);

        if (input != null) {
            boolean error = false;

            String playerId = getPlayerIdFromCollection(players, input[0]);
            Player player = getPlayerFromCollection(players, playerId);

            int bet = 0;
            if (!error) {
                try {
                    bet = Integer.parseInt(input[1]);
                    int maxBet = player.getPoints();
                    if (player.getPoints() == 0) {
                        JOptionPane.showMessageDialog(clientWindow,
                            "Player has no points to bet. No bet placed.",
                            "Place Bet Error", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    } else if (bet < 0 || bet > maxBet) {
                        JOptionPane.showMessageDialog(clientWindow,
                            "Bet must be between 0 and " + maxBet
                                + " No bet placed.", "Place Bet Error",
                            JOptionPane.ERROR_MESSAGE);
                        error = true;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(clientWindow,
                        "Bet not a number. No bet placed.",
                        "APlace Bet Error", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }
            }

            int number = 0;
            if (!error) {
                try {
                    number = Integer.parseInt(input[2]);
                    if (number < 1) {
                        JOptionPane.showMessageDialog(clientWindow,
                            "Wheel number must be greater than 1",
                            "Place Bet Error", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(clientWindow,
                        "Wheel number must be a number. No bet placed.",
                        "Place Bet Error", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }
            }

            if (!error) {
                gameEngine.placeBet(player, number, bet);
            }
        }

    }

    private String[] createPlayerComboBoxContents(Collection<Player> players) {
        String[] result = new String[players.size()];
        int idx = 0;
        for (Player p : players) {
            result[idx++] = p.getPlayerName() + " (" + p.getPoints()
                + " points)";
        }
        return result;
    }

    private String getPlayerIdFromCollection(Collection<Player> players,
        String selectedIndex) {
        return ((Player) players.toArray()[Integer.valueOf(selectedIndex)])
            .getPlayerId();
    }

    private Player getPlayerFromCollection(Collection<Player> players,
        String playerId) {
        Player player = null;
        for (Player p : players) {
            if (p.getPlayerId().equals(playerId)) {
                player = p;
            }
        }
        return player;
    }

}
