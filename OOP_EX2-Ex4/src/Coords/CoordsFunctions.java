package Coords;

import java.util.ArrayList;
import java.util.List;

import Geom.Point3D;

public class CoordsFunctions implements coords_converter {
	/** computes a new point which is the gps point transformed by a 3D vector (in meters)*/
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {//לללכת מספר מסוים של צעדים צפונה או דרומה ומספר צעדים מסויים מזרחה או מערבה ולהחזיר את המיקום החדש בנקודת ג'יפיאס
		Point3D p=new Point3D (D2M(gps,gps.x()),D2M(gps,gps.y()),gps.z());
		double x=M2D(gps, p.x()+local_vector_in_meter.x());
		double y=M2D(gps, p.y()+local_vector_in_meter.y());
		double z=p.z()+local_vector_in_meter.z();
		Point3D p1=new Point3D (x,y,z);
		return p1;
	}
	
	
	/** computes the 3D distance (in meters) between the two gps like points */
	public double distance3d(Point3D gps0, Point3D gps1) {
		double x=D2M(gps0,gps1.x()-gps0.x());
		double y=(D2M(gps0,gps1.y()-gps0.y()))*(Math.cos((gps0.x()*Math.PI)/180));
		double distance=Math.sqrt((x*x)+(y*y));
		return distance;
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
	/**
	 * by the function in the excel file
	 * we convert the 
	 * @param gps
	 * @param y
	 * @return
	 */
	public	double D2M(Point3D gps,double a) {
		double x=(Math.sin(gps.d2r(a)))*EarthP;
		return x;
	}
	public double M2D(Point3D gpsM,double a){//יכול להיות שהלן לום לא קשור צריך לבדוק
		double x=Math.asin(a/EarthP);
		gpsM.r2d(x);
		return x;
	}
	public	Point3D D2M(Point3D gps) {
		double x=D2M(gps,gps.x());
		double y=D2M(gps,gps.y());
		Point3D p=new Point3D(x,y,gps.z());
		return p;
	}
	public	Point3D M2D(Point3D gps) {
		double x=M2D(gps,gps.x());
		double y=M2D(gps,gps.y());
		Point3D p=new Point3D(x,y,gps.z());
		return p;
	}
	private List GpsList=new ArrayList();
	private final int EarthP=6371000;
}
