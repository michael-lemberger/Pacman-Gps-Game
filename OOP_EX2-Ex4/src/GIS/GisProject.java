package GIS;

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
public class GisProject implements GIS_project {
	private static Set<GIS_layer> layers;
	public static Set<GisLayer> setProject;

	/**
	 * GisProject constructor.
	 * this project object contains GisLayers objects.
	 */
	public GisProject() {
		layers=new HashSet<GIS_layer>();
		setProject =new HashSet<GisLayer>();
	}

	public boolean add(GIS_layer layer) {
		try {
			setProject.add((GisLayer) layer);
			layers.add(layer);
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		setProject.addAll((Collection<? extends GisLayer>) c);
		return layers.addAll(c);
	}

	@Override
	public void clear() {
		setProject.clear();
		layers.clear();

	}

	@Override
	public boolean contains(Object o) {

		return layers.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return layers.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return layers.isEmpty();
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		return layers.iterator();
	}

	@Override
	public boolean remove(Object o) {
		setProject.remove(o);
		return layers.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		setProject.removeAll(c);
		return layers.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		setProject.retainAll(c);
		return layers.retainAll(c);
	}

	@Override
	public int size() {
		return layers.size();
	}

	@Override
	public Object[] toArray() {

		return layers.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return layers.toArray(a);
	}

	@Override
	public Meta_data get_Meta_data() {

		return null;
	}

	public String toString() {
		int num=1;
		String send="---project---\n\n";
		for(GisLayer l : setProject) {
			send+= "layer"+num+"\n"+l+"\n ";
			//			send+=""+l.size()+"\n";
			num++;
		}
		return send;
	}

	/**
	 * this function get a output directory string and make a kml file from this project.
	 * @param output output directory.
	 * @throws Exception
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
			for(GisLayer l : this.setProject) {
				for(GisElement e : l.se) {
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

}
