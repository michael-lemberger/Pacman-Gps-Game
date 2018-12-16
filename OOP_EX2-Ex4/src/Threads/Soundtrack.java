package Threads;

import File_format.SimplePlayer;

public class Soundtrack {
	static String path = "res/Pac-man theme remix - By Arsenic1987.mp3";
	
	public static void main(String[] args) {
		SimplePlayer player = new SimplePlayer(path);
		Thread t = new Thread(player);
		t.start();
		
		
		for (int i = 0; i < 1000000; i++) {
			System.out.println(i);
		}
	}
}
