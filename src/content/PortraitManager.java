package content;

import java.util.HashMap;
import java.util.Iterator;

import codeanticode.gsvideo.GSMovie;

import processing.core.PApplet;

public class PortraitManager {
	static HashMap<String, Portrait> portraits;
	
	public static boolean transitionedPlayer = false;
	public static boolean playerReadied = false;
	
	public static void initPortraits(PApplet parent) {
		portraits = new HashMap<String, Portrait>();
		
		portraits.put("AT", new Portrait("AT", new GSMovie(parent, "nola/Portraits/Toussaint sitting.mov"), new GSMovie(parent, "nola/Portraits/Toussaint exit.mov"), 1200, 200));
		portraits.put("JC", new Portrait("JC", new GSMovie(parent, "nola/Portraits/Cleary sitting.mov"), new GSMovie(parent, "nola/Portraits/Cleary exit.mov"), 1600, 200));
		
		System.out.println("Done loading portraits!");
	}
	
	public static void drawPortraits(PApplet parent) {
		Iterator<Portrait> it = portraits.values().iterator();
	    while (it.hasNext()) {
	        it.next().draw(parent);
	    }
	}
	
	public static void startPortraits() {
		portraits.get("JC").playSitting();
	}
	
	public static void transitionPlayer() {
		// start sitting sequence of player who just finished
		portraits.get(PerformanceManager.getCurrentPerformance().player).playSitting();
		
		// start the exit sequence of next player
		portraits.get(PerformanceManager.getNextPerformance().player).playExit();
		
		transitionedPlayer = true;
		playerReadied = false;
	}
	
	public static boolean nextPlayerReady() {
		if (portraits.get(PerformanceManager.getCurrentPerformance().player).finishedExit()) {
			playerReadied = true;
			transitionedPlayer = false;
			return true;
		} else
			return false;
	}
}
