/**
 * Class for storing/loading/saving playtime statistics.
 * @author 	Terry
 */
public class PlaytimeData {
	private int playSessions;
	private long totalPlaytime;
	
	public int getPlaySessions() {
		return this.playSessions;
	}
	public long getTotalPlaytime() {
		return this.totalPlaytime;
	}
	public void setPlaySessions(int i) {
		this.playSessions=i;
	}
	public void setTotalPlaytime(long l) {
		this.totalPlaytime=l;
	}
}
