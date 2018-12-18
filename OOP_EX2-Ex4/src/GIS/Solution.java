package GIS;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import Geom.Point3D;

public class Solution extends ArrayList<Path> {
	Game game;
	
	public Solution (Game game) {
		this.game=game;
		gameConsole();
	}
	public void gameConsole(){
		Iterator<Pacman> it=game.pacmans.iterator();
		while(it.hasNext()) {
			Pacman p=(Pacman)(it.next());
			System.out.println("-Pacman "+p.get_id()+"-");
			System.out.println("Fruit Points: "+p.path.points.toString());
			System.out.println("Fruit's Eaten: "+ p.path.points.size());
			System.out.println("Time: "+time(p));
		}
	}
	
	public double time(Pacman p) {
		double time=p.path.calc_distnce()/p.get_speed();
		return time;
	}
}
