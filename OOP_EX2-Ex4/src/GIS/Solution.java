package GIS;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import Geom.Point3D;
/**
 * Solution prints relevant data about a pacman. 
 * @author Liron Arad, Michael Lemberger, Maoz Grossman.
 */
public class Solution extends ArrayList<Path> {
	Game game;
	int score;
	/**
	 * Constructor
	 * @param game
	 * @param score
	 */
	public Solution (Game game,int score) {
		this.game=game;
		this.score=score;
		gameConsole();
	}
	/**
	 * Prints score and other data
	 * @param score
	 */
	public void gameConsole(){
		Iterator<Pacman> it=game.pacmans.iterator();
		while(it.hasNext()) {
			Pacman p=(Pacman)(it.next());
			System.out.println("-Pacman "+p.get_id()+"-");
			System.out.println("Fruit Points: "+p.path.points.toString());
			System.out.println("Fruit's Eaten: "+ score);
			System.out.println("Time: "+time(p));
		}
	}
	
	/**
	 * Computes the time taken to eat all fruits.
	 * @param time
	 * @param p
	 * @return time 
	 */
	public double time(Pacman p) {
		double time=p.path.calc_distnce(0,p.path.points.size()-1)-(p.get_radius()*p.path.points.size())/p.get_speed();
		return time;
	}
}
