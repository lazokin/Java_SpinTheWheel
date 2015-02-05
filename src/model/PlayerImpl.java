// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package model;

import model.interfaces.Player;

public class PlayerImpl implements Player {
    
    private String playerId;
    private String playerName;
    private int points;
    private int bet;
    private Integer numberPick;
    
    public PlayerImpl() {
        super();
    }

    public PlayerImpl(String playerId, String playerName, int points, int bet,
        Integer numberPick) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.points = points;
        this.bet = bet;
        this.numberPick = numberPick;
    }

    @Override
    public String getPlayerId() {
        return playerId;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public int getBet() {
        return bet;
    }

    @Override
    public int getNumberPick() {
        return numberPick;
    }

    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean placeBet(Integer numberPick, int bet) {
        boolean result = false;
        if (bet >= 0 && bet <= points) {
            this.bet = bet;
            this.numberPick = numberPick;
            result = true;
        }
        return result;
    }

    @Override
    public String toString() {
        return "PlayerId:" + playerId + "|" + "Name:" + playerName + "|"
                + "Bet:" + bet + "|" + "NumberPick:" + numberPick + "|"
                + "Points:" + points;
    }

}
