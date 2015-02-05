// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package protocol.client;

import java.io.Serializable;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import protocol.PlayerProtocol;
import protocol.Protocol;
import application.server.ClientThread;
import application.server.GameEngineServerStub;

@SuppressWarnings("serial")
public class RemovePlayerRequest extends ClientRequest implements Serializable{
    
    private PlayerProtocol player;

    public RemovePlayerRequest(PlayerProtocol player) {
        super();
        this.player = player;
    }

    public PlayerProtocol getPlayer() {
        return player;
    }

    @Override
    public void executeCommand(GameEngine ge, GameEngineServerStub gess,
        ClientThread ct) {
        Player p = Protocol.deserializePlayer(player);
        ge.removePlayer(p);
        gess.broadcastPlayerRemoved(p);  
    }

}
