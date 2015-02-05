// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package protocol.client;

import java.io.Serializable;
import java.util.Collection;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import protocol.PlayerProtocol;
import protocol.Protocol;
import application.server.ClientThread;
import application.server.GameEngineServerStub;

@SuppressWarnings("serial")
public class PlaceBetRequest extends ClientRequest implements Serializable {
    
    private PlayerProtocol player;

    public PlaceBetRequest(PlayerProtocol player) {
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
        Integer numberPick = p.getNumberPick();
        int bet = p.getBet();
        Player serverPlayer = getPlayerFromGameEngine(ge, p.getPlayerId());
        ge.placeBet(serverPlayer, numberPick, bet);
        gess.broadcastBetPlaced(serverPlayer);
    }
    
    private Player getPlayerFromGameEngine(GameEngine gameEngine, String playerId) {
        Player result = null;
        Collection<Player> allPlayers = gameEngine.getAllPlayers();
        for (Player p : allPlayers) {
            if (p.getPlayerId().equals(playerId)) {
                result = p;
            }
        }
        return result;
    }
   
}
