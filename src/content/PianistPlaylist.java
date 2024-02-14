package content;

import java.util.ArrayList;

public class PianistPlaylist {
	private ArrayList<Performance> pieces;
	private int counter = 0;
	private int total = 0;
	
	public PianistPlaylist() {
		pieces = new ArrayList<Performance>();
	}
	
	public void addPerformance(Performance p) {
		pieces.add(p);
		total++;
	}
	
	public boolean atEnd() {
		return counter == total;
	}
	
	public Performance getNextPerformance() {
		if (total == 1)
			return pieces.get(0);
		else { 
			Performance p = pieces.get(counter);
			counter++;
//			if (counter == total)
//				counter = 0;
			return p;
		}
	}
}
