package MainTest;

import Algorithms.MultiCsv;
import GIS.GisLayer;

public class MainTest {

	public static void main(String[] args) throws Exception {
		
		//enter a adress to create a kml project from the directories csv files.
		String adress1 = "C:\\Users\\liron\\Downloads\\Ex2";
		
		//create MultiCsv object
		MultiCsv m = new MultiCsv(adress1);
		
		//create the kml file from the MultiCsv, save it on this path.
		m.getProject().ProjectToKml("C:\\kmlProject.kml");
		
		/**************************************************************************************/
		
		//enter a adress to create a kml layer from the directories csv file.
		String adress2 ="C:\\Users\\liron\\Downloads\\Ex2-1\\Ex2\\data\\WigleWifi_20171201110209.csv";
		
		//create GisLayer object.
		GisLayer layer = new GisLayer(adress2);
		
		//create the kml file from the GisLayer, save it on this path.
		layer.LeyerToKML("C:\\kmlLayer.kml");
		
		

	}

}
