package content;

import processing.core.PApplet;
import codeanticode.gsvideo.GSMovie;

public class Portrait {
	//names
	String player;
	
	// location
	int x, y;
	
	// init
	int init;
	
	//files
	GSMovie sitting_movie;
	GSMovie init_movie;
	GSMovie exit_movie;
	GSMovie current_movie;
	
	public Portrait(String playerName, GSMovie init_movie, GSMovie sitting_movie, GSMovie exit_movie, int x, int y) {
		this.player = playerName;
		this.sitting_movie = sitting_movie;
		this.current_movie = sitting_movie;
		this.init_movie = init_movie;
		this.exit_movie = exit_movie;
		this.x = x;
		this.y = y;
	}
	
	public Portrait(String playerName, GSMovie sitting_movie, GSMovie exit_movie, int x, int y) {
		this(playerName, sitting_movie, sitting_movie, exit_movie, x, y);
	}
	
	public void draw(PApplet parent) {
		parent.imageMode(parent.CORNER);
		parent.pushMatrix();
			parent.translate(x, y);
			parent.image(current_movie, 0,0);
		parent.popMatrix();
		parent.imageMode(parent.CENTER);
	}

	public void playSittingInit() {
		current_movie = init_movie;
		current_movie.play();
	}
	
	public void playSitting() {
		exit_movie.stop();
		exit_movie.goToBeginning();
	
		current_movie = sitting_movie;
		sitting_movie.play();
	}
	
	public void playExit() {
		sitting_movie.stop();
		sitting_movie.goToEnd();
		
		current_movie = exit_movie;
		exit_movie.play();
	}
	
	public boolean finishedExit() {
		double diff = Math.abs(exit_movie.time() - exit_movie.duration());
		
		return exit_movie.frame() != -1 && diff <= 0.003;
	}
}
