// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package application.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.WheelCallback;
import protocol.Protocol;
import protocol.client.AddPlayerRequest;
import protocol.client.PlaceBetRequest;
import protocol.client.RemovePlayerRequest;
import protocol.client.ReturnPlayersRequest;
import protocol.client.SpinWheelRequest;
import protocol.server.ServerNotification;

public class GameEngineClientStub implements GameEngine, ClientStubListener {
    
    // Constants
    private final String HOSTNAME = "localhost";
    
    // Attributes
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int port;
    private boolean listening;
    
    // External Listeners
    private ArrayList<ClientStubListener> clientStubListeners;

    // Local Collection of Players
    private ArrayList<Player> serverSidePlayers;
    private ArrayList<Player> clientSidePlayers;

    // Constructor
    public GameEngineClientStub(int port) {
        super();
        this.port = port;
        this.clientStubListeners = new ArrayList<ClientStubListener>();
        this.serverSidePlayers = new ArrayList<Player>();
        this.clientSidePlayers = new ArrayList<Player>();
    }
    
    // ClientStubListener Registration Method
    public void registerClientStubListener(ClientStubListener clientStubListener) {
        this.clientStubListeners.add(clientStubListener);
    }
    
    // Start Client
    public void start () {
        try {
            socket = new Socket(HOSTNAME, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(new ReturnPlayersRequest());
            listen();
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                "UnknownHostException", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                "IOException", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Listen On Socket
    public void listen() {
        
        listening = true;
        
        while(listening) {
            try {
                ServerNotification serverNotification =
                    (ServerNotification) in.readObject();
                serverNotification.executeNotification(this);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                listening = false;
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                notifyServerDisconnected();
            }
        }
    }
    
    public void addClientSidePlayer (Player player) {
        this.clientSidePlayers.add(player);
    }
    
    public void removeClientSidePlayer (Player player) {
        Player[] clientSidePlayersCopy = new Player[clientSidePlayers.size()];
        clientSidePlayers.toArray(clientSidePlayersCopy);
        for (Player p : clientSidePlayersCopy) {
            if (p.getPlayerId().equals(player.getPlayerId())) {
                clientSidePlayers.remove(p);
            }
        }
    }
    
    public void updateClientSidePlayers () {
        Player[] clientSidePlayersCopy = new Player[clientSidePlayers.size()];
        clientSidePlayers.toArray(clientSidePlayersCopy);
        for (Player ssp : serverSidePlayers) {
            for (Player csp : clientSidePlayersCopy) {
                if (ssp.getPlayerId().equals(csp.getPlayerId())) {
                    clientSidePlayers.remove(csp);
                    clientSidePlayers.add(ssp);
                }
            }
        }
    }
    
    public void updateServerSidePlayers(Collection<Player> ps) {
        serverSidePlayers = new ArrayList<Player>(ps);
    }
    
    
    // GameEngine Interface Methods

    @Override
    public void addPlayer(Player player) {
        AddPlayerRequest addPlayerRequest =
            new AddPlayerRequest(Protocol.serializePlayer(player));
        try {
            out.writeObject(addPlayerRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean removePlayer(Player player) {
        boolean result = true;
        RemovePlayerRequest removePlayerRequest =
            new RemovePlayerRequest(Protocol.serializePlayer(player));
        try {
            if (!socket.isClosed()) {
                out.writeObject(removePlayerRequest);
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean placeBet(Player player, Integer numberPick, int bet) {
        boolean result = true;
        PlaceBetRequest placeBetRequest =
            new PlaceBetRequest(Protocol.serializePlayer(player, bet, numberPick));
        try {
            out.writeObject(placeBetRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public void spin(int wheelSize, int initialDelay, int finalDelay,
        int delayIncrement, WheelCallback callback) {
        SpinWheelRequest spinWheelRequest = new SpinWheelRequest(wheelSize,
            initialDelay, finalDelay, delayIncrement);
        try {
            out.writeObject(spinWheelRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void calculateResult(int result) {
        // TODO Auto-generated method stub

    }

    @Override
    public Collection<Player> getAllPlayers() {
        return serverSidePlayers;
    }
    
    public Collection<Player> getClientPlayers() {
        return clientSidePlayers;
    }
    
    // ClientStubListener Interface Methods

    @Override
    public void notifyPlayerAdded(Collection<Player> serverSidePlayers,
        Collection<Player> clientSidePlayers, Player player) {
        for (ClientStubListener clientStubListener: clientStubListeners) {
            clientStubListener.notifyPlayerAdded(serverSidePlayers,
                clientSidePlayers, player);
        } 
    }

    @Override
    public void notifyPlayerRemoved(Collection<Player> serverSidePlayers,
        Collection<Player> clientSidePlayers, Player player) {
        for (ClientStubListener clientStubListener: clientStubListeners) {
            clientStubListener.notifyPlayerRemoved(serverSidePlayers,
                clientSidePlayers, player);
        } 
        
    }

    @Override
    public void notifyBetPlaced(Collection<Player> players, Player player) {
        for (ClientStubListener clientStubListener: clientStubListeners) {
            clientStubListener.notifyBetPlaced(players, player);
        } 
        
    }

    @Override
    public void notifyNextNumber(int nextNumber) {
        for (ClientStubListener clientStubListener: clientStubListeners) {
            clientStubListener.notifyNextNumber(nextNumber);
        } 
 
    }

    @Override
    public void notifySpinComplete(Collection<Player> allPlayers,
        Collection<Player> spinPlayers, int result, int wheelSize) {
        for (ClientStubListener clientStubListener: clientStubListeners) {
            clientStubListener.notifySpinComplete(allPlayers, spinPlayers,
                    result, wheelSize);
        }
    }

    @Override
    public void notifyServerDisconnected() {
        for (ClientStubListener clientStubListener: clientStubListeners) {
            clientStubListener.notifyServerDisconnected();
        } 
    }
    
    @Override
    public void notifyPlayersReturned(Collection<Player> players) {
        for (ClientStubListener clientStubListener: clientStubListeners) {
            clientStubListener.notifyPlayersReturned(players);
        } 
    }

}
