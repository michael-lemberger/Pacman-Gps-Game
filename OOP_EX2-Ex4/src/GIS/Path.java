package GIS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Geom.Point3D;

public class Path{
	public Set<Point3D> points=new HashSet<Point3D>();

	public Path(Set<Point3D> p) {
		this.points =p;
	}
	
	public Path() {
		
	}

	public double calc_distnce(){
		Iterator <Point3D> it=points.iterator();
		Point3D p0=null;
		if(it.hasNext()) {
		p0=it.next();
		}
		double distance=0;
		while(it.hasNext()) {
			Point3D p1=it.next();
			distance+=p0.distance3D(p1);
		}
		
		return distance;
	}

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
	public void add(Point3D point) {
		this.points.add(point);
	}

	@Override
	public String toString() {
		return "Path (size:"+points.size()+") [points=" + points + "]";
	}
	
	
}
