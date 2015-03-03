package mf;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import codeanticode.gsvideo.GSMovie;

import processing.core.PApplet;

public class DrJohn extends PApplet {

	GSMovie drjohn;  
	content.StartPointManager starts;
	
	public void setup () {
		size(1024, 768, P3D);
		background(0);
		imageMode(CENTER);
		fill(0);
		noStroke();
		
		starts = new content.StartPointManager();
		
		drjohn = new GSMovie(this, "drjohn.avi");
		drjohn.play();		
		while(!drjohn.ready()) {
			delay(500);
		}
		drjohn.jump(getSeconds(2, 42));
		//drjohn.pause();
	}
	
	private float getSeconds(int min, int sec) {
		return (float) (min*60 + sec);
	}
	
	public void draw() {
		image(drjohn, width * .435f, height * .442f, 770, 578);
		rect(0, 534, 1024, 650);
	}

	public void movieEvent(GSMovie movie) {
		movie.read();
	}
	
	public void mousePressed() {
		println(mouseX + ", " + mouseY);
	}
	
	public void keyPressed() {
		println("key pressed: " + keyCode);
		switch (keyCode) {
		case 10:
			if (drjohn.isPlaying()) 
				drjohn.pause();
			else
				drjohn.play();
			break;
		case 109:
			drjohn.jump(starts.getNext().getTime());
			break;
		case 107:
			drjohn.jump(starts.getPrev().getTime());
			break;
		}
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
		PApplet.main(new String[] { location, DrJohn.class.getName() });
	}

}
	

