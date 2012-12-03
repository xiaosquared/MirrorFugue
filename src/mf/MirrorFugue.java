package mf;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import content.Performance;

import processing.core.*;
import warp.CornerPinSurface;
import codeanticode.gsvideo.*;


public class MirrorFugue extends PApplet {  
  private static final long serialVersionUID = 1L;

  // old demo
  Performance ravel2, ravel3;
  
  // user study
  Performance ravel, bach, gershwin, improv, satie_long, marvin, chopin, alisa, chopin_test, chopin_parts_all;
  
  // donal
  Performance donal_prelude, ugly_beauty, autumn_leaves, autumn_leaves2;
  
  Performance current_performance;
  
  CornerPinSurface surface_0, surface_1, surface_2;
  PGraphics plane_0, plane_1, plane_2;
  
  int key_mode = 0; // 0 is keys, 1 is organ mode, 2 is disklavier only
  boolean bPlayMidi = true; 
  
  boolean bStudy = true;
  
  public void init(){
	  if(frame!=null){
		  frame.removeNotify();	//make the frame not displayable
		  frame.setResizable(false);
		  frame.setUndecorated(true);
		  frame.addNotify();
		  frame.requestFocus();
	  }
	  super.init();
  }
  
  public void setup() {
    size(1024, 768, P3D);
    background(0);
    fill(0);
    noStroke();
    imageMode(CENTER);
    
    // surface for hands
    surface_0 = new CornerPinSurface(this, 600, 170, 5);    
    surface_1 = new CornerPinSurface(this, 259, 170, 5);
    surface_2 = new CornerPinSurface(this, 354, 170, 5);
       
    plane_0 = createGraphics(600, 170, P2D);
    plane_1 = createGraphics(259, 170, P2D);
    plane_2 = createGraphics(354, 170, P2D);

    if (bStudy) {
    	setSurfacesStudy();
    	initUserStudy();
    	current_performance = chopin;
    } else {
    	setSurfacesOld();
    	initOldDemo();
    	current_performance = ravel2;
    }
    
    current_performance.loadMidi();
    
    clearScreen();
  }
  
  private void clearScreen() {
	  fill(0);
	  rect(0, 0, width, 800);
	  fill(100);
	  rect(0, 500, width, 800);
  }

  private void setSurfacesStudy(){
	  surface_0.setControlPoints(89, 556,	//TL 
				461, 556, 		//TR
				-22, 788, 		//BL
				443, 788); 		//BR
	  surface_1.setControlPoints(459, 556,	//TL
			  616, 555,	//TR
			  443, 788,	//BL
			  646, 788);	//BR
	  surface_2.setControlPoints(616, 555,	//TL
			   838, 555,	//TR
			   646, 788,	//BL
			   922, 788);	//BR
  }
  
  // for old demo of Ravel 2nd movement
  private void setSurfacesOld() {
	  surface_0.setControlPoints(89, 556,	//TL 
			  460, 556, 		//TR
			  -27, 788, 		//BL
			  443, 788); 		//BR  
	  surface_1.setControlPoints(459, 556,	//TL
			  618, 560,	//TR
			  443, 788,	//BL
			  643, 788);	//BR
	  surface_2.setControlPoints(618, 560,	//TL
			  840, 560,	//TR
			  643, 788,	//BL
			  925, 788);	//BR
  }
  
  private void initUserStudy() {
	  gershwin = new Performance(new GSMovie(this, "xx/Gershwin_HANDS.mov"), new GSMovie(this, "xx/Gershwin_FACE.mov"), "data/xx/Gershwin.MID", 
				plane_0, plane_1, plane_2); 
	  satie_long = new Performance(new GSMovie(this, "xx/Satie_long_HANDS.mov"), new GSMovie(this, "xx/Satie_long_FACE.mov"), "data/xx/Satie_long.MID", 
				plane_0, plane_1, plane_2);
	  bach = new Performance(new GSMovie(this, "xx/Bach_HANDS.mov"), new GSMovie(this, "xx/Bach_FACE.mov"), "xx/data/Bach.MID", 
				plane_0, plane_1, plane_2);
	  ravel = new Performance(new GSMovie(this, "xx/Ravel_HANDS.mov"), new GSMovie(this, "xx/Ravel_FACE.mov"), "xx/data/Ravel.MID", 
				plane_0, plane_1, plane_2);
	  improv = new Performance(new GSMovie(this, "xx/Improv_HANDS.mov"), new GSMovie(this, "xx/Improv_FACE.mov"), "xx/data/Improv.MID", 
				plane_0, plane_1, plane_2);	 
	  marvin = new Performance(new GSMovie(this, "marvin/marvin_hands_opening.mov"), 
			  	new GSMovie(this, "marvin/marvin_face_opening.mov"), "data/marvin/marvin.mid",
			  	plane_0, plane_1, plane_2);
	  chopin = new Performance(new GSMovie(this, "chopin_layers/chopin_hands_all.mov"), 
			  	new GSMovie(this, "chopin_layers/chopin_face_all2.mov"), "data/chopin_layers/chopin_all.MID", 
				plane_0, plane_1, plane_2);
	  alisa = new Performance(new GSMovie(this, "alisa/dance_hands.mov"), 
			  	new GSMovie(this, "alisa/dance_face.mov"), "data/alisa/dance.MID", 
				plane_0, plane_1, plane_2);
				//, 0.45f, 0.26f);
	  chopin_test = new Performance(new GSMovie(this, "chopin_layers/tenor_harmony_hands.mov"), 
			  	new GSMovie(this, "chopin_layers/tenor_harmony_face.mov"), "data/chopin_layers/tenor_harmony.MID", 
				plane_0, plane_1, plane_2);
	  chopin_parts_all = new Performance(new GSMovie(this, "chopin_layers/narrative_hands.mov"), 
			  	new GSMovie(this, "chopin_layers/narrative_face.mov"), "data/chopin_layers/narrative.mid", 
				plane_0, plane_1, plane_2, 0.38f);
//	  chopin_parts_all = new Performance(new GSMovie(this, "chopin_layers/melodies_hands.mov"), 
//			  	new GSMovie(this, "chopin_layers/melodies_face.mov"), "data/chopin_layers/melodies.mid", 
//				plane_0, plane_1, plane_2);
	  
	  // donal
	  ugly_beauty = new Performance(new GSMovie(this, "donal/ub_hands.mov"), 
			  	new GSMovie(this, "donal/ub_face.mov"), "data/donal/ub.mid", 
				plane_0, plane_1, plane_2);
	  
	  donal_prelude = new Performance(new GSMovie(this, "donal/prelude_hands.mov"), 
			  	new GSMovie(this, "donal/prelude_face.mov"), "data/donal/prelude.mid", 
				plane_0, plane_1, plane_2);
	  
	  autumn_leaves = new Performance(new GSMovie(this, "donal/autumn_hands.mov"), 
			  	new GSMovie(this, "donal/autumn_face.mov"), "data/donal/autumn.mid", 
				plane_0, plane_1, plane_2);
	  
	  autumn_leaves2 = new Performance(new GSMovie(this, "donal/autumn2_hands.mov"), 
			  	new GSMovie(this, "donal/autumn_face.mov"), "data/donal/autumn2.mid", 
				plane_0, plane_1, plane_2);
  }
  
