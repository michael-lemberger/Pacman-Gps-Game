package GIS;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import File_format.CsvGameReader;
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
public class Game extends ArrayList<GIS_element> {
	private CsvGameReader csv=new CsvGameReader();
	public static ArrayList<GIS_element> fruits=new ArrayList<GIS_element>();
	public static ArrayList<GIS_element> pacmans =new ArrayList<GIS_element>();

	/**
	 * Game constructor. get a string directory (csv file) and create Game object.
	 * this Gaame object contains  a set of pacman objects and a set Fruit objects.
	 * @param directory a csv directory path.
	 * @throws Exception if there is any problem
	 */
	public  Game(String directory) throws Exception {
 		String s[][]=csv.csvmaker(directory);
		for (int i = 1; i < s.length-1; i++) {		
			GisMetaData metadata =new GisMetaData(s[i]);
			String point=""+s[i][2]+","+s[i][3]+","+s[i][4]+"";
			Point3D p=new Point3D (point);		
			GisElement element= new GisElement(p, metadata);
			this.add(element);			
		}
		for (int i = 1; i < s.length-1; i++) {	
 			String point=""+s[i][2]+","+s[i][3]+","+s[i][4]+"";
			Point3D p = new Point3D (point);	
			int id = Integer.parseInt(s[i][1]);
			if(s[i][0] == "p") {
				double speed = Double.parseDouble(s[i][5]);
				double radius = Double.parseDouble(s[i][6]);
				Pacman pacman = new  Pacman(p, id, speed, radius);
				this.pacmans.add(pacman);
			}
			else if(s[i][0] == "f") {
				Fruit fruit = new Fruit(p, id);
				this.fruits.add(fruit);
			}
			else {
 			}			
		}
		System.out.println();
	}	


	public static ArrayList<GIS_element> getFruits() {
		return fruits;
	}


	public static ArrayList<GIS_element> getPacmans() {
		return pacmans;
	}


	public  void GameToCsv( String output) {
		
	}
	
	
}
