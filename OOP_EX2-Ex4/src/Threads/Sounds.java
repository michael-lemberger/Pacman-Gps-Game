package Threads;

public class Sounds {
		static String path = "res/crunch.mp3";
		public Sounds() {
			
		}
		public void crounch() {
			SimplePlayer player = new SimplePlayer(path);
			Thread t = new Thread(player);
			t.start();
			
		}
}
