package Threads;


import java.util.ArrayList;
import java.util.Iterator;

import Algorithms.ShortestPathAlgo;
import GIS.Map;
import GIS.Pacman;
import GUI.MyFrame;

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
		runnig=false;
		}
	}
	public void close() {
		runnig=false;
	}
}
