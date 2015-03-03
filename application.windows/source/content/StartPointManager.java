package content;

import java.util.ArrayList;

public class StartPointManager {
	private StartPoint s1 = new StartPoint("Frankie and Johnny 1", 2, 42);
	private StartPoint s2 = new StartPoint("Professor Longhair Pattern", 3, 56);
	private StartPoint s3 = new StartPoint("Professor Longhair Pattern", 4, 43);
	
	private StartPoint s4 = new StartPoint("Blueberry Hill", 21, 10);
	private StartPoint s5 = new StartPoint("Blueberry Hill", 23, 50);
	
	ArrayList<StartPoint> starts;
	
	int current = -1;
	
	public StartPointManager() {
		starts = new ArrayList<StartPoint>();
		starts.add(s1);
		starts.add(s2);
		starts.add(s3);
		starts.add(s4);
		starts.add(s5);
	}
	
	public StartPoint getNext() {
		if (current < 4)
			return starts.get(current++);
		else
			current = 0;
			return starts.get(0);
	}
	
	public StartPoint getPrev() {
		if (current > 0)
			return starts.get(current--);
		else 
			current = 4;
			return starts.get(4);
	}
}
