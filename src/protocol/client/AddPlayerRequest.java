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
public class AddPlayerRequest extends ClientRequest implements Serializable {
    
    private PlayerProtocol player;

    public AddPlayerRequest(PlayerProtocol player) {
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
        ge.addPlayer(p);
        gess.broadcastPlayerAdded(p, ct.getUUID());
    }
    
}
