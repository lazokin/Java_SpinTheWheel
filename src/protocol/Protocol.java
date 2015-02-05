// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package protocol;

import java.util.ArrayList;
import java.util.Collection;

import model.PlayerImpl;
import model.interfaces.Player;

public class Protocol {

    public Protocol() {
        super();
    }
    
    public static PlayerProtocol serializePlayer (Player player) {
        return new PlayerProtocol(player.getPlayerId(), player.getPlayerName(),
            player.getPoints(), player.getBet(), player.getNumberPick());
    }
    
    public static PlayerProtocol serializePlayer (Player player, int bet,
        Integer numberPick) {
        return new PlayerProtocol(player.getPlayerId(), player.getPlayerName(),
            player.getPoints(), bet, numberPick);
    }
    
    public static Player deserializePlayer (PlayerProtocol player) {
        return new PlayerImpl(player.getPlayerId(), player.getPlayerName(),
            player.getPoints(), player.getBet(), player.getNumberPick());
    }
    
    public static PlayerProtocol[] serializePlayers (Collection<Player> players) {
        PlayerProtocol[] result = new PlayerProtocol[players.size()];
        int idx = 0;
        for (Player p : players) {
            result[idx++] = new PlayerProtocol(p.getPlayerId(),
                p.getPlayerName(), p.getPoints(), p.getBet(), p.getNumberPick());
        }
        return result;
    }
    
    public static Collection<Player> deserializePlayers (PlayerProtocol[] players) {
        ArrayList<Player> result = new ArrayList<Player>();
        for (PlayerProtocol p : players) {
            result.add(new PlayerImpl(p.getPlayerId(), p.getPlayerName(),
                p.getPoints(), p.getBet(), p.getNumberPick()));
        }
        return result;
    }

}
