package GIS;

import java.awt.image.BufferedImage;

import Geom.Geom_element;
import Geom.Point3D;

public class Fruit implements GIS_element{
	
	private Point3D _p;
	private int _id;
	public BufferedImage _img= null;


	public Fruit(Point3D p, int id) {
		this._id = id;
		this._p = p;	
	}
	
	public Fruit(Point3D p, int id, BufferedImage img) {
		this._id = id;
		this._p = p;
		this._img = img;
	}
	
	public Fruit(Fruit fruit, BufferedImage img) {
		this._id = fruit.get_id();
		this._p = fruit.get_p();
		this._img = img;
	}
	
	public BufferedImage get_img() {
		return _img;
	}
	public void set_img(BufferedImage _img) {
		this._img = _img;
	}
	public Point3D get_p() {
		return _p;
	}

	public int get_id() {
		return _id;
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
		return "Fruit [_p=" + _p + ", _id=" + _id + "]";
	}
}
