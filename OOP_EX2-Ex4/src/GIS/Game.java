package GIS;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;

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
public class Game{
	private CsvGameReader csv=new CsvGameReader();
	public ArrayList<GIS_element> fruits=new ArrayList<GIS_element>();
	public ArrayList<Pacman> pacmans =new ArrayList<Pacman>();

	/**
	 * Game constructor. get a string directory (csv file) and create Game object.
	 * this Gaame object contains  a set of pacman objects and a set Fruit objects.
	 * @param directory a csv directory path.
	 * @throws Exception if there is any problem
	 */
	public  Game(String directory) throws Exception {
 		String s[][]=csv.csvmaker(directory);
	
		for (int i = 0; i < s.length-1; i++) {	
 			String point=""+s[i][2]+","+s[i][3]+","+s[i][4]+"";
			Point3D p = new Point3D (point);	
			int id = Integer.parseInt(s[i][1]);
			if(s[i][0].charAt(0) == 80) {
				double speed = Double.parseDouble(s[i][5]);
				double radius = Double.parseDouble(s[i][6]);
				Pacman pacman = new  Pacman(p, id, speed, radius);
				this.pacmans.add(pacman);
			}
			else if(s[i][0].charAt(0) == 70) {
				Fruit fruit = new Fruit(p, id,new ImageIcon(getFruitIcon()).getImage());
				this.fruits.add(fruit);
			}
			else {
 			}			
		}
		System.out.println();
	}	

	/*****************************get a random fruit icon*************************************/
	public String getFruitIcon() {
		int i= (int) (Math.random()*6);
		String[]icon= {
				"res\\bananagif.gif"
				,"res\\pinapple.gif"
				,"res\\strawbery.gif"
				,"res\\orange.gif"
				,"res\\apple.gif"
				,"res\\pera.gif"
		};
		return icon[i];
	}
	
	
}
