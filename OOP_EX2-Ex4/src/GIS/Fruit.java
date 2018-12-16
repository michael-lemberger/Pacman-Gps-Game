package GIS;

import Geom.Geom_element;
import Geom.Point3D;

public class Fruit implements GIS_element{
	
	@Override
	public String toString() {
		return "Fruit [_p=" + _p + ", _id=" + _id + "]";
	}

	private Point3D _p;
	private int _id;


	public Fruit(Point3D p, int id) {
		this._id = id;
		this._p = p;	
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
}
