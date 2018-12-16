package GIS;

import Geom.Point3D;

public class PointDis {
	double time;
	Fruit fe;
	int id;

	public PointDis(Fruit fe, double time,int pacman_id) {
		this.fe=fe;
		this.time=time;
		id=pacman_id;
	}

	public Fruit getFe() {
		return fe;
	}


	public int getId() {
		return id;
	}

	public double getTime() {
		return time;
	}

}
