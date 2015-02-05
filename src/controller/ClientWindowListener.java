// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Collection;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import application.client.GameEngineClientStub;

public class ClientWindowListener implements WindowListener {
    
    private GameEngine gameEngine;

    public ClientWindowListener(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void windowActivated(WindowEvent e) {};

    @Override
    public void windowClosed(WindowEvent e) {};

    @Override
    public void windowClosing(WindowEvent e) {
        Collection<Player> clientSidePlayers = 
            ((GameEngineClientStub) gameEngine).getClientPlayers();
        for (Player p : clientSidePlayers) {
            gameEngine.removePlayer(p);
        }
        System.exit(0);
    }

    @Override
    public void windowDeactivated(WindowEvent e) {};

    @Override
    public void windowDeiconified(WindowEvent e) {};

    @Override
    public void windowIconified(WindowEvent e) {};

    @Override
    public void windowOpened(WindowEvent e) {};  

}
