package GIS;

import Geom.Point3D;
	/**
	 * This class represent PointDis object.
	 * this object store data related to a fruit:
	 * 1.time for a pcaman to eat that fruit.
	 * 2.fruit.
	 * 3.pacman id in order to relate in a pacmans list.
	 * @author Liron Arad,Michael Lemberger,Maoz Grossman.
	 *
	 */
public class PointDis {
	double time;
	Fruit fe;
	int id;
	
	/**
	 *this constructor get fruit, time and id of a pacman and build an PointDis object.
	 * @param fe fruit.
	 * @param time time for a pcaman to eat the fruit.
	 * @param pacman_id pacman id.
	 */
	public PointDis(Fruit fe, double time,int pacman_id) {
		this.fe=fe;
		this.time=time;
		id=pacman_id;
	}
	
	/**
	 * @return fruit
	 */
	public Fruit getFe() {
		return fe;
	}

	/**
	 * @return pacman id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return	time eating a fruit
	 */
	public double getTime() {
		return time;
	}

}
