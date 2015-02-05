// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package protocol.server;

import java.io.Serializable;

import application.client.GameEngineClientStub;

@SuppressWarnings("serial")
public abstract class ServerNotification implements Serializable {
    
    public ServerNotification() {
        super();
    }
    
    public abstract void executeNotification(GameEngineClientStub gecs);

}
