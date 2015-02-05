// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package view;

import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.menu.MainMenuBar;
import application.client.ClientStubListener;
import controller.ClientWindowListener;

@SuppressWarnings("serial")
public class ClientWindow extends JFrame implements ClientStubListener {

    // External References
    private GameEngine gameEngine;

    // Child Component References
    private MainMenuBar mainMenuBar;
    private ClientPanel clientPanel;

    // Constants
    public final static int WIDTH = 1024;
    public final static int HEIGHT = 768;

    // Constructor
    public ClientWindow(GameEngine gameEngine) {
        super();
        this.processParameters(gameEngine);
    }

    // Construction Methods
    
    public void createGUI() {
        this.setupThisComponent();
        this.createChildComponents();
        this.addChildComponents();
    }

    private void processParameters(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    private void setupThisComponent() {
        this.setTitle("Spin The Wheel Client");
        this.setSize(ClientWindow.WIDTH, ClientWindow.HEIGHT);
        this.addWindowListener(new ClientWindowListener(gameEngine));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void createChildComponents() {
        this.mainMenuBar = new MainMenuBar(this, gameEngine);
        this.clientPanel = new ClientPanel(this, gameEngine);
    }

    private void addChildComponents() {
        this.setJMenuBar(mainMenuBar);
        this.setContentPane(clientPanel);
    }

    // GameWindow Methods

    public void disableControlsDuringSpin() {
        this.clientPanel.disableControlsDuringSpin();
        this.mainMenuBar.disableControlsDuringSpin();
    }

    public void enableControlsAfterSpin() {
        this.clientPanel.enableControlsAfterSpin();
        this.mainMenuBar.enableControlsAfterSpin();
    }

    public void disableControlsWhenNoPlayers() {
        this.clientPanel.disableControlsWhenNoPlayers();
        this.mainMenuBar.disableControlsWhenNoPlayers();
    }
    
    public void disableControlsWhenMaxPlayers() {
        this.clientPanel.disableControlsWhenMaxPlayers();
        this.mainMenuBar.disableControlsWhenMaxPlayers();
    }

    public void enableControlsWhenSomePlayers() {
        this.clientPanel.enableControlsWhenSomePlayers();
        this.mainMenuBar.enableControlsWhenSomePlayers();
    }
    
    protected void disableAllControls() {
        this.clientPanel.disableAllControls();
        this.mainMenuBar.disableAllControls();
    }

    // GameListener Interface Methods

    @Override
    public void notifyPlayerAdded(Collection<Player> serverSidePlayers,
        Collection<Player> clientSidePlayers, Player player) {
        
        SwingUtilities.invokeLater(new Runnable(){

            private ClientWindow clientWindow;
            private Collection<Player> serverSidePlayers;
            private Collection<Player> clientSidePlayers;
            private Player player;
            
            @Override
            public void run() {
                clientWindow.clientPanel.notifyPlayerAdded(serverSidePlayers,
                    clientSidePlayers, player);
                if (serverSidePlayers.size() == GameEngineImpl.MAX_PLAYERS) {
                    clientWindow.disableControlsWhenMaxPlayers();
                } else if (clientSidePlayers.size() > 0) {
                    clientWindow.enableControlsWhenSomePlayers();
                } 
            }

            public Runnable init(ClientWindow clientWindow,
                Collection<Player> serverSidePlayers,
                Collection<Player> clientSidePlayers, Player player) {
                this.clientWindow = clientWindow;
                this.serverSidePlayers = serverSidePlayers;
                this.clientSidePlayers = clientSidePlayers;
                this.player = player;
                return this;
            }
            
        
        }.init(this, serverSidePlayers, clientSidePlayers, player));

    }

    @Override
    public void notifyPlayerRemoved(Collection<Player> serverSidePlayers,
        Collection<Player> clientSidePlayers, Player player) {
        
        SwingUtilities.invokeLater(new Runnable(){

            private ClientWindow clientWindow;
            private Collection<Player> serverSidePlayers;
            private Collection<Player> clientSidePlayers;
            private Player player;
            
            @Override
            public void run() {
                clientWindow.clientPanel.notifyPlayerRemoved(serverSidePlayers,
                    clientSidePlayers, player);
                if (clientSidePlayers.size() == 0) {
                    clientWindow.disableControlsWhenNoPlayers();
                }
            }

            public Runnable init(ClientWindow clientWindow,
                Collection<Player> serverSidePlayers,
                Collection<Player> clientSidePlayers, Player player) {
                this.clientWindow = clientWindow;
                this.serverSidePlayers = serverSidePlayers;
                this.clientSidePlayers = clientSidePlayers;
                this.player = player;
                return this;
            }
            
        
        }.init(this, serverSidePlayers, clientSidePlayers, player));
        
    }

    @Override
    public void notifyBetPlaced(Collection<Player> players, Player player) {
        
        SwingUtilities.invokeLater(new Runnable(){

            private ClientWindow clientWindow;
            private Collection<Player> players;
            private Player player;
            
            @Override
            public void run() {
                clientWindow.clientPanel.notifyBetPlaced(players, player); 
            }

            public Runnable init(ClientWindow clientWindow,
                Collection<Player> players, Player player) {
                this.clientWindow = clientWindow;
                this.players = players;
                this.player = player;
                return this;
            }
            
        
        }.init(this, players, player));
        
    }

    @Override
    public void notifyNextNumber(int nextNumber) {
        
        SwingUtilities.invokeLater(new Runnable(){

            private ClientWindow clientWindow;
            private int nextNumber;
            
            @Override
            public void run() {
                clientWindow.clientPanel.notifyNextNumber(nextNumber);
                clientWindow.disableControlsDuringSpin();
            }

            public Runnable init(ClientWindow clientWindow, int nextNumber) {
                this.clientWindow = clientWindow;
                this.nextNumber = nextNumber;
                return this;
            }
            
        
        }.init(this, nextNumber));
        
    }

    @Override
    public void notifySpinComplete(Collection<Player> allPlayers,
        Collection<Player> spinPlayers, int result, int wheelSize) {
        
        SwingUtilities.invokeLater(new Runnable(){

            private ClientWindow clientWindow;
            private Collection<Player> allPlayers;
            private Collection<Player> spinPlayers;
            private int result;
            private int wheelSize;
            
            @Override
            public void run() {
                clientWindow.clientPanel.notifySpinComplete(allPlayers,
                    spinPlayers, result, wheelSize);
                clientWindow.enableControlsAfterSpin();
            }

            public Runnable init(ClientWindow clientWindow,
                Collection<Player> allPlayers, Collection<Player> spinPlayers,
                int result, int wheelSize) {
                this.clientWindow = clientWindow;
                this.allPlayers = allPlayers;
                this.spinPlayers = spinPlayers;
                this.result = result;
                this.wheelSize = wheelSize;
                return this;
            }

        }.init(this, allPlayers, spinPlayers, result, wheelSize));
        
     
    }

    @Override
    public void notifyServerDisconnected() {
        
        SwingUtilities.invokeLater(new Runnable(){

            private ClientWindow clientWindow;
            
            @Override
            public void run() {
                clientWindow.clientPanel.notifyServerDisconnected();
                clientWindow.disableAllControls();
            }

            public Runnable init(ClientWindow clientWindow) {
                this.clientWindow = clientWindow;
                return this;
            }
            
        
        }.init(this));
        
    }

    @Override
    public void notifyPlayersReturned(Collection<Player> players) {
        
        SwingUtilities.invokeLater(new Runnable(){

        private ClientWindow clientWindow;
        private Collection<Player> players;
        
        @Override
        public void run() {
            clientWindow.clientPanel.notifyPlayersReturned(players);
            if (players.size() != 0) {
                clientWindow.enableControlsWhenSomePlayers();
            } 
        }

        public Runnable init(ClientWindow clientWindow, Collection<Player> players) {
            this.clientWindow = clientWindow;
            this.players = players;
            return this;
        }
        
    
        }.init(this, players));
        
    }

}
