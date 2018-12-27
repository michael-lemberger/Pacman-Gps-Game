package GIS;

import java.awt.Image;
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
 * Game constructor1. get a string directory (csv file) and create Game object.
 * Game constructor2. get an arraylist and crate game object.
 * this Game object contains  a set of pacman objects and a set Fruit objects.
 *  @author Michael Lemberger, Liron Arad, Maoz Grossman.
 *
 */
public class Game{
	private CsvGameReader csv=new CsvGameReader();
	public ArrayList<GIS_element> fruits=new ArrayList<GIS_element>();
	public ArrayList<Pacman> pacmans =new ArrayList<Pacman>();
	public ArrayList<Block> blocks =new ArrayList<Block>();
	public ArrayList<Ghost> ghosts =new ArrayList<Ghost>();
	public Player player;
	

	public Game(){

	}
	/**
	 * csv to game constructor.
	 * @param directory a csv directory path.
	 * @throws Exception if there is any problem
	 */
	public  Game(String directory) throws Exception {
		ArrayList<String>lines=csv.csvmaker(directory);
		arrayList2Game(lines);
	}	

	public Game(ArrayList<String> board_data) {
		arrayList2Game(board_data);	
	}
	

	public void arrayList2Game(ArrayList<String> board_data) {
		Images img=new Images();
		for (int i = 0; i < board_data.size(); i++) {
			String line=board_data.get(i);
			String[]data=line.split(",");	
			Point3D point=new Point3D(Double.parseDouble(data[3]),Double.parseDouble(data[2]),Double.parseDouble(data[4]));
			int id=Integer.parseInt(data[1]);
			double speed=Double.parseDouble(data[5]);
			double radius=Double.parseDouble(data[data.length-1]);
			if(data[0].equals("P")) {
				Pacman pacman = new  Pacman(point, id, speed, radius,img.pacimg);
				this.pacmans.add(pacman);
			}
			else if(data[0].equals("F")) {
				Fruit fruit = new Fruit(point,id,img.getFruitIcon());
				this.fruits.add(fruit);
			}
			else if(data[0].equals("G")) {
				Ghost ghost = new Ghost(point, id,speed,radius,img.getGhostIcon());
				this.ghosts.add(ghost);
			}
			else if(data[0].equals("B")){
				Point3D point2=new Point3D(Double.parseDouble(data[6]),Double.parseDouble(data[5]),Double.parseDouble(data[7]));
				Block block=new Block(point,point2,id,radius);
				this.blocks.add(block);
			}
			else if(data[0].equals("M")) {
				this.player=new Player(point,id,speed,radius,img.playerimg);
			}
		}
		
	}
	
	/**
	 * By id of a Pacman you can find it on a list.
	 * @param id
	 * @return Pacman pac
	 */
	public Pacman getPac(int id){
		Iterator<Pacman> it = this.pacmans.iterator();
		while(it.hasNext()) {
			Pacman pac=it.next();
			if(pac.get_id()==id) {
				return pac;
			}
		}
		return null;
	}
}
