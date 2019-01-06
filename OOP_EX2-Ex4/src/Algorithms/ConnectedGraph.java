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

public class ConnectedGraph {
		public Game game;
		Map map;
		ArrayList<Node>vertexes; ArrayList<Line>lines;
		
		public ConnectedGraph(Game game) {
			this.game=game;
			map=new Map(1386,642);
			BuildGraph();
		}

		public void BuildGraph() {
			vertexes=new ArrayList<Node>();
			lines=new ArrayList<Line>();
			BuildList();
			BuildBlockLines();
			Iterator<Node>it=vertexes.iterator();
			while(it.hasNext()) {
				Node vertex=it.next();
				checkNeighbors(vertexes,vertex);
				
			}
		}
		
		public void checkNeighbors(ArrayList<Node> vertexes,Node vertex){
			Iterator<Node>neighbors=vertexes.iterator();
			while(neighbors.hasNext()) {
				Node neighbor=neighbors.next();
				boolean cutting=false;
				if(vertex._name!=neighbor._name) {
				Line is_neighbor=new Line(vertex._point,neighbor._point);
				Iterator<Line>lines_cut=lines.iterator();
				while(lines_cut.hasNext()&&!cutting){
					Line line=lines_cut.next();
					if(is_neighbor.isCutting(line)==true) {
						cutting=true;
					}
				}
				if(!cutting) {
					vertex._neighbors.add(neighbor);
				}
			}
			}	
		}
		
		private void BuildBlockLines(){
			Iterator<Block>it=game.blocks.iterator();
			while(it.hasNext()) {
				Block b=it.next();
				int start[]=map.gpsToPixel(b.start.x(),b.start.y());
				int point0[]=map.gpsToPixel(b.point0.x(),b.point0.y());
				int point1[]=map.gpsToPixel(b.point1.x(),b.point1.y());
				int end[]=map.gpsToPixel(b.end.x(),b.end.y());
				lines.add(new Line(new Point3D(point0[0],point0[1]),new Point3D(start[0],start[1])));
				lines.add(new Line(new Point3D(start[0],start[1]),new Point3D(point1[0],point1[1])));
				lines.add(new Line(new Point3D(point0[0],point0[1]),new Point3D(end[0],end[1])));
				lines.add(new Line(new Point3D(end[0],end[1]),new Point3D(point1[0],point1[1])));
			}
		}
		
		private void BuildList() {
			ArrayList<Block> blocks =new ArrayList<Block>();
			blocks.addAll(game.blocks);
			
		    int arr[]=map.gpsToPixel(this.game.player._p.x(),this.game.player._p.y());
		    vertexes.add(new Node(new Point3D(arr[0],arr[1]),"a"));
			int counter=1;
			Iterator<Block>it=blocks.iterator();
			while(it.hasNext()) {
				Block b_vertex=(Block) it.next();
				arr=map.gpsToPixel(b_vertex.start.x(),b_vertex.start.y());
				  vertexes.add(new Node(new Point3D(arr[0],arr[1]),""+counter));
				arr=map.gpsToPixel(b_vertex.point0.x(),b_vertex.point0.y());
				  vertexes.add(new Node(new Point3D(arr[0],arr[1]),""+(counter+1)));
				arr=map.gpsToPixel(b_vertex.point1.x(),b_vertex.point1.y());
				  vertexes.add(new Node(new Point3D(arr[0],arr[1]),""+(counter+2)));
				arr=map.gpsToPixel(b_vertex.end.x(),b_vertex.end.y());
				  vertexes.add(new Node(new Point3D(arr[0],arr[1]),""+(counter+3)));
				counter+=4;
		}
		}
		}