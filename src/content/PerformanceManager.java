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
	
	static ArrayList<Performance> playlist;
	static int playlist_index = 0;
	
	static final int midi_delay = 400;
	
	public static void initPerformances(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		allPerformances = new HashMap<String, Performance>();
		playlist = new ArrayList<Performance>();
		
		initNOLA(parent, plane_0, plane_1, plane_2);
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
		allPerformances.put("AT", createPerformance(parent, "nola", "AT2", "AT", "Second piece", 
				plane_0, plane_1, plane_2));
		allPerformances.put("JC", createPerformance(parent, "nola", "JC2", "JC", "Tipitina", 
				plane_0, plane_1, plane_2));
		allPerformances.put("RM", createPerformance(parent, "nola", "RM", "RM", "Groove", 
				plane_0, plane_1, plane_2));
		allPerformances.put("NS", createPerformance(parent, "nola", "NS2", "NS", "Simple", 
				plane_0, plane_1, plane_2));
		
		playlist.add(allPerformances.get("AT"));
		playlist.add(allPerformances.get("JC"));
		//playlist.add(allPerformances.get("RM"));
		//playlist.add(allPerformances.get("NS"));

		// for testing only
		allPerformances.put("satie", createPerformance(parent, "xx", "satie", "xiao xiao", "Gnosienne", 
				plane_0, plane_1, plane_2));
	}
		
	public static void initKeyMappings() {
		keyMappings = new HashMap<Integer, String>();

		keyMappings.put(new Integer(101), "satie"); // 5
		
		keyMappings.put(new Integer(100), "NS"); // 4
		keyMappings.put(new Integer(99), "RM"); // 3
		keyMappings.put(new Integer(98), "JC"); // 2
		keyMappings.put(new Integer(97), "AT"); // 1
		
	}
	
	public static boolean handleKeyPress(int key) {
		String song = keyMappings.get(new Integer(key));
		if (song != null) {
			System.out.println(song);
			currentPerformance.stop(); // stop and reset current performance
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
	
	public static boolean startNextPerformance() {
		if (currentPerformance.player.equals("No Auto Play"))
			return false;
		return true;
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
	
	public static Performance getNextPerformance() {
		return playlist.get((playlist_index + 1) % playlist.size());
	}
	
	public static void setNextPerformance() {
		playlist_index ++;
		if (playlist_index == playlist.size())
			playlist_index = 0;
		currentPerformance = playlist.get(playlist_index);	
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

}
