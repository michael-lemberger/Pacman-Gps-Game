package Algorithms;

import java.util.ArrayList;
import java.util.Iterator;

import GIS.Block;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.Game;
import GIS.Map;
import GIS.Node;
import GIS.Pacman;
import GIS.Path;
import GIS.Player;
import GIS.PointDis;
import Geom.Line;
import Geom.Point3D;

public class ConnectedGraph{
		public Game game;
		Map map;
		/**
		 * Constructor for "ShortestPathAlgo"
		 * @param game
		 */
		public ConnectedGraph(Game game) {
			this.game=game;
			BuildGraph();
		}

		
		public void BuildGraph() {
			ArrayList<Node>vertexes=new ArrayList<Node>();
			BuildList(vertexes);
			Iterator<Node>it=vertexes.iterator();
			while(it.hasNext()) {
				Node vertex=it.next();
				Iterator<Node>it1=vertexes.iterator();
				while(it1.hasNext()) {
					Node neighbor=it.next();
					Line is_neighbor=new Line(vertex._point,neighbor._point);
					ArrayList<Line>lines=BuildBlockLines();
					Iterator<Line>it2=lines.iterator();
					while(it.hasNext()){
						Line line=it2.next();
						if(!is_neighbor.isCutting(line)) {
							vertex._neighbors.add(neighbor);
						}
					}
				}
			}
		}
		
		private ArrayList<Line> BuildBlockLines(){
			Iterator<Block>it=game.blocks.iterator();
			ArrayList<Line>lines=new ArrayList<Line>();
			while(it.hasNext()) {
				Block b=it.next();
				int start[]=map.gpsToPixel(b.start.x(),b.start.y());
				int point0[]=map.gpsToPixel(b.point0.x(),b.point0.y());
				int point1[]=map.gpsToPixel(b.point1.x(),b.point1.y());
				int end[]=map.gpsToPixel(b.end.x(),b.end.y());
				lines.add(new Line(new Point3D(start[0],start[1]),new Point3D(point0[0],point0[1])));
				lines.add(new Line(new Point3D(start[0],start[1]),new Point3D(point1[0],point1[1])));
				lines.add(new Line(new Point3D(end[0],end[1]),new Point3D(point0[0],point0[1])));
				lines.add(new Line(new Point3D(end[0],end[1]),new Point3D(point1[0],point1[1])));
			}
			return lines;
		}
		
		private void BuildList(ArrayList<Node> list) {
			ArrayList<Block> blocks =new ArrayList<Block>();
			blocks.addAll(game.blocks);
			
		    int arr[]=map.gpsToPixel(game.player._p.x(),game.player._p.y());
		    list.add(new Node(new Point3D(arr[0],arr[1]),"a"));
			int counter=1;
			Iterator<Block>it=blocks.iterator();
			while(it.hasNext()) {
				Block b_vertex=(Block) it.next();
				for(int i=0;i<4;i++) {
				arr=map.gpsToPixel(b_vertex.start.x(),b_vertex.start.y());
				  list.add(new Node(new Point3D(arr[0],arr[1]),""+counter));
				arr=map.gpsToPixel(b_vertex.point0.x(),b_vertex.point0.y());
				  list.add(new Node(new Point3D(arr[0],arr[1]),""+counter+1));
				arr=map.gpsToPixel(b_vertex.point1.x(),b_vertex.point1.y());
				  list.add(new Node(new Point3D(arr[0],arr[1]),""+counter+2));
				arr=map.gpsToPixel(b_vertex.end.x(),b_vertex.end.y());
				  list.add(new Node(new Point3D(arr[0],arr[1]),""+counter+3));
				counter+=4;
			}
		}
		}
		}