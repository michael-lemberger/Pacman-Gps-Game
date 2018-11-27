package Coords;

import Geom.Point3D;

public class pointest {
public static void main (String[]args) {
	//TEST
	Point3D p=new Point3D (32.103315,35.209039,670);
	Point3D vector= new Point3D (32.106352, 35.205225,650);
	CoordsFunctions c=new CoordsFunctions ();
	System.out.println("azimuth: "+c.azimuth_elevation_dist(p,vector)[0]+"degree , "+"elevation: "+c.azimuth_elevation_dist(p,vector)[1]+" , distance: "+c.azimuth_elevation_dist(p,vector)[2]/1000+"km");
	
	
}
}
