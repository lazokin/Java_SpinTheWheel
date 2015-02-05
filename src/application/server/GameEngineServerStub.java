// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package application.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import protocol.PlayerProtocol;
import protocol.Protocol;
import protocol.server.BetPlacedNotification;
import protocol.server.NextNumberNotification;
import protocol.server.PlayerAddedNotification;
import protocol.server.PlayerRemovedNotification;
import protocol.server.ResultsNotification;

public class GameEngineServerStub implements ServerStubListener {
    
    private GameEngine gameEngine;
    private boolean running;
    private int port;
    private ArrayList<ClientThread> clientThreads;
    private ServerSocket serverSocket;

    
    // External Listeners
    private ArrayList<ServerStubListener> serverStubListeners =
        new ArrayList<ServerStubListener>();

    // Constructor
    public GameEngineServerStub(int port, GameEngine gameEngine) {
        super();
        this.port = port;
        this.gameEngine = gameEngine;
        this.clientThreads = new ArrayList<ClientThread>();
    }

    // ServerStubListener Registration Method
    public void registerServerStubListener(ServerStubListener serverStubListener) {
        this.serverStubListeners.add(serverStubListener);
    }
    
    // Start Server
    public void start() {

        running = true;
        notifyServerStarted();
        
        try {
            serverSocket = new ServerSocket(port);
            while (running) {
                Socket socket = serverSocket.accept();
                ClientThread t = new ClientThread(socket, gameEngine, this);
                clientThreads.add(t);
                t.start();
                notifyClientConnected();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                "IOException", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    void removeThread(ClientThread clientThread) {
        clientThreads.remove(clientThread);
    }
    
    // Broadcast Methods
    
    public synchronized void broadcastPlayerAdded(Player player, String uuid) {
        notifyPlayerAdded(player);
        PlayerProtocol serializedPlayer =
            Protocol.serializePlayer(player);
        PlayerProtocol[] serializedPlayers =
            Protocol.serializePlayers(gameEngine.getAllPlayers());
        PlayerAddedNotification yourPlayerAddedNotification = 
            new PlayerAddedNotification(serializedPlayers, serializedPlayer, true);
        PlayerAddedNotification otherPlayerAddedNotification = 
            new PlayerAddedNotification(serializedPlayers, serializedPlayer, false);
        for(ClientThread ct: clientThreads) {
            if (ct.getUUID().equals(uuid)) {
                ct.notifyPlayerAdded(yourPlayerAddedNotification);
            } else {
                ct.notifyPlayerAdded(otherPlayerAddedNotification);
            }
            
        }
    }
    
    public synchronized void broadcastPlayerRemoved(Player player) {
        notifyPlayerRemoved(player);
        PlayerProtocol serializedPlayer =
            Protocol.serializePlayer(player);
        PlayerProtocol[] serializedPlayers =
            Protocol.serializePlayers(gameEngine.getAllPlayers());
        PlayerRemovedNotification playerRemovedNotification = 
            new PlayerRemovedNotification(serializedPlayers, serializedPlayer);
        for(ClientThread ct: clientThreads) {
            ct.notifyPlayerRemoved(playerRemovedNotification);
        }
    }
    
    public synchronized void broadcastBetPlaced(Player player) {
        notifyBetPlaced(player);
        PlayerProtocol serializedPlayer =
            Protocol.serializePlayer(player);
        PlayerProtocol[] serializedPlayers =
            Protocol.serializePlayers(gameEngine.getAllPlayers());
        BetPlacedNotification betPlacedNotification = 
            new BetPlacedNotification(serializedPlayers, serializedPlayer);
        for(ClientThread ct: clientThreads) {
            ct.notifyBetPlaced(betPlacedNotification);
        }
    }
    
    public synchronized void broadcastNextNumber(int nextNumber) {
        NextNumberNotification nextNumberNotification = 
            new NextNumberNotification(nextNumber);
        for(ClientThread ct: clientThreads) {
            ct.notifyNextNumber(nextNumberNotification);
        }
    }

    public synchronized void broadcastResults(Collection<Player> allPlayers,
        Collection<Player> spinPlayers, int result, int wheelSize) {
        notifySpinComplete(result);
        PlayerProtocol[] serializedAllPlayers = Protocol.serializePlayers(
            allPlayers);
        PlayerProtocol[] serializedSpinPlayers = Protocol.serializePlayers(
            spinPlayers);
        ResultsNotification resultsNotification =  new ResultsNotification(
            serializedAllPlayers, serializedSpinPlayers, result, wheelSize);
        for(ClientThread ct: clientThreads) {
            ct.notifyResults(resultsNotification);
        }
    }
    
    // ServerStubListener Interface Methods
    
    @Override
    public void notifyServerStarted() {
        for (ServerStubListener serverStubListener: serverStubListeners) {
            serverStubListener.notifyServerStarted();
        }  
    }
    
    @Override
    public void notifyClientConnected() {
        for (ServerStubListener serverStubListener: serverStubListeners) {
            serverStubListener.notifyClientConnected();
        } 
    }
    
    @Override
    public void notifyClientDisconnected() {
        for (ServerStubListener serverStubListener: serverStubListeners) {
            serverStubListener.notifyClientDisconnected();
        } 
    }

    @Override
    public void notifyPlayerAdded(Player player) {
        for (ServerStubListener serverStubListener: serverStubListeners) {
            serverStubListener.notifyPlayerAdded(player);
        }
    }

    @Override
    public void notifyPlayerRemoved(Player player) {
        for (ServerStubListener serverStubListener: serverStubListeners) {
            serverStubListener.notifyPlayerRemoved(player);
        } 
    }

    @Override
    public void notifyBetPlaced(Player player) {
        for (ServerStubListener serverStubListener: serverStubListeners) {
            serverStubListener.notifyBetPlaced(player);
        }
    }

    @Override
    public void notifyWheelSpinning() {
        for (ServerStubListener serverStubListener: serverStubListeners) {
            serverStubListener.notifyWheelSpinning();
        }  
    }

    @Override
    public void notifySpinComplete(int result) {
        for (ServerStubListener serverStubListener: serverStubListeners) {
            serverStubListener.notifySpinComplete(result);
        } 
    }

}
