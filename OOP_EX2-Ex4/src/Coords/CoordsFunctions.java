package Coords;

import java.util.ArrayList;
import java.util.List;

import Geom.Point3D;

public class CoordsFunctions implements coords_converter {
	/** computes a new point which is the gps point transformed by a 3D vector (in meters)*/
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {//לללכת מספר מסוים של צעדים צפונה או דרומה ומספר צעדים מסויים מזרחה או מערבה ולהחזיר את המיקום החדש בנקודת ג'יפיאס
		Point3D p=new Point3D (0,0,0);
		try {
			p = new Point3D (D2M(gps));
		} catch (Exception e) {
			System.out.println("worng output");
			e.printStackTrace();
		}
		double x=p.x()+local_vector_in_meter.x();
		double y=p.y()+local_vector_in_meter.y();
		double z=p.z()+local_vector_in_meter.z();
		Point3D p1=new Point3D (x,y,z);
		
		return p1;
	}
	
	
	/** computes the 3D distance (in meters) between the two gps like points */
	public double distance3d(Point3D gps0, Point3D gps1) {
		double x=Math.sin(Point3D.d2r(gps1.x()-gps0.x()))*EarthR;
		double y=Math.sin(Point3D.d2r(gps1.x()-gps0.x()))*EarthR*(Math.cos((gps0.x()*Math.PI)/180));
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
		
		if(p.x()>180||p.x()<-180) 
			return false;
		
		if(p.y()>90||p.y()<-90)
			return false;
		
		if(p.z()>8848||p.z()<-450) 
			return false;
					
		return true;
		
	}
	/**
	 * by the function in the excel file
	 * we convert the 
	 * @param gps
	 * @param y
	 * @return
	 */
	
	public	Point3D D2M(Point3D gps)throws Exception {
		if(isValid_GPS_Point(gps)==false) {
			throw new Exception ("gps point incorrect!");
		}
		double x=Math.sin(Point3D.d2r(gps.x()))*Math.cos(Point3D.d2r(gps.y()))*EarthR;
		double y=Math.sin(Point3D.d2r(gps.x()))*Math.sin(Point3D.d2r(gps.y()))*EarthR;
		double z=Math.cos(Point3D.d2r(gps.x()))*EarthR;
		Point3D p=new Point3D(x,y,z);
		return p;
	}
	public	Point3D M2D(Point3D gps) throws Exception {
		double x=Math.sqrt((gps.x()*gps.x())+(gps.y()*gps.y()));
		x=Point3D.r2d(x);
		
		double y;
		if(gps.x()>0) {
			y=Point3D.r2d(Math.atan(gps.y()/gps.x()));
		}
		else{
			y=Point3D.r2d(Math.atan(gps.y()/gps.x())+Math.PI);
		}
		
		double z=Math.asin(gps.x()/EarthR);
		z=Point3D.r2d(z);
		
		Point3D p=new Point3D(x,y,z);
		if(isValid_GPS_Point(p)==false) {
			throw new Exception ("gps point incorrect!");
		}
		
		return p;
		
		
	}
	
	private List GpsList=new ArrayList();
	private final int EarthR=6371000;
}
