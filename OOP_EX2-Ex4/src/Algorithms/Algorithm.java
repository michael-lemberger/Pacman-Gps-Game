package Algorithms;
import java.util.ArrayList;
import java.util.Iterator;

import GIS.Game;
import GIS.Path;
import Geom.Point3D;
import graph.Graph;
import graph.Graph_Algo;
import graph.Node;
public class Algorithm {
	Game _game;
	ConnectedGraph  conGraph;
	public Algorithm(Game game) {
		this._game = game;
		
	}


	public void runAlgo() {
		ConnectedGraph  conGraph = new ConnectedGraph(this._game);
		
		Graph G = new Graph(); 
		String source = "a";
		String target = "b";
		
		Iterator<Point3D> itSourse = conGraph.neighbors.get(0).points.iterator();
		while (itSourse.hasNext()) {
			
				Point3D p0 = itSourse.next();
				Point3D p1=null;
				if(itSourse.hasNext())
				p1= itSourse.next();
			G.addEdge("a",conGraph.neighbors.get(0).getIndex(),p0.distance2D(p1) );
		}
		
		Iterator<Path> it = conGraph.neighbors.iterator();
		it.next();
		while(it.hasNext()) {
			Path path = it.next();
			Iterator<Point3D> it1 = path.points.iterator();
			
		}

		Graph_Algo.dijkstra(G, source);

		Node b = G.getNodeByName(target);
		System.out.println("***** Graph Demo for OOP_Ex4 *****");
		System.out.println(b);
		System.out.println("Dist: "+b.getDist());
		ArrayList<String> shortestPath = b.getPath();
		for(int i=0;i<shortestPath.size();i++) {
			System.out.print(","+shortestPath.get(i));
		}

	}
}
