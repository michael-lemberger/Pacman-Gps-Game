package Threads;



import File_format.SimplePlayer;

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
