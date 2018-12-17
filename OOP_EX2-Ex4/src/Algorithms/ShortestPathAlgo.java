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

public class ShortestPathAlgo {

	public ArrayList<GIS_element> fruits=new ArrayList<GIS_element>();
	public Game game;

	public ShortestPathAlgo(Game game) {
		this.game=game;
		this.fruits=game.fruits;
		pacmanPath();
	}

	public int pacmanPath() {
		if(game.fruits.isEmpty()) {
			return 0;
		}

		PointDis[]pd=new PointDis[game.pacmans.size()];
		Iterator<Pacman> it = game.pacmans.iterator();
		int counter=0;
		while(it.hasNext()) {
			Pacman p=(Pacman) it.next();
			double dis=Integer.MAX_VALUE;
			double dishort;
			Fruit f=null;
			Iterator<GIS_element> it1 = this.fruits.iterator();
			while(it1.hasNext()) {
				Fruit f1=(Fruit) it1.next();
				dishort=p.get_p().distance3D(f1.get_p());
				if(dishort<dis) {
					dis=dishort;
					f=f1;
				}
			}
			pd[counter]=new PointDis(f,dis/p.get_speed(),p.get_id());
			counter++;
		}
		for (int i = 0; i < pd.length-1; i++) {
			if(pd[i].getTime()<pd[i+1].getTime()) {
				Point3D x=pd[i].getFe().get_p();
				Pacman p=game.pacmans.get(pd[i].getId());
				p.path.add(x);
				fruits.remove(pd[i].getFe());
			}
			if(pd[i].getTime()>=pd[i+1].getTime()) {
				Point3D x=pd[i+1].getFe().get_p();
				Pacman p=game.pacmans.get(pd[i+1].getId());
				p.path.add(x);
				fruits.remove(pd[i+1].getFe());
			}
		}
		return pacmanPath();
	}

	public double getScore(Pacman pac) {
		double score=pac.path.points.size();
		return score;
	}
}