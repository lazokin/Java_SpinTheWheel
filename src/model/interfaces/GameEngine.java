package model.interfaces;

import java.util.Collection;

/**
 * Assignment interface for SADI
 * 
 * @author Caspar Ryan
 * 
 */
public interface GameEngine {
    /**
     * spin the gaming wheel progressing from the initialDelay to the finalDelay
     * in increments of delayIncrement delays are in milliseconds (ms)
     * 
     * 1. begin by selecting a random starting number on the wheel (between 1
     * and wheelSize) 2. start at initialDelay then increment the delay each
     * time a new number is passed on the wheel 3. call
     * SpinCallback.nextNumber(...) each time a number is passed continue until
     * delay >= finalDelay 4. call SpinCallback.result(...) to finish and
     * process result
     * 
     * @param wheelSize
     *            the size of the wheel from 1 .. wheelSize
     * @param initialDelay
     *            the starting delay in ms between numbers when the wheel starts
     * @param finalDelay
     *            the final delay in ms between numbers when the wheel stops
     *            (usually 0)
     * @param delayIncrement
     *            how much the wheel slows down after each number is passed
     * @param callback
     * @see model.interfaces.WheelCallback
     * 
     */
    public abstract void spin(int wheelSize, int initialDelay, int finalDelay,
            int delayIncrement, WheelCallback callback);

    public abstract void addPlayer(Player player);

    public abstract boolean removePlayer(Player player);

    /**
     * Player places abet for a number on the wheel
     * 
     * @param player
     * @see model.interfaces.Player
     * @param numberPick
     *            the number they picked
     * @param bet
     *            how many points to bet
     * @return true if the bet is placed false if the player does not have
     *         enough points
     */
    public abstract boolean placeBet(Player player, Integer numberPick, int bet);

    /**
     * engine goes through all players and applies win or loss to points
     * 
     * @param result
     *            the winning number from the wheel
     */
    public abstract void calculateResult(int result);

    /**
     * 
     * @return an unmodifiable collection of all Players
     * @see model.interfaces.Player
     */
    public abstract Collection<Player> getAllPlayers();

}