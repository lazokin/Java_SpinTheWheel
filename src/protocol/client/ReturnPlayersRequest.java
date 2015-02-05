// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package protocol.client;

import java.io.Serializable;

import model.interfaces.GameEngine;
import protocol.PlayerProtocol;
import protocol.Protocol;
import protocol.server.ReturnPlayersNotification;
import application.server.ClientThread;
import application.server.GameEngineServerStub;

@SuppressWarnings("serial")
public class ReturnPlayersRequest extends ClientRequest implements Serializable {

    public ReturnPlayersRequest() {
        super();
    }

    @Override
    public void executeCommand(GameEngine ge, GameEngineServerStub gess,
        ClientThread ct) {
        PlayerProtocol[] serializedPlayers =
            Protocol.serializePlayers(ge.getAllPlayers());
        ReturnPlayersNotification returnPlayersNotification = 
            new ReturnPlayersNotification(serializedPlayers);
        ct.notifyReturnPlayers(returnPlayersNotification);
    }

}
