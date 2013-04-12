package content;

import java.util.HashMap;
import java.util.Random;

import codeanticode.gsvideo.GSMovie;

import processing.core.PApplet;
import processing.core.PGraphics;

public class PerformanceManager {
	static Performance currentPerformance;
	static HashMap<String, Performance> allPerformances;
	static HashMap<Integer, String> keyMappings;
	
	static final int midi_delay = 400;
	
	public static void initPerformances(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		allPerformances = new HashMap<String, Performance>();
		
		initXX(parent, plane_0, plane_1, plane_2);
		initMarvin(parent, plane_0, plane_1, plane_2);
		initDonal(parent, plane_0, plane_1, plane_2);
		initAlisa(parent, plane_0, plane_1, plane_2);
		currentPerformance = allPerformances.get("classical dance");

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
	
	/** Load performances by me! */
	private static void initXX(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		String playerFullName = "Xiao Xiao";
		allPerformances.put("satie", createPerformance(parent, "xx", "Satie_long", playerFullName, "Gnosienne No. 1, Erik Satie", 
				plane_0, plane_1, plane_2));
//		allPerformances.put("chopin_all", createPerformance(parent, "xx", "chopin_all", playerFullName, "�tude Op. 25 No. 7, Fr�d�ric Chopin", 
//				plane_0, plane_1, plane_2));
//		allPerformances.put("ravel", createPerformance(parent, "xx", "Ravel", playerFullName, "Ravel Sonatine Mvt. 2",
//				plane_0, plane_1, plane_2));
//		allPerformances.put("improv", createPerformance(parent, "xx", "Improv", playerFullName, "Improvisation",
//				plane_0, plane_1, plane_2));
//		allPerformances.put("gershwin", createPerformance(parent, "xx", "Gershwin", playerFullName, "Prelude No. 3",
//				plane_0, plane_1, plane_2));
//		allPerformances.put("chopin_g", createPerformance(parent, "xx", "Chopin", playerFullName, "Prelude in G minor",
//				plane_0, plane_1, plane_2));
//		allPerformances.put("bach_invention", createPerformance(parent, "xx", "Bach", playerFullName, "Invention No. 14",
//				plane_0, plane_1, plane_2));
	}
	
	/** Load performances by Donal */
	private static void initDonal(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		String playerFullName = "Donal Fox";
		allPerformances.put("ugly beauty", createPerformance(parent, "donal", "ub", playerFullName, "Ugly Beauty",
				plane_0, plane_1, plane_2));
		allPerformances.put("donal prelude", createPerformance(parent, "donal", "prelude", playerFullName, "Prelude",
				plane_0, plane_1, plane_2));
		allPerformances.put("autumn leaves full", createPerformance(parent, "donal", "autumn", playerFullName, "Autumn Leaves",
				plane_0, plane_1, plane_2));
		allPerformances.put("autumn leaves tango", createPerformance(parent, "donal", "autumn2", playerFullName, "Autumn Leaves Tango",
				plane_0, plane_1, plane_2));
		allPerformances.put("lyrical", createPerformance(parent, "donal", "lyrical", playerFullName, "Lyrical",
				plane_0, plane_1, plane_2));
	}
	
	/** Load Marvin */
	private static void initMarvin(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		allPerformances.put("marvin", createPerformance(parent, "marvin", "marvin", "Marvin Minsky", "Improvised Fugue",
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
	
	
	
	public static void initKeyMappings() {
		keyMappings = new HashMap<Integer, String>();
		keyMappings.put(new Integer(103), "satie"); 	// 7
		keyMappings.put(new Integer(104), "marvin"); // 8
		keyMappings.put(new Integer(105), "twinkle"); // 9
	}
	
	public static boolean handleKeyPress(int key) {
		String song = keyMappings.get(new Integer(key));
		if (song != null) {
			System.out.println(song);
			currentPerformance = allPerformances.get(song);
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
	
	public static void pauseCurrentPerformance() {
		currentPerformance.pause();
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
	}

}
