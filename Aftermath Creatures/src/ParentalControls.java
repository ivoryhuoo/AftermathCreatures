/**
 * Class for storing/loading/saving parental controls screentime restrictions.
 * @author 	Terry
 */
public class ParentalControls {
	private int startHr;
	private int startMin;
	private int endHr;
	private int endMin;
	
	/**
	 * Getter for screentime start hour
	 * @return startHr screentime start hour value
	 */
	public int getStartHr() {
		return startHr;
	}
	
	/**
	 * Setter for screentime start hour
	 * @param startHr
	 */
	public void setStartHr(int startHr) {
		this.startHr = startHr;
	}
	
	/**
	 * Getter for screentime start minute
	 * @return startMin screentime start minute value
	 */
	public int getStartMin() {
		return startMin;
	}
	
	/**
	 * Setter for screentime start min
	 * @param startMin
	 */
	public void setStartMin(int startMin) {
		this.startMin = startMin;
	}
	
	/**
	 * Getter for screentime end hour
	 * @return endHr screentime end hour value
	 */
	public int getEndHr() {
		return endHr;
	}
	
	/**
	 * Setter for screentime end hour
	 * @param endHr
	 */
	public void setEndHr(int endHr) {
		this.endHr = endHr;
	}
	
	/**
	 * Getter for screentime end minute
	 * @return endMin screentime end minute value
	 */
	public int getEndMin() {
		return endMin;
	}
	
	/**
	 * Setter for screentime end minute
	 * @param endMin
	 */
	public void setEndMin(int endMin) {
		this.endMin = endMin;
	}
}
