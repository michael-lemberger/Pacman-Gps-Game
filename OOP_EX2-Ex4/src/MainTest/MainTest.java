package MainTest;

import Algorithms.MultiCsv;
import GIS.GisLayer;

public class MainTest {

	public static void main(String[] args) throws Exception {
		
		//enter a adress to create a kml project from the directories csv files.
		String adress1 = "C:\\Users\\Simple Man\\Desktop\\ex2";
		
		//create MultiCsv object
		MultiCsv m = new MultiCsv(adress1);
		
		//create the kml file from the MultiCsv, save it on this path.
		m.getProject().ProjectToKml("C:\\Users\\Simple Man\\Desktop\\ex2\\kmlProject.kml");
		
		/**************************************************************************************/
		
		//enter a adress to create a kml layer from the directories csv file.
		String adress2 ="C:\\Users\\Simple Man\\Desktop\\ex2\\WigleWifi_20171201110209.csv";
		
		//create GisLayer object.
		GisLayer layer = new GisLayer(adress2);
		
		//create the kml file from the GisLayer, save it on this path.
		layer.LeyerToKML("C:\\Users\\Simple Man\\Desktop\\ex2\\kmlProject.kml");
		
		

	}

}
