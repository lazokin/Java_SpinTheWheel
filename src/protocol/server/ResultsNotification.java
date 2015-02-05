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
public class ResultsNotification extends ServerNotification
    implements Serializable {
    
    private PlayerProtocol[] allPlayers;
    private PlayerProtocol[] spinPlayers;
    private int result;
    private int wheelSize;

    public ResultsNotification(PlayerProtocol[] allPlayers,
        PlayerProtocol[] spinPlayers, int result, int wheelSize) {
        super();
        this.allPlayers = allPlayers;
        this.spinPlayers = spinPlayers;
        this.result = result;
        this.wheelSize = wheelSize;
    }

    public PlayerProtocol[] getAllPlayers() {
        return allPlayers;
    }

    public PlayerProtocol[] getSpinPlayers() {
        return spinPlayers;
    }

    public int getResult() {
        return result;
    }

    public int getWheelSize() {
        return wheelSize;
    }

    @Override
    public void executeNotification(GameEngineClientStub gecs) {
        Collection<Player> aps = Protocol.deserializePlayers(allPlayers);
        Collection<Player> sps = Protocol.deserializePlayers(spinPlayers);
        gecs.updateServerSidePlayers(aps);
        gecs.updateClientSidePlayers();
        gecs.notifySpinComplete(aps, sps, result, wheelSize);
    }

}
