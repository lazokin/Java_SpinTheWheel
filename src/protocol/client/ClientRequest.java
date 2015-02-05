// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package protocol.client;

import java.io.Serializable;

import model.interfaces.GameEngine;
import application.server.ClientThread;
import application.server.GameEngineServerStub;

@SuppressWarnings("serial")
public abstract class ClientRequest implements Serializable {
    
    public ClientRequest() {
        super();
    }
    
    public abstract void executeCommand(GameEngine ge, GameEngineServerStub gess,
        ClientThread ct);

}
