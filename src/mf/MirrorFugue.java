package mf;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import content.Performance;
import content.PerformanceManager;

import processing.core.*;
import processing.serial.*;
import warp.CornerPinSurface;
import warp.Keystone;
import codeanticode.gsvideo.*;


public class MirrorFugue extends PApplet {  
  private static final long serialVersionUID = 1L;

  // old demo
  Performance ravel2, ravel3;
  
  // user study
  Performance ravel, bach, gershwin, improv, satie_long, marvin, chopin, chopin_test, chopin_parts_all;
  
  // donal
  Performance donal_prelude, ugly_beauty, autumn_leaves, autumn_leaves2, lyrical;
  
  // alisa
  Performance alisa_twinkle, alisa_dance;
  
  Performance current_performance;
  
  Keystone key;
  CornerPinSurface surface_0, surface_1, surface_2;
  PGraphics plane_0, plane_1, plane_2;
  
  int key_mode = 0; // 0 is keys, 1 is organ mode, 2 is disklavier only
  boolean bPlayMidi = true; 
  
  boolean bStudy = true;
  
  //SERIAL
  Serial myPort;
  
  public void init(){
	  if(frame!=null){
		  frame.removeNotify();	//make the frame not displayable
		  frame.setResizable(false);
		  frame.setUndecorated(true);
		  frame.addNotify();
		  frame.requestFocus();
	  }
	  super.init();
	  println(Serial.list());
  }
  
  public void setup() {
    size(1024, 768, P3D);
    background(0);
    fill(0);
    noStroke();
    imageMode(CENTER);
    
    // surface for hands
//    surface_0 = new CornerPinSurface(this, 600, 170, 5);    
//    surface_1 = new CornerPinSurface(this, 259, 170, 5);
//    surface_2 = new CornerPinSurface(this, 354, 170, 5);
    key = new Keystone(this);
    surface_0 = key.createCornerPinSurface(600, 170, 5);
    surface_1 = key.createCornerPinSurface(259, 170, 5);
    surface_2 = key.createCornerPinSurface(354, 170, 5);
       
    plane_0 = createGraphics(600, 170, P3D);
    plane_1 = createGraphics(259, 170, P3D);
    plane_2 = createGraphics(354, 170, P3D);

    setSurfaces();
    clearScreen();
    
    // load performances
    PerformanceManager.initPerformances(this, plane_0, plane_1, plane_2);
     
    //Serial
    myPort = new Serial(this, Serial.list()[0], 9600);
  }
  
  private void clearScreen() {
	  fill(0);
	  rect(0, 0, width, 800);
	  fill(0);
	  rect(0, 500, width, 800);
  }

  private void setSurfaces(){
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
  
//  private void setSurfaces(){
//	  surface_0.setControlPoints(94, 556,	//TL 
//				460, 556, 		//TR
//				-20, 788, 		//BL
//				444, 788); 		//BR
//	  surface_1.setControlPoints(460, 556,	//TL
//			  615, 555,	//TR
//			  444, 788,	//BL
//			  644, 788);	//BR
//	  surface_2.setControlPoints(615, 555,	//TL
//			   834, 555,	//TR
//			   644, 788,	//BL
//			   919, 788);	//BR
//  }
  
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
    
  public void movieEvent(GSMovie movie) {
    movie.read();
  }

  public void draw() {
	  if (PerformanceManager.isCurrentlyPlaying()) {
		  //keys    
		  if (key_mode == 0)   
			  PerformanceManager.getCurrentPerformance().drawHandsOnKeys(surface_0, surface_1, surface_2);	
		  else if (key_mode == 1)
			  PerformanceManager.getCurrentPerformance().drawHandOrganMode(this);

		  // face
		  if (key_mode != 2)
			  PerformanceManager.getCurrentPerformance().drawFace(this);
	  } 
	  
	  else if (PerformanceManager.isEnded()){
		  String inBuffer = null;
		  while(myPort.available() > 0) {
			  inBuffer = myPort.readStringUntil(95); // code for underscore
		  }
		  if (inBuffer != null) {
			  if (inBuffer.equals("YES_")) {
				  println("yes");
				  PerformanceManager.setRandomPerformance();
				  PerformanceManager.playCurrentPerformance(this, bPlayMidi);
			  }
			  else 
				  println("no");
		  }
	  }
  }
  
  public void keyPressed() {
	  println("key pressed: " + keyCode);
	  switch (keyCode) {
	  	case 32:
		  if (PerformanceManager.isCurrentlyPlaying()) 
			  PerformanceManager.pauseCurrentPerformance();
		  else {
			PerformanceManager.playCurrentPerformance(this, bPlayMidi);
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
	  		
	  	default:
	  		if (PerformanceManager.handleKeyPress(keyCode))
	  			PerformanceManager.playCurrentPerformance(this, bPlayMidi);
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