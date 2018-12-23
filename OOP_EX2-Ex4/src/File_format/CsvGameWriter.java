package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.swing.JOptionPane;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.Game;
import GIS.Pacman;


public class CsvGameWriter 
{
	/**
 * makes a csv file from a game object. 
 * @param game save this game to a csv file.
 * @param name save the csv file in this name.
 * @author Michael Lemberger, Liron Arad, Maoz Grossman.
 */
	public CsvGameWriter(Game game,String name) {
		String fileName = name;
		PrintWriter pw = null;
		
		try 
		{
			pw = new PrintWriter(new File(fileName));
		} 
		catch (FileNotFoundException e) 
		{
			JOptionPane.showMessageDialog(null, "File with the same name is open\n please close it and try again");
			return;
		}

		StringBuilder sb = new StringBuilder();
		sb.append("Type");
		sb.append(',');
		sb.append("id");
		sb.append(',');
		sb.append("Lat");
		sb.append(',');
		sb.append("Lon");
		sb.append(',');
		sb.append("Alt");
		sb.append(',');
		sb.append("Speed/We");
		sb.append(',');
		sb.append("Radius");
		sb.append(',');
		sb.append(game.pacmans.size());
		sb.append(',');
		sb.append(game.fruits.size());
		sb.append('\n');

		
		Iterator<Pacman> itPacmans = game.pacmans.iterator();
		while(itPacmans.hasNext()) {
			Pacman pacman = itPacmans.next();
			sb.append("P");
			sb.append(',');
			sb.append(pacman.get_id());
			sb.append(',');
			sb.append(pacman.get_p().y());
			sb.append(',');
			sb.append(pacman.get_p().x());
			sb.append(',');
			sb.append(pacman.get_p().z());
			sb.append(',');
			sb.append(pacman.get_speed());
			sb.append(',');
			sb.append(pacman.get_radius());
			sb.append('\n');
			
		}
		
		Iterator<GIS_element> itFruits = game.fruits.iterator();
		while(itFruits.hasNext()) {
			Fruit fruit = (Fruit) itFruits.next();
			sb.append("F");
			sb.append(',');
			sb.append(fruit.get_id());
			sb.append(',');
			sb.append(fruit.get_p().y());
			sb.append(',');
			sb.append(fruit.get_p().x());
			sb.append(',');
			sb.append(fruit.get_p().z());
			sb.append(',');
			sb.append(1);
			sb.append('\n');
		}

		pw.write(sb.toString());
		pw.close();
		System.out.println("save is done!");
	}
}