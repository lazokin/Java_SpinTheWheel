// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package application.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

import model.interfaces.GameEngine;
import protocol.client.ClientRequest;
import protocol.server.BetPlacedNotification;
import protocol.server.NextNumberNotification;
import protocol.server.PlayerAddedNotification;
import protocol.server.PlayerRemovedNotification;
import protocol.server.ResultsNotification;
import protocol.server.ReturnPlayersNotification;

public class ClientThread extends Thread {
    
    private String uuid;
    
    private GameEngine gameEngine;
    private GameEngineServerStub gameEngineServerStub;
    
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    private boolean listening;

    public ClientThread(Socket socket, GameEngine gameEngine,
        GameEngineServerStub gameEngineServerStub) {
        this.uuid = UUID.randomUUID().toString();
        this.gameEngine = gameEngine;
        this.gameEngineServerStub = gameEngineServerStub;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        
        listening = true;

        while(listening) {
            try {
                ClientRequest clientRequest = (ClientRequest) in.readObject();
                clientRequest.executeCommand(gameEngine, gameEngineServerStub, this);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                listening = false;
                gameEngineServerStub.removeThread(this);
                gameEngineServerStub.notifyClientDisconnected();
            }
        }
        
    }
    
    public void notifyPlayerAdded(PlayerAddedNotification notification) {
        try {
            out.writeObject(notification);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void notifyPlayerRemoved(PlayerRemovedNotification notification) {
        try {
            out.writeObject(notification);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void notifyBetPlaced(BetPlacedNotification notification) {
        try {
            out.writeObject(notification);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void notifyNextNumber(NextNumberNotification nextNumberNotification) {
        try {
            out.writeObject(nextNumberNotification);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void notifyResults(ResultsNotification resultsNotification) {
        try {
            out.writeObject(resultsNotification);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void notifyReturnPlayers(ReturnPlayersNotification returnPlayersNotification) {
        try {
            out.writeObject(returnPlayersNotification);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUUID() {
        return uuid;
    }
    
}
