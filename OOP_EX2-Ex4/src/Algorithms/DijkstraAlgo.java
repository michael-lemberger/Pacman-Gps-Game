package Algorithms;
import java.util.ArrayList;
import java.util.Iterator;

import GIS.Fruit;
import GIS.GIS_element;
import GIS.Game;
import GIS.Map;
import GIS.Node;
import Geom.Point3D;
import graph.Graph;
import graph.Graph_Algo;

public class DijkstraAlgo{
	Game _game;
	public ConnectedGraph  conGraph;
	Map map;public Fruit fruit;
	public ArrayList<String>path;
	ArrayList<GIS_element>fruits;
	double distance;
	public DijkstraAlgo(Game game) {
		this._game = game;
		path=new ArrayList<String>();
		fruits=new ArrayList<GIS_element>();
		this.fruits.addAll(game.fruits);
		map=new Map(1386,642);
		distance=Integer.MAX_VALUE;
		runAlgo();
	}

	public void runAlgo() {
		ConnectedGraph  conGraph = new ConnectedGraph(this._game);
		Iterator<GIS_element>it=fruits.iterator();
		int index=0;
		while(it.hasNext()) {
		Fruit f_target=(Fruit) it.next();
		Graph G = new Graph(); 
		String source = "a";String target="b";
		Iterator <Node> vertexes=conGraph.vertexes.iterator();
		while(vertexes.hasNext()) {
		Node vertex=vertexes.next();
		graph.Node d=new graph.Node(vertex._name);
		G.add(d);
		Iterator<Node>neighbors=vertex._neighbors.iterator();
		while(neighbors.hasNext()) {
		Node neighbor=neighbors.next();	
		G.addEdge(vertex._name,neighbor._name,vertex._point.distance2D(neighbor._point));
		}
		}
			int arr[]=map.gpsToPixel(f_target.get_p().x(),f_target.get_p().y());
			graph.Node d=new graph.Node("b");
			G.add(d);
			Node b=new Node(f_target.get_p(),new Point3D(arr[0],arr[1]),"b");
			conGraph.checkNeighbors(conGraph.vertexes,b);
			Iterator<Node>neighbors=b._neighbors.iterator();
			while(neighbors.hasNext()) {
				Node neighbor=neighbors.next();
				G.addEdge(neighbor._name,b._name,neighbor._point.distance2D(b._point));
			}
			Graph_Algo.dijkstra(G, source);
		graph.Node targeted= G.getNodeByName(target);
		if(targeted.getDist()<distance) {
		distance=targeted.getDist();
		path=targeted.getPath();
		fruit=f_target;
		}
		}	
	}
	}