package GIS;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import Geom.Geom_element;
import Geom.Point3D;

/**
 * this class represent a fruit object in the pacman game.
 * each fruit as the following fields: point3D, id, image to show on the game screen.
 * @author Michael Lemberger, Liron Arad, Maoz Grossman.
 */
public class Fruit implements GIS_element{
	
	private Point3D _p;
	private int _id;
	public Image _img= null;


	public Fruit(Point3D p, int id) {
		this._id = id;
		this._p = p;	
	}
	
	public Fruit(Point3D p, int id, Image img) {
		this._id = id;
		this._p = p;
		this._img = img;
	}
	
	public Fruit(Fruit fruit, Image img) {
		this._id = fruit.get_id();
		this._p = fruit.get_p();
		this._img = img;
	}
	
	public Fruit(Fruit fr) {
		this._id = fr.get_id();
		this._img = fr.get_img();
		this._p = fr.get_p();
	}

	public Image get_img() {
		return _img;
	}
	/**
	 * Set method for _img.
	 * @param _img
	 */
	public void set_img(Image _img) {
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
