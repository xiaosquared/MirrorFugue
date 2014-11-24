package content;

import processing.core.PApplet;
import codeanticode.gsvideo.GSMovie;

public class Portrait {
	//names
	String player;
	
	// location
	int x, y;
	
	//files
	GSMovie sitting_movie;
	GSMovie exit_movie;
	GSMovie current_movie;
	
	public Portrait(String playerName, GSMovie sitting_movie, GSMovie exit_movie, int x, int y) {
		this.player = playerName;
		this.sitting_movie = sitting_movie;
		this.current_movie = sitting_movie;
		this.exit_movie = exit_movie;
		this.x = x;
		this.y = y;
	}
	
	public void draw(PApplet parent) {
		parent.pushMatrix();
			parent.translate(x, y);
			parent.scale(0.3f, 0.3f);
			parent.image(current_movie, 0,0);
		parent.popMatrix();
	}

	public void playSitting() {
		exit_movie.stop();
		exit_movie.goToBeginning();
	
		current_movie = sitting_movie;
		sitting_movie.play();
		sitting_movie.volume(0);
	}
	
	public void playExit() {
		sitting_movie.stop();
		sitting_movie.goToEnd();
		
		current_movie = exit_movie;
		exit_movie.play();
		exit_movie.volume(0);
	}
	
	public boolean finishedExit() {
		double diff = Math.abs(exit_movie.time() - exit_movie.duration());
		
		return exit_movie.frame() != -1 && diff <= 0.003;
	}
}
