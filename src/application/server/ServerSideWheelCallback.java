// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package application.server;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.WheelCallback;

public class ServerSideWheelCallback implements WheelCallback {

    private GameEngineServerStub gameEngineServerStub;
    
    public ServerSideWheelCallback(GameEngineServerStub gameEngineServerStub) {
        this.gameEngineServerStub = gameEngineServerStub;
    }

    @Override
    public void nextNumber(int nextNumber, GameEngine engine) {
        gameEngineServerStub.broadcastNextNumber(nextNumber);
    }

    @Override
    public void result(int result, GameEngine engine) {
        engine.calculateResult(result);
        gameEngineServerStub.broadcastResults(engine.getAllPlayers(),
            ((GameEngineImpl) engine).getSpinPlayers(), result,
            ((GameEngineImpl) engine).getLastWheelSize());
    }

}
