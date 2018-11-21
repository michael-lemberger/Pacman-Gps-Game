package Coords;

import Geom.Point3D;

public class pointest {
public static void main (String[]args) {
	Point3D p=new Point3D(32.103315,35.209039);
	Point3D p1=new Point3D(32.106352,35.205225);
	CoordsFunctions c=new CoordsFunctions();
	System.out.println(c.distance3d(p, p1));
	
}
}
