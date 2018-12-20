package Threads;


import java.util.ArrayList;
import java.util.Iterator;

import Algorithms.ShortestPathAlgo;
import GIS.Pacman;
import GUI.MyFrame;

/**
 * This class represent the main thread that play the game.
 * the class as list of threads, thread for each pacman in the game.
 * @author Liron Arad, Michael Lemberger, Maoz Grossman.
 *
 */
public class playThread extends Thread {
	MyFrame mf;
	ArrayList<SinglePacman>thr=new ArrayList<>();
	boolean runnig=true;
	public playThread(MyFrame mf) {
	this.mf=mf;
	}
	@Override
	public void run() {
		while(runnig) {
		//would be in the main thread
		if(!mf.game.pacmans.isEmpty()) {
			
		ShortestPathAlgo algo= new ShortestPathAlgo(mf.game);
		Iterator<Pacman>it=mf.game.pacmans.iterator();
		mf.status=3;
		while(it.hasNext()) {
			Pacman pacman= it.next();
			thr.add(new SinglePacman(pacman, mf));
			
		}
		Iterator<SinglePacman>it2= thr.iterator();
		while(it2.hasNext()) {
			SinglePacman sp= it2.next();
			sp.start();
			
		}
		}
		
		close();
		
		}
	}
	
	
	
	public void close() {
		runnig=false;
	}
}
