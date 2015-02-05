// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package model;

import java.util.Collection;
import java.util.TreeMap;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.WheelCallback;

public class GameEngineImpl implements GameEngine {

    public static final int MAX_PLAYERS = 6;

    private TreeMap<String, Player> allPlayers = new TreeMap<String, Player>();
    private TreeMap<String, Player> spinPlayers = new TreeMap<String, Player>();
    
    private int lastWheelSize;

    public GameEngineImpl() {
        super();
    }

    @Override
    public synchronized void spin(int wheelSize, int initialDelay, int finalDelay,
            int delayIncrement, WheelCallback callback) {

        // pre: wheelSize > 0
        // pre: initialDelay > 0
        // pre: finalDelay > initialDelay
        // pre: delayIncrement > 0
        // pre: callback != null
        assert (wheelSize > 0) : "Wheel size should be a positive number";
        assert (initialDelay > 0) : "Initial delay should be a positive number";
        assert (finalDelay > initialDelay) : "Final delay should be greater than initial delay";
        assert (delayIncrement > 0) : "Delay encrement should be a positive number";
        if (callback == null) {
            throw new IllegalArgumentException(
                    "Argument callback cannot be null");
        }

        this.lastWheelSize = wheelSize;
        this.assignSpinPlayers(wheelSize);

        int wheelDelay = initialDelay;

        // Star wheel at some random number between 1 and wheelSize
        int nextNumber = 1 + (int) (Math.random() * wheelSize);

        // Spin the wheel
        while (wheelDelay < finalDelay) {

            // Calculate next wheel number. Wheel numbers wrap after wheelSize.
            nextNumber++;
            if (nextNumber > wheelSize) {
                nextNumber = 1;
            }

            // Add delay between wheel numbers
            try {
                Thread.sleep(wheelDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Call nextNumber method of WheelCallback
            callback.nextNumber(nextNumber, this);

            // Increase delay to simulate wheel slowing down
            wheelDelay += delayIncrement;

        }

        // post: nextNumber > 0
        // post: nextNumber <= wheelSize
        assert (nextNumber > 0 && nextNumber <= wheelSize) : "Wheel stopped on invalid number";

        // Call result method of WheelCallback
        callback.result(nextNumber, this);

    }

    private void assignSpinPlayers(int wheelSize) {
        this.spinPlayers.clear();
        for (Player player : allPlayers.values()) {
            boolean hasPoints = (player.getPoints() > 0);
            boolean validBet = (player.getBet() > 0 && player.getBet() <= player
                    .getPoints());
            boolean validNumber = (player.getNumberPick() > 0 && player
                    .getNumberPick() <= wheelSize);
            if (hasPoints && validBet && validNumber) {
                spinPlayers.put(player.getPlayerId(), player);
            }
        }
    }

    @Override
    public synchronized void addPlayer(Player player) {
        if (!allPlayers.containsKey(player.getPlayerId()))
            allPlayers.put(player.getPlayerId(), player);
    }

    @Override
    public synchronized boolean removePlayer(Player player) {
        boolean result = false;
        if (allPlayers.containsKey(player.getPlayerId())) {
            allPlayers.remove(player.getPlayerId());
            result = true;
        }
        return result;

    }

    @Override
    public synchronized boolean placeBet(Player player, Integer numberPick, int bet) {
        if (player.placeBet(numberPick, bet)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public synchronized void calculateResult(int result) {
        for (Player player : spinPlayers.values()) {

            int playerPoints = player.getPoints();
            int playerCurrentBet = player.getBet();
            int playerCurrentNumberPick = player.getNumberPick();

            if (playerCurrentNumberPick == result) {
                player.setPoints(playerPoints + playerCurrentBet);
            } else {
                player.setPoints(playerPoints - playerCurrentBet);
            }

        }
    }

    @Override
    public synchronized Collection<Player> getAllPlayers() {
        return allPlayers.values();
    }

    public synchronized Collection<Player> getSpinPlayers() {
        return spinPlayers.values();
    }

    public int getLastWheelSize() {
        return lastWheelSize;
    }

}
