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
		Iterator<GIS_element> it=game.pacmans.iterator();
		while(it.hasNext()) {
			Pacman p=(Pacman)(it.next());
			System.out.println("-Pacman "+p.get_id()+"-");
			System.out.println("Fruit Points: "+p.path.points.toString());
			System.out.println("Fruit's Eaten: "+ p.path.points.size());
			System.out.println("Time: "+time(p));
		}
	}
	
	public double time(Pacman p) {
		Set<Point3D> points=p.path.points;
		double time=0;
		time+=p._p.distance3D(p.path.get(0))/p.get_speed();
		time+=p.path.calc_distnce()/p.get_speed();
		return time;
	}
}
