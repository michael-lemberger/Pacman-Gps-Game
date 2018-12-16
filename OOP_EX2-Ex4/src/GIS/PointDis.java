package GIS;

import Geom.Point3D;

public class PointDis {
	public Point3D p;
	double distance;
	Fruit fe;
	int id;

	public PointDis(Fruit fe, double distance,int pacman_id) {
		this.fe=fe;
		this.distance=distance;
		id=pacman_id;
	}

	public Fruit getFe() {
		return fe;
	}


	public int getId() {
		return id;
	}

	public double getDistance() {
		return distance;
	}

}
