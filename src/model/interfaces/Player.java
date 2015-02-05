package model.interfaces;

/**
 * Assignment interface for SADI
 * 
 * @author Caspar Ryan
 * 
 */
public interface Player
{
	public abstract String getPlayerName();

	public abstract void setPlayerName(String playerName);

	/**
	 * 
	 * @return number of points for betting (updated with each wins or loss)
	 */
	public abstract int getPoints();

	public abstract void setPoints(int points);

	public abstract String getPlayerId();

	/**
	 * 
	 * @param numberPick
	 *            - the number on the wheel the player is betting on
	 * @param bet
	 *            - the bet in points
	 * @return
	 */
	public abstract boolean placeBet(Integer numberPick, int bet);

	public abstract int getNumberPick();

	public abstract int getBet();

	/**
	 * 
	 * @return a human readable String that lists the values of this Player
	 *         instance
	 */
	public abstract String toString();
}
