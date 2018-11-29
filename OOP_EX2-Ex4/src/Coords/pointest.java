package Coords;

import File_format.CsvParser;
import GIS.GisLayer;
import GIS.GisMetaData;
import Geom.Point3D;

public class pointest {
	public static void main (String[]args) throws Exception {

		GisLayer csv = new GisLayer("C:\\Users\\liron\\Downloads\\Ex2\\Ex2\\data\\WigleWifi_20171201110209.csv");
		csv.writeFileKML("C:\\Users\\liron\\siout.kml");
	}
}
