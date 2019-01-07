package MainTest;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;

import Algorithms.DijkstraAlgo;
import GIS.Game;
import GIS.Node;
import GIS.Player;
import Geom.Point3D;

public class MainTest {
	public static void main (String[]args) throws Exception {
		Game game=new Game("data\\Ex4_OOP_example5.csv");
		Image img=new ImageIcon("res\\pacman1.gif").getImage();;
		game.player=new Player(new Point3D(32.10486356386293,35.21019224455733,0.0),1,2.0,1.0,img);
		DijkstraAlgo D=new DijkstraAlgo(game);
		String vertex="";
		if(D.path.size()>=2) {
		vertex=D.path.get(1);
		}
		System.out.println(D);
		Node n=null;
		Iterator<Node>vertexes=D.conGraph.vertexes.iterator();
		while(vertexes.hasNext()) {
			Node n0=vertexes.next();
			if(vertex.equals(n0._name)) {
				n=n0;
			}
			
		}
	}
}
