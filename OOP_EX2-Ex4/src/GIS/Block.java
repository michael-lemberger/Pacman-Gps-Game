package GIS;

import Geom.Point3D;

public class Block extends Map {
	int id;
	double radius;
	Point3D start,end;
	public Block(Point3D start,Point3D end,int id,double radius) {
		super(0, 0);
		this.start=start;
		this.end=end;
		this._rangeLat = new Range(start.y(),end.y());
		this._rangeLon = new Range(start.x(),end.y());
		this.id=id;
		this.radius=radius;
	}
}
