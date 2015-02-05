// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package protocol;

import java.io.Serializable;

public class PlayerProtocol implements Serializable {
    
    private static final long serialVersionUID = -2205112020686237864L;
    
    private String playerId;
    private String playerName;
    private int points;
    private int bet;
    private Integer numberPick;

    public PlayerProtocol(String playerId, String playerName, int points,
        int bet, Integer numberPick) {
        super();
        this.playerId = playerId;
        this.playerName = playerName;
        this.points = points;
        this.bet = bet;
        this.numberPick = numberPick;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPoints() {
        return points;
    }

    public int getBet() {
        return bet;
    }

    public Integer getNumberPick() {
        return numberPick;
    }

}
