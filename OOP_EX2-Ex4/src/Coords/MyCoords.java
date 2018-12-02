package Coords;

import java.util.ArrayList;
import java.util.List;

import Geom.Point3D;

public class MyCoords implements coords_converter {
	/**
	 * computes a new point which is the gps point transformed by a 3D vector (in meters)
	 * @return point3d that represents the sum 
	 */
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		
		Point3D p=new Point3D (0,0,0);//new point converted to meters
		try {
			p = new Point3D (M2D(local_vector_in_meter,gps.x()));
		} catch (Exception e) {
			System.out.println("worng output");
			e.printStackTrace();
		}
		double x=p.x()+gps.x();
		double y=p.y()+gps.y();
		double z=p.z()+gps.z();
		p=new Point3D (x,y,z);
		return p;
	}
	
	
	/** 
	 * computes the 3D distance (in meters) between the two gps like points
	 *@return the distance in Double 
	 *@throws RuntimeException isValid
	 */
	public double distance3d(Point3D gps0, Point3D gps1) throws RuntimeException {
		if(!isValid_GPS_Point(gps0)||!isValid_GPS_Point(gps1)) {
			throw new RuntimeException("Invalid Point!");
			}
		double x=D2M(gps0,gps1.x()-gps0.x());
		double y=(D2M(gps0,gps1.y()-gps0.y()))*(Math.cos((gps0.x()*Math.PI)/180));
		double z= gps1.z()-gps0.z();
		double distance=Math.sqrt((x*x)+(y*y)+(z*z));
		return distance;
	}
	
	/** computes the 3D vector (in meters) between two gps like points 
	 * 
	 * @return point3d vector 
	 * @throws RuntimeException isValid
	 */
	public Point3D vector3D(Point3D gps0, Point3D gps1) throws RuntimeException {
		if(!isValid_GPS_Point(gps0)||!isValid_GPS_Point(gps1)) {
			throw new RuntimeException("Invalid Point!");
			}
		double deltaX = Math.toRadians(gps1.x() - gps0.x());
		double deltaY = Math.toRadians(gps1.y() - gps0.y());
		double deltaZ = gps1.z() - gps0.z();

		deltaX = Math.sin(deltaX)*EarthR;

		double lonNorm = Math.cos(gps0.x()*Math.PI/180);
		deltaY = Math.sin(deltaY)*EarthR*lonNorm;

		Point3D p = new Point3D(deltaX,deltaY,deltaZ);

		return p;	
	}
	/** 
	 * computes the polar representation of the 3D vector be gps0 to gps1 
	 * Code based on "https://stackoverflow.com/questions/9457988/bearing-from-one-coordinate-to-another";
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance
	 * @return Double array of azimuth elevation
	 * @throws RuntimeException isValid
	 */
public double[] azimuth_elevation_dist (Point3D gps0, Point3D gps1) throws RuntimeException {
		
		if(!isValid_GPS_Point(gps0)||!isValid_GPS_Point(gps1)) {
		throw new RuntimeException("Invalid Point!");
		}
		
		double distance=this.distance3d(gps0,gps1);
		double z=(gps1.z()-gps0.z());
		double elevation=Point3D.r2d(Math.asin(z/distance3d(gps0,gps1)));
		
		double x0r=Point3D.d2r(gps0.x());
		double x1r=Point3D.d2r(gps1.x());
		double dlamda=Point3D.d2r(gps1.y()-gps0.y());
		double a= Math.sin(dlamda)*Math.cos(x1r);
		double b= Math.cos(x0r)*Math.sin(x1r)-Math.sin(x0r)*Math.cos(x1r)*Math.cos(dlamda);
		double azimuth=(Point3D.r2d(Math.atan2(a, b))+360)%360;
		
		
		double[] ans= {azimuth,elevation,distance};
		return ans;
	}
	/**
	 * return true iff this point is a valid lat, lon , lat coordinate: [-180,+180],[-90,+90],[-450, +inf]
	 * @param p point3d
	 * @return boolean if true
	 */
	public boolean isValid_GPS_Point(Point3D p) {
		
		if(p.x()>180||p.x()<-180) 
			return false;
		
		if(p.y()>90||p.y()<-90)
			return false;
		
		if(p.z()>8848||p.z()<-450) 
			return false;
					
		return true;
		
	}
	
	/**
	 * from cartesian to polar(https://brilliant.org/wiki/convert-cartesian-coordinates-to-polar/)
	 * @param gps point3d vector
	 * @param num Double to compute
	 * @return point3d vector
	 * @throws Exception if their is a problem
	 */
	public	Point3D M2D(Point3D gps,double num) throws Exception {
		
		double x=((gps.x()/(2*Math.PI*EarthR)))*360;
		double y=((gps.y()/(2*Math.PI*EarthR))*360)/Math.cos(num*Math.PI/180);
		double z=gps.z();
		System.out.println(x+","+y+","+z);
		
		Point3D p=new Point3D(x,y,z);
		if(isValid_GPS_Point(p)==false) {
			throw new Exception ("gps point incorrect!");
		}
		
		return p;
	}
	/**
	 * from degree to meters converter 
	 * @param gps point3d
	 * @param a Double meter
	 * @return x value in meter
	 */
	public	double D2M(Point3D gps,double a) {
		double x=(Math.sin(gps.d2r(a)))*EarthR;
		return x;
	}
	private List GpsList=new ArrayList();
	private final int EarthR=6371000;
	private final int EarthP=40075000;
}
