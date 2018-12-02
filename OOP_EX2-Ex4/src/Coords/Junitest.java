package Coords;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Geom.Point3D;

class Junitest {

	@Test
	void test_MyCoords() {
		//add
		Point3D p=new Point3D (32.103315,35.209039,670);
		Point3D vector= new Point3D (337.6989921,-359.2492069,-20);
		MyCoords c=new MyCoords(); 
		Point3D p2=c.add(p,vector);
		Point3D result=new Point3D (32.106352,35.205225,650);
		assertEquals((Math.round(result.x()*1000000)/1000000.0),(Math.round(p2.x()*1000000)/1000000.0));
		assertEquals((Math.round(result.y()*1000000)/1000000.0),(Math.round(p2.y()*1000000)/1000000.0));
		assertEquals(result.z(),p2.z());
		
		//Vector3D
		p2=c.vector3D(p, result);
		assertEquals((Math.round(vector.x()*1000000)/1000000.0),(Math.round(p2.x()*1000000)/1000000.0));
		assertEquals((Math.round(vector.y()*1000000)/1000000.0),(Math.round(p2.y()*1000000)/1000000.0));
		assertEquals(vector.z(),p2.z());
		
		//azimuth_elevation_dist
		double [] d=c.azimuth_elevation_dist(p, result);
		Point3D azimuth= new Point3D(d[0],d[1],d[2]/1000);
		result=new Point3D(313.23,-2.323,0.493);
		assertEquals((Math.round(result.x()*1000)/1000.0),(Math.round(azimuth.x()*1000)/1000.0));
		assertEquals((Math.round(result.y()*1000)/1000.0),(Math.round(azimuth.y()*1000)/1000.0));
		assertEquals(result.z(),(Math.round(azimuth.z()*1000)/1000.0));
		
		//isValid
		assertFalse(c.isValid_GPS_Point(new Point3D(190,0,0)));
		assertFalse(c.isValid_GPS_Point(new Point3D(0,100,0)));
		assertFalse(c.isValid_GPS_Point(new Point3D(0,0,8849)));
		assertFalse(c.isValid_GPS_Point(new Point3D(-190,0,0)));
		assertFalse(c.isValid_GPS_Point(new Point3D(0,-100,0)));
		assertFalse(c.isValid_GPS_Point(new Point3D(0,0,-8849)));
		assertTrue(c.isValid_GPS_Point(new Point3D(1,0,0)));
	}

}
