package content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import codeanticode.gsvideo.GSMovie;
import processing.core.PApplet;
import processing.core.PGraphics;

public class PerformanceManager {
	static Performance currentPerformance;
	static HashMap<String, Performance> allPerformances;
	static HashMap<Integer, String> keyMappings;
	static ArrayList<Performance> partitaRecital;
	static int partitaIndex = 0;
	
	static final int midi_delay = 400;
	
	public static void initPerformances(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		allPerformances = new HashMap<String, Performance>();
		
		initNOLA(parent, plane_0, plane_1, plane_2);
//		initXX(parent, plane_0, plane_1, plane_2);
//		initMarvin(parent, plane_0, plane_1, plane_2);
//		initDonal(parent, plane_0, plane_1, plane_2);
//		initVijay(parent, plane_0, plane_1, plane_2);
//		initPrelude(parent, plane_0, plane_1, plane_2);
//		initTED(parent, plane_0, plane_1, plane_2);
		//initLEXUS(parent, plane_0, plane_1, plane_2);
		
		currentPerformance = allPerformances.get("AT");
		currentPerformance.loadMidi();

		initKeyMappings();
		
		PApplet.println("Done loading performances!");
	}
	
	private static Performance createPerformance(PApplet parent, String player, String song, 
												String playerFullName, String songFullName, 
												PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		String handsMovieFile = player + "/" + song + "_HANDS.mov";
		String faceMovieFile = player + "/" + song + "_FACE.mov";
		String midiFile = "data/" + player + "/" + song + ".MID";
		
		return new Performance(	songFullName, playerFullName,
								new GSMovie(parent, handsMovieFile), new GSMovie(parent, faceMovieFile), midiFile, 
								plane_0, plane_1, plane_2);
	}
	
	private static void initNOLA(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		String playerFullName = "AT";
		allPerformances.put("AT", createPerformance(parent, "nola", "AT2", playerFullName, "first piece",
				plane_0, plane_1, plane_2));
	}
	
	private static void initTED(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		String playerFullName = "TEDxBoston";
		allPerformances.put("prelude3", createPerformance(parent, "xx_special", "tedxprelude", playerFullName, "Prelude",
				plane_0, plane_1, plane_2));
		allPerformances.put("invention", createPerformance(parent, "xx_special", "invention", playerFullName, "Invention",
				plane_0, plane_1, plane_2));
		allPerformances.put("ugly beauty shortened", createPerformance(parent, "donal", "ub", playerFullName, "Ugly Beauty Shortened",
				plane_0, plane_1, plane_2));
		allPerformances.put("twinkle", createPerformance(parent, "alisa", "twinkle_short", playerFullName, "Twinkle Twinkle Little Star",
				plane_0, plane_1, plane_2));
		allPerformances.put("marvin", createPerformance(parent, "marvin", "marvin", "Marvin Minsky", "Improvised Fugue",
				plane_0, plane_1, plane_2));
		allPerformances.put("finale2", createPerformance(parent, "donal", "finale2", playerFullName, "Final arrangement of Prelude",
				plane_0, plane_1, plane_2));
		allPerformances.put("ryuichi", createPerformance(parent, "ryuichi", "colors", playerFullName, "colors",
				plane_0, plane_1, plane_2));
		allPerformances.put("ryuichi_improv", createPerformance(parent, "ryuichi", "improv", playerFullName, "improv",
				plane_0, plane_1, plane_2));
	}
	
