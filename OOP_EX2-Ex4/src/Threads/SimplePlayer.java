package Threads;

	import javazoom.jl.player.*;
	import java.io.FileInputStream;

	/**
	 * This class play the Background music of the game.
	 * the class implements Runnable class and work as thread.
	  * @author Liron Arad, Michael Lemberger, Maoz Grossman.
	 *
	 */
	public class SimplePlayer implements Runnable{

	    private String path;

		public SimplePlayer(String path){
	    	this.path = path;
	    }
		
	    public void play()
	    {
	        try{

	             FileInputStream fis = new FileInputStream(path);
	             Player playMP3 = new Player(fis);

	             playMP3.play();

	        }  catch(Exception e){
	             System.out.println(e);
	        }
	    }

		@Override
		public void run() {
			while(true)
			play();
		} 
	}
