package content;

import processing.core.PApplet;
import processing.core.PGraphics;
import codeanticode.gsvideo.GSMovie;

public class Performance {
	// files
	GSMovie movie_hands;
	GSMovie movie_face;
	midi.MidiPlayer midi;
	
	String midi_file;
	
	// parameters
	boolean bPlaying = false;
	boolean bPlayMidi;
	float scale_face = 0.32f;
	float scale_hands = 0.608f;
	
	// planes for keyboard
	PGraphics plane_0;
	PGraphics plane_1;
	PGraphics plane_2;
	
	public Performance(GSMovie movie_hands, GSMovie movie_face, String midi_file,
						PGraphics plane0, PGraphics plane1, PGraphics plane2) {
		this.movie_hands = movie_hands;
		this.movie_face = movie_face;
		
		//midi = new midi.MidiPlayer();
		//midi.openMidi(midi_file);
		this.midi_file = midi_file;
		
		plane_0 = plane0;
		plane_1 = plane1;
		plane_2 = plane2;
	}

	public boolean isPlaying() {
		return bPlaying;
	}
	
	public void loadMidi() {
		midi = new midi.MidiPlayer();
		midi.openMidi(midi_file);
	}
	
	public void drawFace(PApplet parent) {
		parent.pushMatrix();
			parent.translate(parent.width * 0.48f, parent.height * 0.29f);
			parent.scale(-scale_face, scale_face);
			parent.image(movie_face, 0,0);
		parent.popMatrix();
	}
	
	public void drawHandsOnKeys(warp.CornerPinSurface s0, warp.CornerPinSurface s1, warp.CornerPinSurface s2) {
		plane_0.beginDraw();
		plane_0.image(movie_hands, -53, -105);
		plane_0.endDraw();

		plane_1.beginDraw();
		plane_1.image(movie_hands, -651, -105);
		plane_1.endDraw();

		plane_2.beginDraw();
		plane_2.image(movie_hands, -910, -105);
		plane_2.endDraw();
		
		s0.render(plane_0);
		s1.render(plane_1);
		s2.render(plane_2);
	}
	
	public void drawHandOrganMode(PApplet parent) {
		parent.pushMatrix();
			parent.translate(parent.width * 0.44f, parent.height * 0.798f);
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
	
	public void play(boolean bPlayMidi) {
		bPlaying = true;

//		if (bPlayMidi) {
//			midi.playMidi();
//		}
//	
		movie_hands.play();
		movie_face.play();
		
		if (bPlayMidi) {
			movie_hands.volume(0);
		}
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
