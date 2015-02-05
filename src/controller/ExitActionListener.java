// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import application.client.GameEngineClientStub;

public class ExitActionListener implements ActionListener {
    
    private GameEngine gameEngine;

    public ExitActionListener(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Collection<Player> clientSidePlayers = 
            ((GameEngineClientStub) gameEngine).getClientPlayers();
        for (Player p : clientSidePlayers) {
            gameEngine.removePlayer(p);
        }
        System.exit(0);
    }

}
