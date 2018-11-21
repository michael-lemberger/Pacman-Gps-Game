package Coords;

import java.util.ArrayList;
import java.util.List;

import Geom.Point3D;

public class CoordsFunctions implements coords_converter {
	/** computes a new point which is the gps point transformed by a 3D vector (in meters)*/
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		Point3D p=new Point3D (D2M(gps));
		double x=p.x()+local_vector_in_meter.x();
		double y=p.y()+local_vector_in_meter.y();
		double z=p.z()+local_vector_in_meter.z();
		Point3D p1=new Point3D (x,y,z);
		return p1;
	}
	
	/** computes the 3D distance (in meters) between the two gps like points */
	public double distance3d(Point3D gps0, Point3D gps1) {
		
	}
	
	/** computes the 3D vector (in meters) between two gps like points */
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		
	}
	/** computes the polar representation of the 3D vector be gps0-->gps1 
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance*/
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		
	}
	/**
	 * return true iff this point is a valid lat, lon , lat coordinate: [-180,+180],[-90,+90],[-450, +inf]
	 * @param p
	 * @return
	 */
	public boolean isValid_GPS_Point(Point3D p) {
	
	}
	private Point3D D2M(Point3D gps) {
		
		double x=(Math.sin(gps.d2r(gps.x())))*EarthP;
		double y=(Math.sin(gps.d2r(gps.y())))*EarthP*(Math.cos((gps.x()*Math.PI)/180));
		
		Point3D p=new Point3D(x,y,gps.z());
		return p;
	}
	
	private List GpsList=new ArrayList();
	private final int EarthP=6371000;
}
