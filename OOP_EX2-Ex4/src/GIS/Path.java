package GIS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import Geom.Point3D;

/**
 *  This class represent a Path that  store a list of points.
 *  Those Points represent the fruits eaten by a pacman.
 *  The first point in the list represent the pacman's point.
 * @author Liron Arad,Michael Lemberger,Maoz Grossman.
 */
public class Path{
	public ArrayList<Point3D> points=new ArrayList<Point3D>();
	
	/**
	 * This constructor gets a list of points to store.
	 * @param p list of points.
	 */
	public Path(ArrayList<Point3D> p) {
		this.points =(ArrayList<Point3D>) p;
	}
	
	/**
	 * This constructor is An empty constructor.
	 */
	public Path() {
		
	}

	/**
	 * This function calculate distannce between two points in the path.
	 * @param from
	 * @param to
	 * @return
	 */
	public double calc_distnce(int from,int to){
		Iterator <Point3D> it=points.iterator();
		Point3D p0=null;
		int counter=0;
		if(it.hasNext()&&from==0) {
		p0=it.next();
		}
		else {
			while(it.hasNext()&&counter<from) {
				p0=it.next();
				counter++;
			}
		}
		double distance=0;
		while(it.hasNext()&&counter<to) {
			Point3D p1=it.next();
			distance+=(p0.distance2D(p1));
			p0=p1;
		}
		
		return distance;
	}

	/**
	 * This function return point in the i'th place in the path.
	 * @param i index number in the path.
	 * @return point in the path.
	 */
	public Point3D get(int i) {
		Iterator<Point3D> it=points.iterator();
		int counter=0;
		Point3D p=null;
		while (it.hasNext()) {
			p = (Point3D) it.next();
			if(counter==i) {
				break;
			}
		}
		return p;
	}
	

	@Override
	/**
	 * toString function to print the points in the path.
	 */
	public String toString() {
		return "Path (size:"+points.size()+") [points=" + points + "]";
	}
	
	
}
