package GIS;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * this class represent a GIS project, the class implement the Gis_project interface.
 *  the project contains GIS layers, it implemented as a set structure and support all set functions.
 * in addition, the class support the ProjectToKml function to create a kml file from this Project.
 * 
 *  @author Michael Lemberger, Liron Arad, Maoz Grossman.
 *
 */
public class GisProject extends HashSet<GIS_layer> implements GIS_project {
	

	/**
	 * GisProject constructor.
	 * this project object contains GisLayers objects.
	 */

	public String toString() {
     String send="";
     Iterator <GIS_layer> it= this.iterator();
		int num=1;
     while(it.hasNext()) {
			GisLayer g=(GisLayer) it.next();
			System.out.println(g.size());
//			send="\nlayer "+num+"\n"+g.toString();
			num++;
		}
    
     return send;
     
	}

	/**
	 * this function get a output directory string and make a kml file from this project.
	 * @param output output directory.
	 * @throws Exception if there is any
	 */
	public void ProjectToKml(String output) throws Exception {
		ArrayList<String> content = new ArrayList<String>();
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" + "<Document>\n" + "<Folder>\n";

		String kmlend = "</Folder>\n"+"</Document>\n"+"</kml>";
		content.add(kmlstart);
		try{
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			Iterator <GIS_layer> it= this.iterator();
			while(it.hasNext()) {
				String icon= getIcon();
				GisLayer g=(GisLayer) it.next();
				Iterator <GIS_element> it1= g.iterator();
				while(it1.hasNext()) {
					GisElement e=(GisElement) it1.next();
					String[] s = e._metaData.getData();
					String kmlelement ="<Placemark>\n"
							+"<Style>\n<IconStyle>\n<scale>1.3</scale>\n<Icon>\n"
						     +" <href>"+icon+"</href>\n"
						    +"</Icon>\n"
						+" </IconStyle>\n"
						+"</Style>"
							+"<name>"+s[1]+"</name>\n" 
							+"<description>\n"
							+"<MAC>"+s[0]+"</MAC>\n"
							+"<AuthMode>"+s[2]+"</AuthMode>\n"
							+"<FirstSeen>"+s[3]+"</FirstSeen>\n"
							+"<Channel>"+s[4]+"</Channel>\n"
							+"<RSSI>"+s[5]+"</RSSI>\n"
							+"<AccuracyMeters>"+s[9]+"</AccuracyMeters>\n"
							+"<Type>"+s[10]+"</Type>\n"
							+"</description>\n" 
							+"<Point>\n" +
							"<coordinates>"+""+s[7]+","+s[6]+","+s[8]+"</coordinates>" +
							"</Point>\n" +
							"</Placemark>\n";
					content.add(kmlelement);
				}
			}
			content.add(kmlend);
			String csv = content.toString().replaceAll(", <", "  <").replace("[", "").replace("]", "");
			bw.write(csv);
			bw.close();
		}
		catch (IOException exp) {
			exp.printStackTrace();
		}
	}






	@Override
	public Meta_data get_Meta_data() {
		return null;
	}
	public String getIcon() {
		int i= (int) (Math.random()*8);
		String[]icon= {
				"http://maps.google.com/mapfiles/kml/pushpin/ylw-pushpin.png"
				,"http://maps.google.com/mapfiles/kml/pushpin/wht-pushpin.png"
				,"http://maps.google.com/mapfiles/kml/pushpin/red-pushpin.png"
				,"http://maps.google.com/mapfiles/kml/pushpin/purple-pushpin.png"
				,"http://maps.google.com/mapfiles/kml/pushpin/pink-pushpin.png"
				,"http://maps.google.com/mapfiles/kml/pushpin/ltblu-pushpin.png"
				,"http://maps.google.com/mapfiles/kml/pushpin/grn-pushpin.png"
				,"http://maps.google.com/mapfiles/kml/pushpin/blue-pushpin.png"
		};
			System.out.println(i);
		return icon[i];
	}
}