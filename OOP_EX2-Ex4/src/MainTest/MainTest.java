package MainTest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import Geom.Point3D;
import Threads.Sounds;
import sun.security.pkcs11.P11TlsKeyMaterialGenerator;

public class MainTest {

	public static void main(String[] args) throws Exception {
		//test gpsToKml func
		Game game1=new Game ("res/game_1543684662657.csv");
		Map map = new Map(1386, 642);

//		Iterator<GIS_element>fruits=game1.fruits.iterator();
//		while (fruits.hasNext()) {
//			Fruit f = (Fruit) fruits.next();
//			
//			int[] ans = map.gpsToPixel(f.get_p().x(), f.get_p().y());
//			double[] ans1 = map.pixelToGps(ans[0], ans[1]);
//			
//			System.out.println(f.get_p().toString());
//			System.out.println(ans[0]+","+ans[1]);
//			System.out.println(ans1[0]+","+ans1[1]);
//			
//			System.out.println();
//		}

		double[] gps = map.pixelToGps(743, 392);
		System.out.println(gps[0]+","+gps[1]);
		
//		Sounds sounds=new Sounds();
//		sounds.crounch();
		Game game=new Game ("res\\game_1543693822377.csv");
		ShortestPathAlgo s=new ShortestPathAlgo(game);
	
		Path2KML p2m=new Path2KML(game,"C:\\Users\\Simple Man\\Desktop\\test.kml");
		Iterator<Point3D>it = game.pacmans.get(0).path.points.iterator();
		int counter=0;
		while(it.hasNext()) {
			Point3D f= it.next();
			counter++;
			Point3D a=new Point3D (game.pacmans.get(0).get_p());
			double distance=a.distance2D(f);
			double steps=0;
			if(counter>1) {
				Point3D move= movment(game.pacmans.get(0).get_p(),f);
				while(steps<=distance&&Math.abs(steps-distance)>0.000001) {
					game.pacmans.get(0).get_p().add(move);
					steps+=game.pacmans.get(0).get_p().distance2D(a);
					a=new Point3D (game.pacmans.get(0).get_p());
				}
			}
		}
	}

	private static Point3D movment(Point3D pac,Point3D fruit) {
		double x =((fruit.x()-pac.x())/10);
		double y =(fruit.y()-pac.y())/10;
		double z= (fruit.z()-pac.z())/10;
		Point3D move= new Point3D(y,x,z);
		return move;
	}




}


