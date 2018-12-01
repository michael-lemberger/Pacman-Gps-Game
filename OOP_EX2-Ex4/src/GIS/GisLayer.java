package GIS;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import File_format.CsvParser;
import Geom.Point3D;

/**
 * this class represent a GIS layer, the class implement the Gis_layer interface.
 *  the layer contains GIS elements, it implemented as a set structure and support all set functions.
 * in addition, the class support the LayerToKml function to create a kml file from this layer.
 * 
 *  @author Michael Lemberger, Liron Arad, Maoz Grossman.
 *
 */
public class GisLayer implements GIS_layer {
	private CsvParser csv=new CsvParser();
	private static Set<GIS_element> Elements=new HashSet<GIS_element>();
	public static Set<GisElement> se =new HashSet<GisElement>();

	/**
	 * GisLayer constructor. get a string directory (csv file) and create GisLayer object.
	 * this layer object contains GisElements objects.
	 * @param directory a csv directory path.
	 * @throws Exception
	 */
	public  GisLayer(String directory) throws Exception {

		String s[][]=csv.csvmaker(directory);
		for (int i = 1; i < s.length-1; i++) {		
			GisMetaData metadata =new GisMetaData(s[i]);
			String point=""+s[i][6]+","+s[i][7]+","+s[i][8]+"";
			Point3D p=new Point3D (point);		
			GisElement element= new GisElement(p, metadata);
			add(element);			
		}


	}

	@Override
	public boolean add(GIS_element arg0) {
		try {
			se.add((GisElement) arg0);
			Elements.add(arg0);
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends GIS_element> arg0) {
		se.addAll((Collection<? extends GisElement>) arg0);
		return Elements.addAll(arg0);	
	}

	@Override
	public void clear() {
		se.clear();
		Elements.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return Elements.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
	return Elements.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return Elements.isEmpty();	
	}

	@Override
	public Iterator<GIS_element> iterator() {
		return Elements.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		se.remove(arg0);
		return Elements.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		se.removeAll(arg0);
		return Elements.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return Elements.retainAll(arg0);
	}

	@Override
	public int size() {
		return Elements.size();
	}

	@Override
	public Object[] toArray() {
		
		return Elements.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return Elements.toArray(arg0);
	}

	@Override
	public Meta_data get_Meta_data() {
		return null;
	}
	
	/**
	 * toString function to print the layer details.
	 */
	public String toString() {
		String send="";
		for(GisElement e : se) {
			send+= ""+e+"\n ";
		}
		return send;
	}
	
	/**
	 * this function get a output directory string and make a kml file from this layer.
	 * @param output output directory.
	 */
	public  void LeyerToKML( String output) {
		ArrayList<String> content = new ArrayList<String>();
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" + "<Document>";
		content.add(kmlstart);

		String kmlend = "</Document>\n"+"</kml>";
		try{
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			for(GisElement e : se) {
				String[] s = e._metaData.getData();
				String kmlelement ="<Placemark>\n" 
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
