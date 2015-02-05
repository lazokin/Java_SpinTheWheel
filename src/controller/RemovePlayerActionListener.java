// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.ClientWindow;
import application.client.GameEngineClientStub;

import com.lazokin.util.SwingInput;

public class RemovePlayerActionListener implements ActionListener {
    
    private ClientWindow clientWindow;
    private GameEngine gameEngine;

    public RemovePlayerActionListener(ClientWindow clientWindow,
        GameEngine gameEngine) {
        this.clientWindow = clientWindow;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Collection<Player> players =
            ((GameEngineClientStub) gameEngine).getClientPlayers();

        ArrayList<String[]> comboBoxContents = new ArrayList<String[]>();
        comboBoxContents.add(createPlayerComboBoxContents(players));
        String[] input = SwingInput.showComboDialog(clientWindow,
            "Remove Player", 300, new String[] { "Player" },
            comboBoxContents);
        if (input != null) {
            String playerId = getPlayerIdFromCollection(players, input[0]);
            Player player = getPlayerFromCollection(players, playerId);
            gameEngine.removePlayer(player);
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