	private static void initLEXUS(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		String playerFullName = "Partita Recital";
		allPerformances.put("1_prelude", createPerformance(parent, "lexus", "1_prelude", playerFullName, "Prelude",
				plane_0, plane_1, plane_2));
		allPerformances.put("2_allemande", createPerformance(parent, "lexus", "2_allemande", playerFullName, "Prelude",
				plane_0, plane_1, plane_2));
		allPerformances.put("3_courante", createPerformance(parent, "lexus", "3_courante", playerFullName, "Prelude",
				plane_0, plane_1, plane_2));
		allPerformances.put("4_sarabande", createPerformance(parent, "lexus", "4_sarabande", playerFullName, "Prelude",
				plane_0, plane_1, plane_2));
		allPerformances.put("5_menuet", createPerformance(parent, "lexus", "5_menuet", playerFullName, "Prelude",
				plane_0, plane_1, plane_2));
		allPerformances.put("6_gigue", createPerformance(parent, "lexus", "6_gigue", playerFullName, "Prelude",
				plane_0, plane_1, plane_2));
		
		partitaRecital = new ArrayList<Performance>();
		partitaRecital.add(allPerformances.get("1_prelude"));
		partitaRecital.add(allPerformances.get("2_allemande"));
		partitaRecital.add(allPerformances.get("3_courante"));
		partitaRecital.add(allPerformances.get("4_sarabande"));
		partitaRecital.add(allPerformances.get("5_menuet"));
		partitaRecital.add(allPerformances.get("6_gigue"));
	}
	
	/** Load performances by me! */
	private static void initXX(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		String playerFullName = "Xiao Xiao";
		allPerformances.put("satie", createPerformance(parent, "xx", "satie_recolored2013", playerFullName, "Gnosienne No. 1, Erik Satie", 
				plane_0, plane_1, plane_2));
		allPerformances.put("5", createPerformance(parent, "xx", "bagatelle5", playerFullName, "Bagatelle #5, Beethoven", 
				plane_0, plane_1, plane_2));
//		allPerformances.put("1", createPerformance(parent, "xx", "bagatelle1", playerFullName, "Bagatelle #1, Beethoven", 
//				plane_0, plane_1, plane_2));
//		allPerformances.put("chopin_all", createPerformance(parent, "xx", "chopin_all", playerFullName, "Étude Op. 25 No. 7, Frédéric Chopin", 
//				plane_0, plane_1, plane_2));
		allPerformances.put("ravel", createPerformance(parent, "xx", "Ravel", playerFullName, "Ravel Sonatine Mvt. 2",
				plane_0, plane_1, plane_2));
//		allPerformances.put("improv", createPerformance(parent, "xx", "Improv", playerFullName, "Improvisation",
//				plane_0, plane_1, plane_2));
//		allPerformances.put("gershwin", createPerformance(parent, "xx", "Gershwin", playerFullName, "Prelude No. 3",
//				plane_0, plane_1, plane_2));
//		allPerformances.put("chopin_g", createPerformance(parent, "xx", "Chopin", playerFullName, "Prelude in G minor",
//				plane_0, plane_1, plane_2));
//		allPerformances.put("bach_invention", createPerformance(parent, "xx", "Bach", playerFullName, "Invention No. 14",
//				plane_0, plane_1, plane_2));
	}
	
	private static void initVijay(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		String playerFullName = "Vijay Iyer";
		allPerformances.put("vijay", createPerformance(parent, "vijay", "vijay", playerFullName, "full piece", 
				plane_0, plane_1, plane_2));
	}
	
	/** Load performances by Donal */
	private static void initDonal(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		String playerFullName = "Donal Fox";
		allPerformances.put("ugly beauty", createPerformance(parent, "donal", "ub", playerFullName, "Ugly Beauty",
				plane_0, plane_1, plane_2));
//		allPerformances.put("ugly beauty shortened", createPerformance(parent, "donal", "ub_short", playerFullName, "Ugly Beauty Shortened",
//				plane_0, plane_1, plane_2));
		allPerformances.put("donal prelude", createPerformance(parent, "donal", "prelude", playerFullName, "Prelude",
				plane_0, plane_1, plane_2));
		allPerformances.put("autumn leaves full", createPerformance(parent, "donal", "autumn", playerFullName, "Autumn Leaves",
				plane_0, plane_1, plane_2));
//		allPerformances.put("autumn leaves tango", createPerformance(parent, "donal", "autumn2", playerFullName, "Autumn Leaves Tango",
//				plane_0, plane_1, plane_2));
		allPerformances.put("lyrical", createPerformance(parent, "donal", "lyrical", playerFullName, "Lyrical",
				plane_0, plane_1, plane_2));
		allPerformances.put("finale2", createPerformance(parent, "donal", "finale2", playerFullName, "Final arrangement of Prelude",
				plane_0, plane_1, plane_2));
	}
	
