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
//		MyCoords m=new MyCoords();
//		Point3D Point= new Point3D(lon,lat);
//		double[] ans= m.azimuth_elevation_dist(super.get_p(), Point);
//		this.azimute = ans[0];
		
		
		/*couldn't use my azimuth function of MyCoords, 
		 * the board is too different from normal x,y coordinates   */
		double lon1=super.get_p().x();
		double lat1=super.get_p().y();
		double longDiff= lat-lat1;
		double y = Math.sin(longDiff)*Math.cos(lon);
		double x = Math.cos(lon1)*Math.sin(lon)-Math.sin(lon1)*Math.cos(lon)*Math.cos(longDiff);
		this.azimute = ( Math.toDegrees(Math.atan2(y, x)) +90 ) % 360;
		
	}
	
	
	
	
}
