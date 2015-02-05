// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package application.server;

import model.interfaces.Player;

public interface ServerStubListener {
    
    void notifyServerStarted();
    
    void notifyClientConnected();
    
    void notifyClientDisconnected();

    void notifyPlayerAdded(Player player);

    void notifyPlayerRemoved(Player player);

    void notifyBetPlaced(Player player);

    void notifyWheelSpinning();

    void notifySpinComplete(int result);

}
