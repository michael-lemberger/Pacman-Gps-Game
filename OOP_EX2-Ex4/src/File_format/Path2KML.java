package File_format;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import GIS.GIS_element;
import GIS.GisElement;
import GIS.Path;
import Geom.Point3D;

public class Path2KML {
	
	Path path;
	
	public Path2KML(Path path,String output) {
		this.path=path;
		PathToKML(output);
	}
	
	public  void PathToKML( String output) {
		ArrayList<String> content = new ArrayList<String>();
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" + "<Document>";
		content.add(kmlstart);

		String kmlend = "</Document>\n"+"</kml>";
		try{
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			Iterator <Point3D> it1=path.points.iterator();
			while(it1.hasNext()) {
				Point3D p=it1.next();
				
				String kmlelement ="<Placemark>\n"+ 
						"<coordinates>"+p.toString()+"</coordinates>" +
						"</Placemark>\n";
				content.add(kmlelement);
			}
			content.add(kmlend);
			String csv = content.toString().replaceAll(", <", "  <").replace("[", "").replace("]", "");
			bw.write(csv);
			bw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
