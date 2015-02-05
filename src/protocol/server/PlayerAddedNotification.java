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
public class PlayerAddedNotification extends ServerNotification
    implements Serializable {
    
    private boolean clientPlayer;
    private PlayerProtocol player;
    private PlayerProtocol[] players;

    public PlayerAddedNotification(PlayerProtocol[] players,
        PlayerProtocol player, boolean clientPlayer) {
        super();
        this.clientPlayer = clientPlayer;
        this.player = player;
        this.players = players;
    }

    public boolean isClientPlayer() {
        return clientPlayer;
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
        if (clientPlayer) {
            gecs.addClientSidePlayer(p);   
        }
        gecs.notifyPlayerAdded(ps,gecs.getClientPlayers(), p);  
    }

}