  public void initOldDemo() {
	  ravel2 = new Performance(new GSMovie(this, "xx/mv2_hands.mov"), new GSMovie(this, "xx/mv2_face.mov"), "data/xx/mv2.mid", 
				plane_0, plane_1, plane_2);
	  ravel3 = new Performance(new GSMovie(this, "xx/mv3_hands.mov"), new GSMovie(this, "xx/mv3_face.mov"), "data/xx/mv3.mid", 
				plane_0, plane_1, plane_2);
  }
  
  public void movieEvent(GSMovie movie) {
    movie.read();
  }

  public void draw() {
	//keys    
	if (key_mode == 0)   
		current_performance.drawHandsOnKeys(surface_0, surface_1, surface_2);	
	else if (key_mode == 1)
		current_performance.drawHandOrganMode(this);
	
	// face
	if (key_mode != 2)
		current_performance.drawFace(this);
  }

  private void playPerformance(int midi_delay) {
	  key_mode = 0;
	  current_performance.play(bPlayMidi);
	  while(!current_performance.ready()) {
		  delay(10);
	  }
	  current_performance.pause();			// when it's ready, reset and start everything together
	  current_performance.rewind();
	  
	  current_performance.playMidi();		// put in a little delay between starting midi and video to compensate for dsk delay
	  delay(midi_delay);
	  
	  current_performance.play(bPlayMidi);
  }
  
  public void keyPressed() {
	  println("key pressed: " + keyCode);
	  switch (keyCode) {
	  	case 32:
		  if (current_performance.isPlaying()) 
			  current_performance.pause();
		  else {
			playPerformance(400);
		  }
		  break;
	  	case 109:
	  		current_performance.stop();
	  		clearScreen();
	  		break;
	
	  	// SOUND or Disklavier	
	  	case 96:						
	  		bPlayMidi = false;
	  		break;
	  	case 110:
	  		bPlayMidi = true;
	  		break;
	  	
	  	// ON KEYS OR ABOVE
	  	case 8:				// draw on keys
	  		key_mode = 0;
	  		fill(0);
	  		rect(0, 0, width, height);
	  		break;
	  	case 106:				// organ mode
	  		key_mode = 1;
	  		fill(0);
	  		rect(0, 0, width, height);
	  		break;
	  	case 111:				// only disklavier
	  		key_mode = 2;
	  		clearScreen();
	  		break;
	  		
	  	// SWITCHING PIECES!!
	  	case 97:
	  		println("RAVEL");
	  		switchPiece(ravel);
	  		break;
	  	case 98:
	  		println("IMPROV");
	  		switchPiece(improv);
	  		break;
	  	case 99:
	  		println("Narrative");
	  		switchPiece(chopin_parts_all);
	  		break;
	  	case 100:
	  		println("Ugly Beauty");
	  		switchPiece(ugly_beauty);
	  		break;
	  	case 101:
	  		println("PRELUDE");
	  		switchPiece(donal_prelude);
	  		break;
	  	case 102:
	  		println("AUTUMN");
	  		switchPiece(autumn_leaves);
	  		break;
	  	case 103:
	  		println("SATIE");
	  		switchPiece(satie_long);
	  		break;
	  	case 104:
	  		println("marvin");
	  		switchPiece(marvin);
	  		break;
	  	case 105:
	  		println("alisa");
	  		switchPiece(alisa);
	  		break;
	  }
  }
  
  private void switchPiece (Performance p) {
	  current_performance.stop();
	  current_performance = p;
	  current_performance.loadMidi();
  }
  
  public void mousePressed() {
	  println("mouse: " + mouseX + ", " + mouseY);
  }
  
  public static void main(String args[]) {
	/* 
	 * places window on second screen automatically if there's additional display
	 * 
	 */
	int primary_width;
	int screen_y = 0;
	
	GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice devices[] = environment.getScreenDevices();
	String location;
	if (devices.length > 1) {
		primary_width = devices[0].getDisplayMode().getWidth();
		location = "--location=" +primary_width+ "," + screen_y;
	} else {
		location="--location=0,0";
	}
    PApplet.main(new String[] { location, MirrorFugue.class.getName() });
  }

}
