package GIS;

import java.awt.Image;
import java.util.ArrayList;

import Geom.Geom_element;
import Geom.Point3D;

/**
 * this class represent a pacman object in the pacman game.
 * each pacman as the following fields: point3D, id, speed, radius, path of points and image to show on the game screen.
 * @author Michael Lemberger, Liron Arad, Maoz Grossman.
 */
public class Pacman implements GIS_element{
	
	public Point3D _p;
	private int _id;
	private double _speed = 1;
	private double _radius = 1;
	public Path path;
	public Image _img= null;
	
	public Path getPath() {
		return path;
	}

	/**
	 * This constructor gets point, id, speed and radius as arguments and build an pacman object.
	 * @param p Point3D.
	 * @param id id number.
	 * @param speed speed progress.
	 * @param radius radius eating.
	 */
	public Pacman(Point3D p, int id, double speed, double radius) {
		this._id = id;
		this._p = p;
		this._radius = radius;
		this._speed = speed;
		this.path=new Path(new ArrayList<Point3D>());
		this.path.points.add(this.get_p());
	}
	
	/**
	 * This constructor gets point, id, speed, radius and image as arguments and build an pacman object.
	 * @param p Point3D.
	 * @param id id number.
	 * @param speed speed progress.
	 * @param radius radius eating.
	 * @param img image of a pacman.
	 */
	public Pacman(Point3D p, int id, double speed, double radius,Image img) {
		this._id = id;
		this._p = p;
		this._radius = radius;
		this._speed = speed;
		this.path=new Path(new ArrayList<Point3D>());
		this.path.points.add(this.get_p());
		this._img = img;
	}
	
	/**
	 * This constructor is a copy constructor. 
	 * the pacman gets point, id, speed, radius and path of another pacman and image.
	 * the constructor build an pacman object with the same fields with new image.
	 * @param p Point3D.
	 * @param id id number.
	 * @param speed speed progress.
	 * @param radius radius eating.
	 * @param img image of a pacman.
	 */
	public Pacman(Pacman pacman, Image img) {
		this._id = pacman.get_id();
		this._p = pacman.get_p();
		this._radius = pacman.get_radius();
		this._speed = pacman.get_speed();
		this.path=pacman.path;
		this.path.points.add(pacman.get_p());
		this._img = img;
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
	
	public Image get_img() {
		return _img;
	}
	public void set_img(Image _img) {
		this._img = _img;
	}
	@Override
	public String toString() {
		return "Pacman [_p=" + _p + ", _id=" + _id + ", _speed=" + _speed + ", _radius=" + _radius + "]";
	}
		
}
