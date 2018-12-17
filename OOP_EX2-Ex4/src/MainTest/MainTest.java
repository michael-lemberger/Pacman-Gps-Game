package MainTest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;

import Algorithms.MultiCsv;
import Algorithms.ShortestPathAlgo;
import File_format.Path2KML;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.Game;
import GIS.GisLayer;
import GIS.Map;
import GIS.Pacman;

public class MainTest {

	public static void main(String[] args) throws Exception {
			//test gpsToKml func
				Game game1=new Game ("res/game_1543684662657.csv");
				Map map = new Map(1386, 642);

				Iterator<GIS_element>fruits=game1.fruits.iterator();
				while (fruits.hasNext()) {
					Fruit f = (Fruit) fruits.next();
					int[] ans = map.gpsToPixel(f.get_p().y(), f.get_p().x());
					System.out.println(ans[0]+", "+ans[1]);
					
//					Sounds sounds=new Sounds();
//					sounds.crounch();
					Game game=new Game ("res\\game_1543684662657.csv");
					ShortestPathAlgo s=new ShortestPathAlgo(game);
					Iterator<Pacman>pacmans=game.pacmans.iterator();
					int i=1;
					Pacman p = null;
					while (pacmans.hasNext()) {
						p= (Pacman) pacmans.next();
						System.out.println("-p"+i+"-");
						System.out.println(p.path.toString()+"\n");
						i++;
					}
					Path2KML p2m=new Path2KML(p.path,"C:\\Users\\Simple Man\\Desktop\\Path2Kml.kml");
				}

				}




	}


