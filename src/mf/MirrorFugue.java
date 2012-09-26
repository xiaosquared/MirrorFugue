package mf;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import content.Performance;

import processing.core.*;
import warp.CornerPinSurface;
import codeanticode.gsvideo.*;


public class MirrorFugue extends PApplet {  
  private static final long serialVersionUID = 1L;
 
  //test comment
  
  // old demo
  Performance ravel2, ravel3;
  
  // user study
  Performance satie, ravel, bach, chopin, gershwin, improv, satie_long;
  
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
    
    // PLAY!!
    current_performance.play(false);		// first start the movie to let it load
    while (!current_performance.ready()) {
    	delay(200);
    }
    current_performance.pause();			// when it's ready, reset and start everything together
    current_performance.rewind();
    //current_performance.play(bPlayMidi);
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
	  gershwin = new Performance(new GSMovie(this, "Gershwin_HANDS.mov"), new GSMovie(this, "Gershwin_FACE.mov"), "data/Gershwin.MID", 
				plane_0, plane_1, plane_2); 
	  satie = new Performance(new GSMovie(this, "Satie_HANDS.mov"), new GSMovie(this, "Satie_FACE.mov"), "data/Satie.MID", 
				plane_0, plane_1, plane_2);
	  satie_long = new Performance(new GSMovie(this, "Satie_long_HANDS.mov"), new GSMovie(this, "Satie_long_FACE.mov"), "data/Satie_long.MID", 
				plane_0, plane_1, plane_2);
	  bach = new Performance(new GSMovie(this, "Bach_HANDS.mov"), new GSMovie(this, "Bach_FACE.mov"), "data/Bach.MID", 
				plane_0, plane_1, plane_2);
	  ravel = new Performance(new GSMovie(this, "Ravel_HANDS.mov"), new GSMovie(this, "Ravel_FACE.mov"), "data/Ravel.MID", 
				plane_0, plane_1, plane_2);
	  chopin = new Performance(new GSMovie(this, "Chopin_HANDS.mov"), new GSMovie(this, "Chopin_FACE.mov"), "data/Chopin.MID", 
				plane_0, plane_1, plane_2);
	  improv = new Performance(new GSMovie(this, "Improv_HANDS.mov"), new GSMovie(this, "Improv_FACE.mov"), "data/Improv.MID", 
				plane_0, plane_1, plane_2);	  
  }
  
  public void initOldDemo() {
	  ravel2 = new Performance(new GSMovie(this, "mv2_hands.mov"), new GSMovie(this, "mv2_face.mov"), "data/mv2.mid", 
				plane_0, plane_1, plane_2);
	  ravel3 = new Performance(new GSMovie(this, "mv3_hands.mov"), new GSMovie(this, "mv3_face.mov"), "data/mv3.mid", 
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

  public void keyPressed() {
	  println("key pressed: " + keyCode);
	  switch (keyCode) {
	  	case 10:
		  if (current_performance.isPlaying()) 
			  current_performance.pause();
		  else {
			  delay(4000);
			  current_performance.play(bPlayMidi);
		  }
		  break;
	  	case 109:
	  		current_performance.stop();
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
	  		fill(0);
	  		rect(0, 0, width, 800);
	  		fill(200);
	  		rect(0, 560, width, 300);
	  		break;
	  		
	  	// SWITCHING PIECES!!
	  	case 97:
	  		println("RAVEL");
	  		switchPiece(ravel);
	  		break;
	  	case 98:
	  		println("SATIE");
	  		switchPiece(satie);
	  		break;
	  	case 99:
	  		println("GERSHWIN");
	  		switchPiece(gershwin);
	  		break;
	  	case 100:
	  		println("CHOPIN");
	  		switchPiece(chopin);
	  		break;
	  	case 101:
	  		println("BACH");
	  		switchPiece(bach);
	  		break;
	  	case 102:
	  		println("IMPROV");
	  		switchPiece(improv);
	  		break;
	  	case 103:
	  		println("SATIE FULL");
	  		switchPiece(satie_long);
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
