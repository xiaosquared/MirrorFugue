package mf;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import content.PerformanceManager;

import processing.core.*;
import processing.serial.*;
import warp.SurfaceManager;
import codeanticode.gsvideo.*;
import controlP5.*;


public class MirrorFugue extends PApplet {  
  private static final long serialVersionUID = 1L;

  // Surfaces
  SurfaceManager surfaceMapping;
  PGraphics plane_0, plane_1, plane_2;
  
  int key_mode = 0; // 0 is keys, 1 is organ mode, 2 is disklavier only
  boolean bPlayMidi = true; 
  
  boolean bStudy = true;
  boolean noMotionSensor = true;
  
  //SERIAL
  Serial myPort;
  
  //GUI
  ControlP5 cp5;
  ControlWindow calibration;
  RadioButton r;
  
  PImage bkg;
  
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
    //size(1280, 1024, P3D);
	  background(0);
    fill(0);
    noStroke();
    imageMode(CENTER);
    
    bkg = loadImage("bkg4.jpg");
    
    // Calibration GUI
    cp5 = new ControlP5(this);
    calibration = cp5.addControlWindow("controlP5window", 200, 50, 600, 300).hide()
    	    .hideCoordinates().setBackground(color(40));
    r = cp5.addRadioButton("radioButton").moveTo(calibration)
            .setPosition(60,160)
            .setSize(20,20)
            .setColorForeground(color(120))
            .setColorActive(color(255))
            .setColorLabel(color(255))
            .setItemsPerRow(6)
            .setSpacingColumn(50)
            .setSpacingRow(20)
            .addItem("tl0",1)
            .addItem("tr0",2)
            .addItem("tl1",3)
            .addItem("tr1",4)
            .addItem("tl2",5)
            .addItem("tr2",6)
            .addItem("bl0",7)
            .addItem("br0",8)
            .addItem("bl1",9)
            .addItem("br1",10)
            .addItem("bl2",11)
            .addItem("br2",12)
            ;
  
    surfaceMapping = new SurfaceManager(this);
    
//    plane_0 = createGraphics(625, 170, P3D);
//    plane_1 = createGraphics(259, 170, P3D);
//    plane_2 = createGraphics(354, 170, P3D);
    plane_0 = createGraphics(430, 170, P3D);
    plane_1 = createGraphics(411, 170, P3D);
	plane_2 = createGraphics(411, 170, P3D);

    //setSurfaces();
    clearScreen();
    
    // load performances
    PerformanceManager.initPerformances(this, plane_0, plane_1, plane_2);
  }
  
  private void clearScreen() {
	  fill(0);
	  rect(0, 0, width, 800);
	  //fill(255);
	  //rect(0, 500, width, 800);
	  imageMode(CORNER);
	  image(bkg,60, 400);
	  imageMode(CENTER);
  }

  public void movieEvent(GSMovie movie) {
    movie.read();
  }

  public void draw() {
	  if (PerformanceManager.isCurrentlyPlaying()) {
		  //keys    
		  if (key_mode == 0)   
			  PerformanceManager.getCurrentPerformance().drawHandsOnKeys(surfaceMapping.getSurface(0), 
					  													surfaceMapping.getSurface(1), 
					  													surfaceMapping.getSurface(2));	
		  else if (key_mode == 1)
			  PerformanceManager.getCurrentPerformance().drawHandOrganMode(this);

		  // face
		  if (key_mode != 2)
			  PerformanceManager.getCurrentPerformance().drawFace(this);
	  } 
	  
	  else if (PerformanceManager.isEnded() && PerformanceManager.startNextPerformance()){
		  if(noMotionSensor) {
			  delay(2700);
			  PerformanceManager.setNextPerformance();
			  PerformanceManager.playCurrentPerformance(this, bPlayMidi);
			  return;
		  }
		  
		  String inBuffer = null;
		  while(myPort.available() > 0) {
			  inBuffer = myPort.readStringUntil(95); // code for underscore
		  }
		  if (inBuffer != null) {
			  if (inBuffer.equals("YES_")) {
				  println("yes");
				  PerformanceManager.setNextPerformance();
				  PerformanceManager.playCurrentPerformance(this, bPlayMidi);
			  }
			  else 
				  println("no");
		  }
	  }
  }
  
  public void keyPressed() {
	  println("key pressed: " + keyCode);
	//  fill(0);
	 //1 rect(0, 500, width, 800);
	  if (key == CODED) {
		  calibrationGUI(keyCode);
		  return;
	  }
	  switch (keyCode) {
	  	case 10:
		  if (PerformanceManager.isCurrentlyPlaying()) 
			  PerformanceManager.pauseCurrentPerformance();
		  else {  
			PerformanceManager.playCurrentPerformance(this, bPlayMidi);
		  }
		  break;
	  	case 32:
			  if (PerformanceManager.isCurrentlyPlaying()) 
				  PerformanceManager.pauseCurrentPerformance();
			  else {  
				PerformanceManager.playCurrentPerformance(this, bPlayMidi);
			  }
			  break;
	  	
	    // Go to next performance
	  	case 90:
	  		if (!PerformanceManager.isCurrentlyPlaying())
	  			PerformanceManager.playCurrentPerformance(this, bPlayMidi);
	  		else {
	  			PerformanceManager.getCurrentPerformance().stop();
	  			PerformanceManager.setNextPerformance();
	  			PerformanceManager.playCurrentPerformance(this, bPlayMidi);
	  		}
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
//	  	case 65:
//	  		noMotionSensor = !noMotionSensor;
//	  		break;
	  	default:
	  		key_mode = 0;
	  		if (PerformanceManager.handleKeyPress(keyCode))
	  			PerformanceManager.playCurrentPerformance(this, bPlayMidi);
	  		break;
	  }
  }
  
  private void calibrationGUI(int keyCode) {
	  if (keyCode == ALT) {
		  if (calibration.isVisible()) {
			  calibration.hide();
			  surfaceMapping.save();
		  }
		  else
			  calibration.show();
	  
	  }
	  if (!surfaceMapping.surfaceSelected())
		  return;
	  
	  if (keyCode == UP) {
		  surfaceMapping.nudgeSelectedPointY(-1);
	  } else if (keyCode == DOWN) {
		  surfaceMapping.nudgeSelectedPointY(1);
	  } else if (keyCode == LEFT) {
		  surfaceMapping.nudgeSelectedPointX(-1);
	  } else if (keyCode == RIGHT) {
		  surfaceMapping.nudgeSelectedPointX(1);
	  }
		  
  }
  
  public void controlEvent(ControlEvent e) {
	  if (e.isFrom(r)) {
		  println(e.getGroup().getValue());
		  surfaceMapping.setSelection((int) e.getGroup().getValue());
	  }
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
//	if (devices.length > 1) {
//		primary_width = devices[0].getDisplayMode().getWidth();
//		location = "--location=" +primary_width+ "," + screen_y;
//	} else {
		location="--location=0,0";
//	}
    PApplet.main(new String[] { location, MirrorFugue.class.getName() });
  }

}