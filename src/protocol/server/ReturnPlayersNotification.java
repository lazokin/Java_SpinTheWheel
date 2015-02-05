// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package protocol.server;

import java.io.Serializable;
import java.util.Collection;

import model.interfaces.Player;
import protocol.PlayerProtocol;
import protocol.Protocol;
import application.client.GameEngineClientStub;

@SuppressWarnings("serial")
public class ReturnPlayersNotification extends ServerNotification
    implements Serializable {
    
    private PlayerProtocol[] players;

    public ReturnPlayersNotification(PlayerProtocol[] players) {
        super();
        this.players = players;
    }

    public PlayerProtocol[] getPlayers() {
        return players;
    }

    @Override
    public void executeNotification(GameEngineClientStub gecs) {
        Collection<Player> ps = Protocol.deserializePlayers(players);
        gecs.updateServerSidePlayers(ps);
        gecs.notifyPlayersReturned(ps);
    }

}
