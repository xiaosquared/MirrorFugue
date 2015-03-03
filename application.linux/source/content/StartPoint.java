package content;

/**
 * 
 * @author xx
 *	
 * To keep track of points in each video to start from
 *
 */

public class StartPoint {
	private String name;
	private float time;		/** in seconds */
	
	public StartPoint(String name, float time) {
		this.name = name;
		this.time = time;
	}
	
	public StartPoint(String name, int min, int sec) {
		this.name = name;
		this.time = (float) (60*min + sec);
	}
	
	public String getName() {
		return name;
	}
	
	public float getTime() {
		return time;
	}
}
