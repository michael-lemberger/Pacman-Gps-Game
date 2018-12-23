package GIS;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import Geom.Point3D;
/**
 * This class represent solution of a game.
 * The class have a function to print relevant data about the result of the game. 
 * @author Liron Arad, Michael Lemberger, Maoz Grossman.
 */
public class Solution extends ArrayList<Path> {
	Pacman pacman;
	int score;
	
	/**
	 * Téhis constructor get game and score of fruits that was eaten in the game. 
	 * @param pacman
	 * @param score
	 */
	public Solution (Pacman pacman, int score) {
		this.pacman=pacman;
		this.score=score;
		gameConsole();
	}
	/**
	 * This function prints score and other data to the console frame.
	 * @param score
	 */
	public void gameConsole(){
			System.out.println("-Pacman "+pacman.get_id()+"-");
			System.out.println("Fruit Points: "+pacman.path.points.toString());
			System.out.println("Fruit's Eaten: "+ score);
			System.out.println("Time: "+time(pacman));
	}
	
	/**
	 *This function computes the time taken to eat all fruits.
	 * @param time
	 * @param p
	 * @return time 
	 */
	public double time(Pacman p) {
		double time=p.path.calc_distnce(0,p.path.points.size()-1)-(p.get_radius()*p.path.points.size())/p.get_speed();
		return time;
	}
}
