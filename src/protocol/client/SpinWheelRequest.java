// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package protocol.client;

import java.io.Serializable;

import model.interfaces.GameEngine;
import application.server.ClientThread;
import application.server.GameEngineServerStub;
import application.server.ServerSideWheelCallback;

@SuppressWarnings("serial")
public class SpinWheelRequest extends ClientRequest implements Serializable {
    
    private int wheelSize;
    private int initialDelay;
    private int finalDelay;
    private int delayIncrement;

    public SpinWheelRequest(int wheelSize, int initialDelay, int finalDelay,
        int delayIncrement) {
        super();
        this.wheelSize = wheelSize;
        this.initialDelay = initialDelay;
        this.finalDelay = finalDelay;
        this.delayIncrement = delayIncrement;
    }

    public int getWheelSize() {
        return wheelSize;
    }

    public int getInitialDelay() {
        return initialDelay;
    }

    public int getFinalDelay() {
        return finalDelay;
    }

    public int getDelayIncrement() {
        return delayIncrement;
    }

    @Override
    public void executeCommand(GameEngine ge, GameEngineServerStub gess,
        ClientThread ct) {
        gess.notifyWheelSpinning();
        ge.spin(wheelSize, initialDelay, finalDelay, delayIncrement,
            new ServerSideWheelCallback(gess));
    }

    

}
