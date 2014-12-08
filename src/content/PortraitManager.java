package content;

import java.util.HashMap;
import java.util.Iterator;

import mf.MirrorFugue;

import codeanticode.gsvideo.GSMovie;

import processing.core.PApplet;

public class PortraitManager {
	static HashMap<String, Portrait> portraits;
	
	public static boolean transitionedPlayer = false;
	public static boolean playerReadied = false;
	
	static int pX, pY, pWidth;
	
	public static void initPortraits(MirrorFugue parent) {
		portraits = new HashMap<String, Portrait>();
				
		pWidth = 426;	
		pX = 1024 + pWidth*2;
		pY = 68;

		portraits.put("AT", new Portrait("AT", 	new GSMovie(parent, "nola/Portraits/240 AT init.mov"),
												new GSMovie(parent, "nola/Portraits/240 AT sitting.mov"), 
												new GSMovie(parent, "nola/Portraits/240 AT exit.mov"), 945+1024, pY));
		portraits.put("JC", new Portrait("JC", 	new GSMovie(parent, "nola/Portraits/240 JC init.mov"), 
												new GSMovie(parent, "nola/Portraits/240 JC sitting.mov"), 
												new GSMovie(parent, "nola/Portraits/240 JC exit.mov"), 1374+1024, pY));
		portraits.put("RM", new Portrait("RM", 	new GSMovie(parent, "nola/Portraits/240 RM init.mov"), 
												new GSMovie(parent, "nola/Portraits/240 RM init.mov"), 
												new GSMovie(parent, "nola/Portraits/240 RM exit.mov"), 1800+1024, pY));
		portraits.put("NS", new Portrait("NS", 	new GSMovie(parent, "nola/Portraits/240 NS init.mov"), 
												new GSMovie(parent, "nola/Portraits/240 NS sitting.mov"), 
												new GSMovie(parent, "nola/Portraits/240 NS exit.mov"), 2225+1024, pY));

		System.out.println("Done loading portraits!");
	}
	
	public static void drawPortraits(PApplet parent) {
		Iterator<Portrait> it = portraits.values().iterator();
	    while (it.hasNext()) {
	        it.next().draw(parent);
	    }

	}
	
	public static void startPortraits() {
		portraits.get("AT").playSittingInit();
		portraits.get("JC").playSittingInit();
		portraits.get("RM").playSittingInit();
		portraits.get("NS").playSittingInit();
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
