package Coords;

import File_format.CsvParser;
import GIS.GisLayer;
import GIS.GisMetaData;
import Geom.Point3D;

public class pointest {
	public static void main (String[]args) throws Exception {

		GisLayer csv = new GisLayer("C:\\Users\\user\\Desktop\\test\\123.csv");
		csv.writeFileKML("C:\\Users\\user\\Desktop\\test\\siout.kml");
	}
}
