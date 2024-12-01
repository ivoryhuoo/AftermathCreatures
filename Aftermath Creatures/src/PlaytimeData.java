/**
 * Class for storing/loading/saving playtime statistics.
 * @author 	Terry
 */
public class PlaytimeData {
	private int playSessions;
	private long totalPlaytime;
	/**
	 * Getter for number of play sessions
	 * @return playSessions number of play sessions
	 */
	public int getPlaySessions() {
		return this.playSessions;
	}
	/**
	 * Getter for total playtime
	 * @return totalPlaytime total number of play sessions
	 */
	public long getTotalPlaytime() {
		return this.totalPlaytime;
	}
	/**
	 * Setter for number of play sessions
	 * <p>
	 * Sets playtime = i
	 * @param i
	 */
	public void setPlaySessions(int i) {
		this.playSessions=i;
	}
	/**
	 * Setter for total playtime
	 * <p>
	 * Sets playtime = l
	 * @param l
	 */
	public void setTotalPlaytime(long l) {
		this.totalPlaytime=l;
	}
}
