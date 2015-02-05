// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package application.client;

import java.util.Collection;

import model.interfaces.Player;

public interface ClientStubListener {

    void notifyPlayerAdded(Collection<Player> serverSidePlayers,
        Collection<Player> clientSidePlayers, Player player);

    void notifyPlayerRemoved(Collection<Player> serverSidePlayers,
        Collection<Player> clientSidePlayers, Player player);

    void notifyBetPlaced(Collection<Player> players, Player player);

    void notifyNextNumber(int nextNumber);

    void notifySpinComplete(Collection<Player> allPlayers,
        Collection<Player> spinPlayers, int result, int wheelSize);
    
    void notifyServerDisconnected();
    
    void notifyPlayersReturned(Collection<Player> players);

}
