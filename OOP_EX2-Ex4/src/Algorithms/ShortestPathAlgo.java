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
import Geom.Point3D;

public class ShortestPathAlgo extends ArrayList<GIS_element> {
	public Game game;
	
	public ShortestPathAlgo(Game game) {
		this.game=game;
		this.addAll(game.fruits);
		Algorithm();
	}

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
				p=game.pacmans.get(p0.getId());
			}
			else if(p0.getTime()>=p1.getTime()) {
				x=p1.getFe().get_p();
				p=game.pacmans.get(p1.getId());
				p0=p1;
			}
		}
		p.path.add(x);
		int index=this.lastIndexOf(p0.getFe());
		this.remove(index);

		return Algorithm();
	}

	public void ClosestFruit(ArrayList <PointDis> pd) {
		Iterator<Pacman> it = game.pacmans.iterator();
		while(it.hasNext()) {
			Pacman p=(Pacman) it.next();
			double dis=Integer.MAX_VALUE;
			double dishort;
			Fruit f=null;
			Iterator<GIS_element> it1 = this.iterator();
			while(it1.hasNext()) {
				Fruit f1=(Fruit) it1.next();
				dishort=p.get_p().distance3D(f1.get_p());
				if(dishort<dis) {
					dis=dishort;
					f=f1;
				}
			}
			pd.add(new PointDis(f,(dis-p.get_radius())/p.get_speed(),p.get_id()));
		}
	}

	public double getScore(Pacman pac) {
		double score=pac.path.points.size()-1;
		return score;
	}
	
}