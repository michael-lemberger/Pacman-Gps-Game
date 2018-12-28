package GIS;

import java.awt.Image;

import Coords.MyCoords;
import Geom.Point3D;

public class Player extends Pacman {
	public double azimute;
	
	public Player() {
		super(null,0, 0, 0);
		azimute=0;
	}
	
	public Player(Point3D p, int id, double speed, double radius, Image img) {
		super(p, id, speed, radius, img);
		azimute=0;
	}

	

	public double getAzimute() {
		return azimute;
	}

	public void setAzimute(double lon,double lat) {
		MyCoords m=new MyCoords();
		Point3D Point= new Point3D(lon,lat);
		double[] ans= m.azimuth_elevation_dist(super.get_p(), Point);
		this.azimute = ans[0];
	}

	
	
}
