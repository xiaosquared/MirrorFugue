package content;

import processing.core.PApplet;
import processing.core.PGraphics;
import codeanticode.gsvideo.GSMovie;

public class Performance {
	// names
	String song;
	String player;
	
	// files
	GSMovie movie_hands;
	GSMovie movie_face;
	midi.MidiPlayer midi;
	
	String midi_file;
	
	// parameters
	boolean bPlaying = false;
	boolean bPlayMidi = true;
	float scale_face = 0.395f;
	//float scale_face = 0.317f;
	float scale_hands = 0.608f;
	//float y_offset = 0.275f;
	float y_offset = 0.43f;
	//float y_offset = 0.15f;
	float x_offset = 0.55f;
	//float x_offset = 0.f;
	
	// planes for keyboard
	PGraphics plane_0;
	PGraphics plane_1;
	PGraphics plane_2;
	
	public Performance(String songFullName, String playerFullName,
						GSMovie movie_hands, GSMovie movie_face, String midi_file,
						PGraphics plane0, PGraphics plane1, PGraphics plane2) {
		this.song = songFullName;
		this.player = playerFullName;
		this.movie_hands = movie_hands;
		this.movie_face = movie_face;
		
		//midi = new midi.MidiPlayer();
		//midi.openMidi(midi_file);
		this.midi_file = midi_file;
		
		plane_0 = plane0;
		plane_1 = plane1;
		plane_2 = plane2;
	}
	
	public Performance(String songFullName, String playerFullName,
			GSMovie movie_hands, GSMovie movie_face, String midi_file,
			PGraphics plane0, PGraphics plane1, PGraphics plane2, boolean bPlayMidi) {
		this.song = songFullName;
		this.player = playerFullName;
		this.movie_hands = movie_hands;
		this.movie_face = movie_face;

		//midi = new midi.MidiPlayer();
		//midi.openMidi(midi_file);
		this.midi_file = midi_file;

		plane_0 = plane0;
		plane_1 = plane1;
		plane_2 = plane2;

		this.bPlayMidi = bPlayMidi;
	}
	
	public Performance(GSMovie movie_hands, GSMovie movie_face, String midi_file,
			PGraphics plane0, PGraphics plane1, PGraphics plane2, float scale_face) {
		this("", "", movie_hands, movie_face, midi_file, plane0, plane1, plane2);
		this.scale_face = scale_face;
	}

	public boolean isPlaying() {
		return bPlaying && (Math.abs(movie_face.time() - movie_face.duration()) > 0.01);
	}
	
	public boolean isEnded() {
		return (Math.abs(movie_face.time() - movie_face.duration()) <= 0.01);
	}
	
	public boolean isPaused() {
		return !bPlaying;
	}
	
	public void loadMidi() {
		midi = new midi.MidiPlayer();
		midi.openMidi(midi_file);
	}
	
	public void drawFace(PApplet parent) {
		parent.pushMatrix();
			parent.translate(parent.width * x_offset, parent.height * y_offset);
			parent.scale(-scale_face, scale_face);
			parent.image(movie_face, 0,0);
		parent.popMatrix();
	}
	
	public void drawHandsOnKeys(warp.CornerPinSurface s0, warp.CornerPinSurface s1, warp.CornerPinSurface s2) {
		plane_0.beginDraw();
		plane_0.image(movie_hands, -34, -105);
		//plane_0.image(movie_hands, -53, -105);
		plane_0.endDraw();

		plane_1.beginDraw();
		//plane_1.image(movie_hands, -651, -105);
		plane_1.image(movie_hands, -455, -105);
		plane_1.endDraw();

		plane_2.beginDraw();
		//plane_2.image(movie_hands, -910, -105);
		plane_2.image(movie_hands, -863, -105);
		plane_2.endDraw();
		
		s0.render(plane_0);
		s1.render(plane_1);
		s2.render(plane_2);
		
	}
	
	public void drawHandOrganMode(PApplet parent) {
		parent.pushMatrix();
			//parent.translate(parent.width * 0.44f, parent.height * 0.798f);
			
			parent.translate(parent.width * 0.44f, parent.height * .798f);
			parent.scale(scale_hands, scale_hands);
			parent.image(movie_hands, 3, 0);
		parent.popMatrix();
	}
	
	public boolean ready() {
		return movie_hands.ready() && movie_face.ready();
	}
	
	
	public void playMidi() {
		midi.playMidi();
	}
	
	public void pauseMidi() {
		midi.pauseMidi();
	}
	
	public void play(boolean bPlayMidi) {
		bPlaying = true;

		movie_hands.play();
		movie_face.play();
		
		if (bPlayMidi && this.bPlayMidi) {
			movie_hands.volume(0);
		}
		movie_face.volume(0);
	}
	
	public void pause() {
		bPlaying = false;
		movie_hands.pause();
		movie_face.pause();
		midi.pauseMidi();
	}
	
	public void stop() {
		bPlaying = false;
		movie_hands.stop();
		movie_face.stop();
		midi.pauseMidi();
		midi.rewindMidi();
	}
	
	public void rewind() {
		midi.rewindMidi();
		movie_face.goToBeginning();
		movie_hands.goToBeginning();
	}
}
