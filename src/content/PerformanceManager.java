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
	
	static ArrayList<PianistPlaylist> playlist;
	
	static PianistPlaylist eegStudyPlaylist;
	
	static int playlistIndex = -1;
	
	static final int midi_delay = 350;
	
	public static void initPerformances(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		allPerformances = new HashMap<String, Performance>();
		playlist = new ArrayList<PianistPlaylist>();
		
		eegStudyPlaylist = new PianistPlaylist();
		
		initEEG(parent, plane_0, plane_1, plane_2);
		initEEG2(parent, plane_0, plane_1, plane_2);
//		initXX(parent, plane_0, plane_1, plane_2);
//		initDonal(parent, plane_0, plane_1, plane_2);
		initAlisa(parent, plane_0, plane_1, plane_2);
		initRyuichi(parent, plane_0, plane_1, plane_2);
//		initMarvin(parent, plane_0, plane_1, plane_2);
		
//		currentPerformance = allPerformances.get("P1_2");		// for EEG
//        currentPerformance = allPerformances.get("P4_1_dark");		// for EEG
//		currentPerformance = allPerformances.get("P1_2_dark"); // for EEG2
        currentPerformance = allPerformances.get("P4_1");		// for EEG
		currentPerformance.loadMidi();

		initKeyMappings();
		
		PApplet.println("Done loading performances!");
	}
	
	private static Performance createPerformance(PApplet parent, String player, String song, 
												String playerFullName, String songFullName, 
												PGraphics plane_0, PGraphics plane_1, PGraphics plane_2, boolean bPlayMidi) {
		String handsMovieFile = player + "/" + song + "_HANDS.mov";
		String faceMovieFile = player + "/" + song + "_FACE.mov";
		String midiFile = "data/" + player + "/" + song + ".MID";
		
		return new Performance(	songFullName, playerFullName,
								new GSMovie(parent, handsMovieFile), new GSMovie(parent, faceMovieFile), midiFile, 
								plane_0, plane_1, plane_2, bPlayMidi);
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
	private static void initEEG(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		String playerFullName = "EEG Study";

		allPerformances.put("P1_2", createPerformance(parent, "EEG", "P1_2", playerFullName, "Player 1, Piece 2", plane_0, plane_1, plane_2));
	
		allPerformances.put("P1_1_dark", createPerformance(parent, "EEG", "P1_1_dark", playerFullName, "Player 1, Piece 1", plane_0, plane_1, plane_2, false));

		allPerformances.put("P2_4_dark", createPerformance(parent, "EEG", "P2_4_dark", playerFullName, "Player 2, Piece 2", plane_0, plane_1, plane_2, false));

		allPerformances.put("P2_2", createPerformance(parent, "EEG", "P2_2", playerFullName, "Player 1, Piece 2", plane_0, plane_1, plane_2));
		
		allPerformances.put("P4_1_dark", createPerformance(parent, "EEG", "P4_1_dark", playerFullName, "Player 4, Piece 3", plane_0, plane_1, plane_2, false));
		
		allPerformances.put("P4_3", createPerformance(parent, "EEG", "P4_3", playerFullName, "Player 1, Piece 2", plane_0, plane_1, plane_2));
		
		allPerformances.put("mozart", createPerformance(parent, "EEG", "mozart", playerFullName, "Player 3, Piece 2", plane_0, plane_1, plane_2, false));
		
		allPerformances.put("chopin", createPerformance(parent, "EEG", "chopin", playerFullName, "Player 3, Piece 3", plane_0, plane_1, plane_2));
		
		allPerformances.put("prelude", createPerformance(parent, "EEG", "P3_prelude", playerFullName, "Player 3, Piece 3", plane_0, plane_1, plane_2));
		
		
		//eegStudyPlaylist.addPerformance(allPerformances.get("twinkle"));
//		eegStudyPlaylist.addPerformance(allPerformances.get("P1_2"));
//		eegStudyPlaylist.addPerformance(allPerformances.get("P1_1_dark"));
//		eegStudyPlaylist.addPerformance(allPerformances.get("P2_2"));
//		eegStudyPlaylist.addPerformance(allPerformances.get("P2_4_dark"));
//		eegStudyPlaylist.addPerformance(allPerformances.get("P4_3"));
//		eegStudyPlaylist.addPerformance(allPerformances.get("P4_1_dark"));
		
		eegStudyPlaylist.addPerformance(allPerformances.get("P4_1_dark"));
		eegStudyPlaylist.addPerformance(allPerformances.get("P4_3"));
		eegStudyPlaylist.addPerformance(allPerformances.get("P1_1_dark"));
		eegStudyPlaylist.addPerformance(allPerformances.get("P1_2"));
		eegStudyPlaylist.addPerformance(allPerformances.get("P2_4_dark"));
		eegStudyPlaylist.addPerformance(allPerformances.get("P2_2"));
	}
	
	private static void initEEG2(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		String playerFullName = "EEG Study";

		allPerformances.put("P1_2_dark", createPerformance(parent, "EEG2", "P1_2_dark", playerFullName, "Player 1, Piece 2", plane_0, plane_1, plane_2, false));
	
		allPerformances.put("P1_1", createPerformance(parent, "EEG2", "P1_1", playerFullName, "Player 1, Piece 1", plane_0, plane_1, plane_2));

		allPerformances.put("P2_4", createPerformance(parent, "EEG2", "P2_4", playerFullName, "Player 2, Piece 2", plane_0, plane_1, plane_2));

		allPerformances.put("P2_2_dark", createPerformance(parent, "EEG2", "P2_2_dark", playerFullName, "Player 1, Piece 2", plane_0, plane_1, plane_2, false));
		
		allPerformances.put("P4_1", createPerformance(parent, "EEG2", "P4_1", playerFullName, "Player 4, Piece 3", plane_0, plane_1, plane_2));
		
		allPerformances.put("P4_3_dark", createPerformance(parent, "EEG2", "P4_3_dark", playerFullName, "Player 1, Piece 2", plane_0, plane_1, plane_2, false));
		
		
		//eegStudyPlaylist.addPerformance(allPerformances.get("twinkle"));
//		eegStudyPlaylist.addPerformance(allPerformances.get("P1_2_dark"));
//		eegStudyPlaylist.addPerformance(allPerformances.get("P1_1"));
//		eegStudyPlaylist.addPerformance(allPerformances.get("P2_2_dark"));
//		eegStudyPlaylist.addPerformance(allPerformances.get("P2_4"));
//		eegStudyPlaylist.addPerformance(allPerformances.get("P4_3_dark"));
//		eegStudyPlaylist.addPerformance(allPerformances.get("P4_1"));
//		
		eegStudyPlaylist.addPerformance(allPerformances.get("P4_1"));
		eegStudyPlaylist.addPerformance(allPerformances.get("P4_3_dark"));
		eegStudyPlaylist.addPerformance(allPerformances.get("P1_1"));
		eegStudyPlaylist.addPerformance(allPerformances.get("P1_2_dark"));
		eegStudyPlaylist.addPerformance(allPerformances.get("P2_4"));
		eegStudyPlaylist.addPerformance(allPerformances.get("P2_2_dark"));
	}

	
	/** Load performances by me! */
	private static void initXX(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		String playerFullName = "Xiao Xiao";
		allPerformances.put("satie", createPerformance(parent, "xx", "Satie", playerFullName, "Gnosienne No. 1, Erik Satie", 
				plane_0, plane_1, plane_2));
		allPerformances.put("1", createPerformance(parent, "_fidelity", "bagatelle1", playerFullName, "Bagatelle #1, Beethoven", 
				plane_0, plane_1, plane_2));
		
		PianistPlaylist xx = new PianistPlaylist();
		xx.addPerformance(allPerformances.get("satie"));
		xx.addPerformance(allPerformances.get("5"));
		
		playlist.add(xx);
	}
	
	/** Load performances by Donal */
	private static void initDonal(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		String playerFullName = "Donal Fox";
		allPerformances.put("ugly beauty", createPerformance(parent, "donal", "ub", playerFullName, "Ugly Beauty",
				plane_0, plane_1, plane_2));
		allPerformances.put("prelude", createPerformance(parent, "donal", "prelude", playerFullName, "Prelude",
				plane_0, plane_1, plane_2));
		allPerformances.put("autumn leaves", createPerformance(parent, "donal", "autumn2", playerFullName, "Autumn Leaves",
				plane_0, plane_1, plane_2));
		allPerformances.put("lyrical", createPerformance(parent, "donal", "lyrical", playerFullName, "Lyrical",
				plane_0, plane_1, plane_2));
		
		PianistPlaylist donal = new PianistPlaylist();
		donal.addPerformance(allPerformances.get("prelude"));
		donal.addPerformance(allPerformances.get("ugly beauty"));
		donal.addPerformance(allPerformances.get("autumn leaves"));
		donal.addPerformance(allPerformances.get("lyrical"));
		
		playlist.add(donal);
	}
	
	/** Load Alisa */
	private static void initAlisa(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		String playerFullName = "Alisa Ishii";
		allPerformances.put("classical dance", createPerformance(parent, "alisa", "dance", playerFullName, "Classical Dance",
				plane_0, plane_1, plane_2));
		allPerformances.put("twinkle", createPerformance(parent, "alisa", "twinkle", playerFullName, "Twinkle Twinkle Little Star",
				plane_0, plane_1, plane_2));

		PianistPlaylist alisa = new PianistPlaylist();
		alisa.addPerformance(allPerformances.get("classical dance"));
		alisa.addPerformance(allPerformances.get("twinkle"));
		
		playlist.add(alisa);
	}
	
	/** Load Ryuichi */
	private static void initRyuichi(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		String playerFullName = "Ryuichi Sakamoto";
		allPerformances.put("improv", createPerformance(parent, "ryuichi", "improv", playerFullName, "Improv",
				plane_0, plane_1, plane_2));
		
		PianistPlaylist ryuichi = new PianistPlaylist();
		ryuichi.addPerformance(allPerformances.get("improv"));
		
		playlist.add(ryuichi);
	}
	
	/** Load Marvin */
	private static void initMarvin(PApplet parent, PGraphics plane_0, PGraphics plane_1, PGraphics plane_2) {
		allPerformances.put("marvin", createPerformance(parent, "marvin", "marvin", "Marvin Minsky", "Improvised Fugue",
				plane_0, plane_1, plane_2));
		
		PianistPlaylist marvin = new PianistPlaylist();
		marvin.addPerformance(allPerformances.get("marvin"));
		
		playlist.add(marvin);
	}
	
	public static void initKeyMappings() {
		keyMappings = new HashMap<Integer, String>();
		
		keyMappings.put(new Integer(49), "improv"); 	// 7 103
		keyMappings.put(new Integer(50), "P2_2"); // 8 104
		keyMappings.put(new Integer(51), "P4_3"); // 9 105
	
		
		keyMappings.put(new Integer(100), "P1_1"); // 4
		keyMappings.put(new Integer(101), "P2_4"); // 5
		keyMappings.put(new Integer(102), "P4_1"); // 6
		
		keyMappings.put(new Integer(99), "mozart"); // 3
		keyMappings.put(new Integer(98), "chopin"); // 2
		keyMappings.put(new Integer(97), "prelude"); // 1
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

	public static boolean setNextPerformance() {
		if (eegStudyPlaylist.atEnd()) {
			return false;
		} else {
			currentPerformance = eegStudyPlaylist.getNextPerformance();
			return true;
		}
//		if (playlistIndex < (playlist.size()-1))
//			playlistIndex ++;
//		else
//			playlistIndex = 0;
//		
//		currentPerformance = playlist.get(playlistIndex).getNextPerformance();
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
