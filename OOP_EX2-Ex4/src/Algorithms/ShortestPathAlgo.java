package Algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import GIS.Fruit;
import GIS.GIS_element;
import GIS.Game;
import GIS.Pacman;
import GIS.PointDis;
import GIS.Solution;
import Geom.Point3D;
/**
 * Main algorithm That computes the path every pacman should travel on in relation to time. 
 * @author Liron Arad, Michael Lemberger, Maoz Grossman.
 *
 */
public class ShortestPathAlgo extends ArrayList<GIS_element> {
	public Game game;
	/**
	 * Constructor for "ShortestPathAlgo"
	 * @param game
	 */
	public ShortestPathAlgo(Game game) {
		this.game=game;
		this.addAll(game.fruits);
		Algorithm();
	}
	/**
	 * Greedy Algorithm, algorithm That computes the path every pacman should travel on. 
	 * @param ClosestFruit the closest fruit to every pacman.
	 * @return a stop condition.
	 */
	public int Algorithm() {
		if(this.isEmpty()) {
			return 0;
		}
		ArrayList <PointDis>ClosestFruit=new ArrayList<PointDis>();
		ClosestFruit(ClosestFruit);
		Point3D x=null;
		Pacman p=null;
		Iterator<PointDis> it1 = ClosestFruit.iterator();
		PointDis p0=new PointDis(new Fruit(new Point3D(1,0,0),0),Double.MAX_VALUE,Integer.MIN_VALUE);
		while(it1.hasNext()) {
			PointDis p1=it1.next();
			if(p0.getTime()<p1.getTime()) {
				x=p0.getFe().get_p();
				p=game.getPac(p0.getId());
			}
			else if(p0.getTime()>=p1.getTime()) {
				x=p1.getFe().get_p();
				p=game.getPac(p1.getId());
				p0=p1;
			}
		}
		p.path.points.add(x);
		int index=this.lastIndexOf(p0.getFe());
		this.remove(index);

		return Algorithm();
	}

	/**
	 * Computes the closest fruit to every pacman.
	 * @param pd a list of PointDis.
	 */
	public void ClosestFruit(ArrayList <PointDis> pd) {
		Iterator<Pacman> it = game.pacmans.iterator();
		while(it.hasNext()) {
			Pacman p=(Pacman) it.next();
			Point3D compare=p.path.get(p.path.points.size()-1);
			double dis=Integer.MAX_VALUE;
			double dishort;
			Fruit f=null;
			Iterator<GIS_element> it1 = this.iterator();
			while(it1.hasNext()) {
				Fruit f1=(Fruit) it1.next();
				dishort=Math.abs(compare.distance2D(f1.get_p()));
				if(dishort<dis) {
					dis=dishort;
					f=f1;
				}
			}
			double road=p.path.calc_distnce(0,p.path.points.size());
			pd.add(new PointDis(f,(((dis*1000)+(road*1000))-p.get_radius())/p.get_speed(),p.get_id()));
			
		}
	}
	
	/**
	 * Computes the number of fruits eaten by a lone pacman.
	 * @param pac Pacman
	 * @return number of fruits eaten.
	 */
	public int getScore(Pacman pac) {
		int score=pac.path.points.size()-1;
		Solution s=new Solution (game,score);
		return score;
	}
	
}