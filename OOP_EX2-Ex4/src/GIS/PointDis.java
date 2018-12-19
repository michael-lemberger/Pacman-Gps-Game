package GIS;

import Geom.Point3D;
	/**
	 * PointDis store data related to a fruit:
	 * 1.time for a pcaman to eat that fruit
	 * 2.fruit
	 * 3.pacman id in order to relate to a pacman list.
	 * @author Liron Arad,Michael Lemberger,Maoz Grossman
	 *
	 */
public class PointDis {
	double time;
	Fruit fe;
	int id;
	
	/**
	 * Constructor
	 * @param fe
	 * @param time
	 * @param pacman_id
	 */
	public PointDis(Fruit fe, double time,int pacman_id) {
		this.fe=fe;
		this.time=time;
		id=pacman_id;
	}
	
	/**
	 * 
	 * @return fruit
	 */
	public Fruit getFe() {
		return fe;
	}

	/**
	 * 
	 * @return pacman id
	 */
	public int getId() {
		return id;
	}
	/**
	 * 
	 * @return	time eating a fruit
	 */
	public double getTime() {
		return time;
	}

}
