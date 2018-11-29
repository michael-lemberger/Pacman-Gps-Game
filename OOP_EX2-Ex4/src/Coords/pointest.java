package Coords;

import File_format.CsvParser;
import GIS.GisLayer;
import GIS.GisMetaData;
import Geom.Point3D;

public class pointest {
public static void main (String[]args) throws Exception {
//	//TEST
//	Point3D p=new Point3D (32.103315,35.209039,670);
//	Point3D vector= new Point3D (32.106352, 35.205225,650);
//	MyCoords c=new MyCoords ();
//	//System.out.println("azimuth: "+c.azimuth_elevation_dist(p,vector)[0]+"degree , "+"elevation: "+c.azimuth_elevation_dist(p,vector)[1]+" , distance: "+c.azimuth_elevation_dist(p,vector)[2]/1000+"km");
//	CsvParser cs=new CsvParser();
//	try {
//	String tis[][]	=cs.csvmaker("C:\\Users\\Simple Man\\git\\OOP_EX2-EX4-1\\OOP_EX2-Ex4\\src\\File_format\\WigleWifi_20171203085618.csv");
//	System.out.println(new GisMetaData(tis[1]));
//	
//	}
//	catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	
//
	GisLayer csv = new GisLayer("C:\\Users\\Simple Man\\git\\OOP_EX2-EX4-1\\OOP_EX2-Ex4\\src\\File_format\\WigleWifi_20171203085618.csv");
	csv.writeFileKML("C:\\Users\\Simple Man\\Desktop\\WigleWifi_20171203085618.kml");
}
}
