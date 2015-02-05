// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package protocol.server;

import java.io.Serializable;

import application.client.GameEngineClientStub;


@SuppressWarnings("serial")
public class NextNumberNotification extends ServerNotification
    implements Serializable {
    
    private int nextNumber;

    public NextNumberNotification(int nextNumber) {
        super();
        this.nextNumber = nextNumber;
    }

    public int getNextNumber() {
        return nextNumber;
    }

    @Override
    public void executeNotification(GameEngineClientStub gecs) {
        gecs.notifyNextNumber(nextNumber);
    }

}
