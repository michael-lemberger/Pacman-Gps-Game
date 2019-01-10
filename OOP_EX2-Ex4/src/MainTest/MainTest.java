package MainTest;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;

import Algorithms.ConnectedGraph;
import Algorithms.DijkstraAlgo;
import GIS.Game;
import GIS.Node;
import GIS.Player;
import Geom.Point3D;
import Robot.Play;

public class MainTest {
	public static void main (String[]args) throws Exception {
		Game game=new Game("data\\Ex4_OOP_example3.csv");
		Image img=new ImageIcon("res\\pacman1.gif").getImage();;
		game.player=new Player(new Point3D(35.21073425108225,32.10380415576324,0.0),1,2.0,1.0,img);
		ConnectedGraph c = new ConnectedGraph(game);
		DijkstraAlgo D=new DijkstraAlgo(game,c);
		String vertex="";
		for (int i = 0; i < D.path.size(); i++) {
			System.out.print(D.path.get(i)+" ,");
		}
		

		ArrayList<String> board_data = new ArrayList<>();
		
		game = new Game(board_data);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		if(D.path.size()>=2) {
//		vertex=D.path.get(1);
//		System.out.println(vertex);
//		}
//		System.out.println(D);
//		Node n=null;
//		Iterator<Node>vertexes=D.C.vertexes.iterator();
//		while(vertexes.hasNext()) {
//			Node n0=vertexes.next();
//			if(vertex.equals(n0._name)) {
//				n=n0;
//			}
			
//		}
	}
}