	/** Load Marvin */
	private static void initMarvin(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		allPerformances.put("marvin", createPerformance(parent, "marvin", "marvin_short", "Marvin Minsky", "Improvised Fugue",
				plane_0, plane_1, plane_2));
	}
	
	/** Load Alisa */
	private static void initAlisa(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		String playerFullName = "Alisa Ishii";
		allPerformances.put("classical dance", createPerformance(parent, "alisa", "dance", playerFullName, "Classical Dance",
				plane_0, plane_1, plane_2));
		allPerformances.put("twinkle", createPerformance(parent, "alisa", "twinkle", playerFullName, "Twinkle Twinkle Little Star",
				plane_0, plane_1, plane_2));
	}
	
	/** Load Partita */
	private static void initPartita(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		String playerFullName = "No Auto Play";
		allPerformances.put("prelude3", createPerformance(parent, "xx_special", "tedxprelude", playerFullName, "Prelude",
				plane_0, plane_1, plane_2));
	}
	
	private static void initPrelude(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		String playerFullName = "xx & hanoi";
		allPerformances.put("hanoi_all", createPerformance(parent, "bach_video", "hanoi_full", playerFullName, "full piece", 
				plane_0, plane_1, plane_2));
		allPerformances.put("xx_all", createPerformance(parent, "bach_video", "xx_full", playerFullName, "full piece", 
				plane_0, plane_1, plane_2));
		allPerformances.put("b_xx", createPerformance(parent, "bach_video", "b_xx", playerFullName, "start from B", 
				plane_0, plane_1, plane_2));
		allPerformances.put("b_hanoi", createPerformance(parent, "bach_video", "b_hanoi", playerFullName, "start from B", 
				plane_0, plane_1, plane_2));
		allPerformances.put("end_xx", createPerformance(parent, "bach_video", "end_xx", playerFullName, "last part", 
				plane_0, plane_1, plane_2));
		allPerformances.put("end_hanoi", createPerformance(parent, "bach_video", "end_hanoi", playerFullName, "last part", 
				plane_0, plane_1, plane_2));
		allPerformances.put("together", createPerformance(parent, "bach_video", "together", playerFullName, "beatbox playing together", 
				plane_0, plane_1, plane_2));
		allPerformances.put("gene_xx", createPerformance(parent, "bach_video", "gene_xx", playerFullName, "beatbox xx", 
				plane_0, plane_1, plane_2));
		allPerformances.put("gene_hanoi", createPerformance(parent, "bach_video", "gene_hanoi", playerFullName, "beatbox hanoi", 
				plane_0, plane_1, plane_2));
	}
	
	/** Load nick joliat */
	private static void initNick(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		String playerFullName = "Nick Joliat";
		allPerformances.put("waltz", createPerformance(parent, "njoliat", "waltz", playerFullName, "Chopin waltz(?)",
				plane_0, plane_1, plane_2));
		allPerformances.put("slow piece", createPerformance(parent, "njoliat", "slow", playerFullName, "Slow Piece",
				plane_0, plane_1, plane_2));
		allPerformances.put("lizst", createPerformance(parent, "njoliat", "lizst", playerFullName, "Lizst etude(?)",
				plane_0, plane_1, plane_2));
	}
	private static void initPeter(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		allPerformances.put("blues", createPerformance(parent, "peter", "blues", "Peter Godart", "blues",
				plane_0, plane_1, plane_2));
//		allPerformances.put("bebop", createPerformance(parent, "peter", "bebop", "Peter Godart", "bebop",
//				plane_0, plane_1, plane_2));
	}
	private static void initJoe(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		allPerformances.put("joep", createPerformance(parent, "joep", "joep", "No Auto Play", "Improv scales",
				plane_0, plane_1, plane_2));
	}
	
