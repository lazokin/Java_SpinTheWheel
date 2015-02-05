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
public class PlayerRemovedNotification extends ServerNotification
    implements Serializable {
    
    private PlayerProtocol player;
    private PlayerProtocol[] players;

    public PlayerRemovedNotification(PlayerProtocol[] players,
        PlayerProtocol player) {
        super();
        this.player = player;
        this.players = players;
    }

    public PlayerProtocol getPlayer() {
        return player;
    }

    public PlayerProtocol[] getPlayers() {
        return players;
    }

    @Override
    public void executeNotification(GameEngineClientStub gecs) {
        Player p = Protocol.deserializePlayer(player);
        Collection<Player> ps = Protocol.deserializePlayers(players);
        gecs.updateServerSidePlayers(ps);
        gecs.removeClientSidePlayer(p);
        gecs.notifyPlayerRemoved(ps, gecs.getClientPlayers(), p);
    }

}
