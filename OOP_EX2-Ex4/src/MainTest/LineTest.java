package MainTest;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;
import java.util.Iterator;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.Test;

import Algorithms.Cgraph;
import Algorithms.ConnectedGraph;
import Algorithms.DijkstraAlgo;
import GIS.Block;
import GIS.Game;
import GIS.Map;
import GIS.Node;
import GIS.Player;
import Geom.Line;
import Geom.Point3D;

class LineTest {

	@Test
	void test() {
		Line l1=new Line (new Point3D(2,5),new Point3D(0,3));
		Line l2=new Line (new Point3D(2,0),new Point3D(2,5));
//		assertEquals(true,l1.isCutting(l2));
		Game game=null;
		try {
			game = new Game("data\\Ex4_OOP_example3.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image img=new ImageIcon("res\\pacman1.gif").getImage();
		Map map=new Map(1386,642);
		Point3D p=new Point3D(35.210147088744584,32.10433727725857,0.0);
		int [] arr=map.gpsToPixel(p.x(),p.y());
		game.player=new Player(p,1,2.0,1.0,img);
		Cgraph C=new Cgraph(game);
		Iterator<Node>Vertexes=C.nodes.iterator();
		Point3D p1=null;
		while(Vertexes.hasNext()) {	
		Node n=Vertexes.next();
		p1=n._point;
		System.out.println(n._name+"("+n.inPixel+")"+":"+n._neighbors);
		}
		Line l=new Line(new Point3D(p),p1);
		System.out.println(arr[0]+","+arr[1]);
		System.out.println(l.ax+"+"+l.b);
		DijkstraAlgo D=new DijkstraAlgo(game, C);
		System.out.println(D.path);
	}

}
