// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.playerlist.PlayerListPanel;
import view.status.GameStatusPanel;
import view.status.ResultStatusPanel;
import view.toolbar.MainToolbar;
import view.wheel.WheelPanel;
import application.client.ClientStubListener;

@SuppressWarnings("serial")
public class ClientPanel extends JPanel implements ClientStubListener {

    // External References
    private ClientWindow clientWindow;
    private GameEngine gameEngine;

    // Child Component References
    private MainToolbar mainToolbar;
    private WheelPanel wheelPanel;
    private PlayerListPanel playerListPanel;
    private GameStatusPanel gameStatusPanel;
    private ResultStatusPanel resultStatusPanel;

    // Constructor
    public ClientPanel(ClientWindow gameWindow, GameEngine gameEngine) {
        super();
        this.processParameters(gameWindow, gameEngine);
        this.setupThisComponent();
        this.createChildComponents();
        this.addChildComponents();
    }

    // Construction Methods

    private void processParameters(ClientWindow clientWindow, GameEngine gameEngine) {
        this.clientWindow = clientWindow;
        this.gameEngine = gameEngine;
    }

    private void setupThisComponent() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void createChildComponents() {
        this.mainToolbar = new MainToolbar(clientWindow, gameEngine);
        this.wheelPanel = new WheelPanel();
        this.playerListPanel = new PlayerListPanel();
        this.gameStatusPanel = new GameStatusPanel();
        this.resultStatusPanel = new ResultStatusPanel();

    }

    private void addChildComponents() {
        this.add(mainToolbar, BorderLayout.NORTH);
        this.add(wheelPanel, BorderLayout.CENTER);
        this.add(playerListPanel, BorderLayout.SOUTH);
        this.add(gameStatusPanel, BorderLayout.WEST);
        this.add(resultStatusPanel, BorderLayout.EAST);
    }

    // GamePanel Methods

    public void disableControlsDuringSpin() {
        this.mainToolbar.disableControlsDuringSpin();
    }

    public void enableControlsAfterSpin() {
        this.mainToolbar.enableControlsAfterSpin();
    }

    public void disableControlsWhenNoPlayers() {
        this.mainToolbar.disableControlsWhenNoPlayers();
    }
    
    public void disableControlsWhenMaxPlayers() {
        this.mainToolbar.disableControlsWhenMaxPlayers();   
    }

    public void enableControlsWhenSomePlayers() {
        this.mainToolbar.enableControlsWhenSomePlayers();
    }
    
    public void disableAllControls() {
        this.mainToolbar.disableAllControls();
    }

    // GameListener Interface Methods

    @Override
    public void notifyPlayerAdded(Collection<Player> serverSidePlayers,
        Collection<Player> clientSidePlayers, Player player) {
        this.playerListPanel.refreshPlayers(serverSidePlayers);
        this.gameStatusPanel.printPlayerAdded(player);
    }

    @Override
    public void notifyPlayerRemoved(Collection<Player> serverSidePlayers,
        Collection<Player> clientSidePlayers, Player player) {
        this.playerListPanel.refreshPlayers(serverSidePlayers);
        this.gameStatusPanel.printPlayerRemoved(player);
    }

    @Override
    public void notifyBetPlaced(Collection<Player> players, Player player) {
        this.playerListPanel.refreshPlayers(players);
        this.gameStatusPanel.printBetPlaced(player);
    }

    @Override
    public void notifyNextNumber(int nextNumber) {
        this.wheelPanel.updateNuber(nextNumber);
    }

    @Override
    public void notifySpinComplete(Collection<Player> allPlayers,
        Collection<Player> spinPlayers, int result, int wheelSize) {
        this.playerListPanel.refreshPlayers(allPlayers);
        this.resultStatusPanel.printResults(allPlayers, spinPlayers, result,
                wheelSize);
        this.gameStatusPanel.printSpinComplete();
    }

    @Override
    public void notifyServerDisconnected() {
        this.gameStatusPanel.printServerDisconnected();
    }

    @Override
    public void notifyPlayersReturned(Collection<Player> players) {
        this.playerListPanel.refreshPlayers(players);
    }

}
