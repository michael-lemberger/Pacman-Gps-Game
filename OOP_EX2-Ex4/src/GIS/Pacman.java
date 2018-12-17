package GIS;

import java.util.HashSet;

import Geom.Geom_element;
import Geom.Point3D;

public class Pacman implements GIS_element{
	
	public Point3D _p;
	private int _id;
	private double _speed = 1;
	private double _radius = 1;
	public Path path;
	
	public Path getPath() {
		return path;
	}

	public Pacman(Point3D p, int id, double speed, double radius) {
		this._id = id;
		this._p = p;
		this._radius = radius;
		this._speed = speed;
		this.path=new Path(new HashSet<Point3D>());
		this.path.add(this.get_p());
	}

	public Point3D get_p() {
		return _p;
	}

	public int get_id() {
		return _id;
	}

	public double get_speed() {
		return _speed;
	}

	public double get_radius() {
		return _radius;
	}

	@Override
	public Geom_element getGeom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Meta_data getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public String toString() {
		return "Pacman [_p=" + _p + ", _id=" + _id + ", _speed=" + _speed + ", _radius=" + _radius + "]";
	}

	
}