	public static void initKeyMappings() {
//		keyMappings = new HashMap<Integer, String>();
//		keyMappings.put(new Integer(105), "end_hanoi"); 	// 9
//		keyMappings.put(new Integer(104), "end_xx"); // 8
//		keyMappings.put(new Integer(103), "gene_hanoi"); // 7
//	
//		keyMappings.put(new Integer(102), "gene_hanoi"); // 6
//		keyMappings.put(new Integer(101), "together"); // 5
//		keyMappings.put(new Integer(100), "b_hanoi"); // 4		
//
//		keyMappings.put(new Integer(99), "b_xx"); // 3
//		keyMappings.put(new Integer(98), "xx_all"); // 2
//		keyMappings.put(new Integer(97), "hanoi_all"); // 1
		
		keyMappings = new HashMap<Integer, String>();
		keyMappings.put(new Integer(99), "lyrical"); // 3
		keyMappings.put(new Integer(98), "autumn leaves full"); // 2
		keyMappings.put(new Integer(97), "ugly beauty"); // 1
		
		keyMappings.put(new Integer(103), "ryuichi_improv"); 	// 7
		keyMappings.put(new Integer(104), "twinkle"); // 8
		keyMappings.put(new Integer(105), "marvin"); // 9
	
		keyMappings.put(new Integer(100), "satie"); // 4	
		keyMappings.put(new Integer(101), "ryuichi"); // 5
		keyMappings.put(new Integer(102), "vijay"); // 6

		
		keyMappings.put(new Integer(66), "invention"); // 4
		keyMappings.put(new Integer(96), "finale2"); // finale
		
		keyMappings.put(new Integer(49), "satie"); 
		keyMappings.put(new Integer(50), "2_allemande");
		keyMappings.put(new Integer(51), "3_courante");
		keyMappings.put(new Integer(52), "4_sarabande");
		keyMappings.put(new Integer(53), "5_menuet");
		keyMappings.put(new Integer(54), "6_gigue");
		
	}
	
	public static boolean handleKeyPress(int key) {
		String song = keyMappings.get(new Integer(key));
		if (song != null) {
			System.out.println(song);
			currentPerformance.stop(); // stop and reset current performance
			currentPerformance = allPerformances.get(song);
			
			// if picked one of the partita pieces, set index
			int num = key - 49;
			if ((num >=0) && (num < 6))
				partitaIndex = num;
			
			return true;			// if a song was chosen, return true
		}
		return false;		// else return false
	}
	
	
	public static void playCurrentPerformance(PApplet parent, Boolean bPlayMidi) {
		
		currentPerformance.loadMidi();				// preload the midi first
		
		currentPerformance.play(bPlayMidi);			// start performance and wait for videos to load 
		while(!currentPerformance.ready()) {
			parent.delay(10);
		}
		currentPerformance.pause();					// after videos loaded, go back to beginning
		currentPerformance.rewind();
		
		if (bPlayMidi) {							// then start both midi and video
			currentPerformance.playMidi();
			parent.delay(midi_delay);
		}
		
		currentPerformance.play(bPlayMidi);
	}
	
	public static boolean startNextPerformance() {
		if (currentPerformance.player.equals("Partita Recital"))
			return true;
		return false;
	}
	
	public static void pauseCurrentPerformance() {
		currentPerformance.pause();
	}
	
	public static void clearMidi() {
		currentPerformance.pauseMidi();
	}
	
	public static boolean isCurrentlyPlaying() {
		return currentPerformance.isPlaying();
	}
	
	public static boolean isPaused() {
		return currentPerformance.isPaused();
	}
	
	public static boolean isEnded() {
		return currentPerformance.isEnded();
	}
	
	public static Performance getCurrentPerformance() {
		return currentPerformance;
	}
	
	/**
	 * picks a random performance and sets it as currentPerformance
	 */
	public static void setRandomPerformance() {
		Random generator = new Random();
		Object[] values = allPerformances.values().toArray();
		Object randomValue = values[generator.nextInt(values.length)];
		
		currentPerformance = (Performance) randomValue;
		if (currentPerformance.player.equals("No Auto Play"))
			setRandomPerformance();
	}

	public static void setNextPerformance() {
		partitaIndex++;
		if (partitaIndex == partitaRecital.size())
			partitaIndex = 0;
		currentPerformance = partitaRecital.get(partitaIndex);
	}
}
