package Threads;

import Threads.SimplePlayer;

/**
 * This class represent the sount of eating fruit by the pacman.
 * @author Liron Arad, Michael Lemberger, Maoz Grossman.
 */
public class sounds {
		static String path = "res/crunch.mp3";
		public sounds() {
			
		}
		
		public void crounch() {
			SimplePlayer player = new SimplePlayer(path);
			Thread t = new Thread(player);
			t.start();
			
		}
}
