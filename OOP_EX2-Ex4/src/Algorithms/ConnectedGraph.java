package Algorithms;

import java.util.ArrayList;
import java.util.Iterator;

import GIS.Block;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.Game;
import GIS.Map;
import GIS.Pacman;
import GIS.Path;
import GIS.Player;
import GIS.PointDis;
import Geom.Line;
import Geom.Point3D;

public class ConnectedGraph{
		public Game game;
		public ArrayList<Path>neighbors;
		Map map;
		/**
		 * Constructor for "ShortestPathAlgo"
		 * @param game
		 */
		public ConnectedGraph(Game game) {
			this.game=game;
			BuildGraph();
		}

		
		public ArrayList<Path> BuildGraph() {
			ArrayList<Path>neighbors=new ArrayList<Path>();
			BuildList(neighbors);
			Iterator<Path>it=neighbors.iterator();
			while(it.hasNext()) {
				Path vertex=it.next();
				Iterator<Path>it1=neighbors.iterator();
				while(it1.hasNext()) {
					Path neighbor=it.next();
					Line is_neighbor=new Line(vertex.points.get(0),neighbor.points.get(0));
					ArrayList<Line>lines=BuildBlockLines();
					Iterator<Line>it2=lines.iterator();
					while(it.hasNext()){
						Line line=it2.next();
						if(!is_neighbor.isCutting(line)) {
							vertex.points.add(neighbor.points.get(0));
						}
					}
				}
			}
			return neighbors;
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
		
		private void BuildList(ArrayList<Path> list) {
			ArrayList<Block> blocks =new ArrayList<Block>();
			blocks.addAll(game.blocks);
			list.add(new Path());
		    int arr[]=map.gpsToPixel(game.player._p.x(),game.player._p.y());
		    list.get(0).points.add(new Point3D(arr[0],arr[1]));
			Iterator<GIS_element>it=game.fruits.iterator();
			list.get(0).setIndex("a");
			int counter=1;
			
			while(it.hasNext());
			Fruit f_vertex=(Fruit) it.next();
			list.add(new Path());
			list.get(counter).setIndex("f"+counter);
		    arr=map.gpsToPixel(f_vertex.get_p().x(),f_vertex.get_p().y());
		    list.get(0).points.add(new Point3D(arr[0],arr[1]));
			list.get(counter).points.add(f_vertex.get_p());
			counter++;
			
			Iterator<Block>it1=blocks.iterator();
			while(it1.hasNext()) {
				Block b_vertex=(Block) it.next();
				for(int i=0;i<4;i++) {
				list.add(new Path());
				list.get(counter+i).setIndex(""+counter+i);
				}
				arr=map.gpsToPixel(b_vertex.start.x(),b_vertex.start.y());
				list.get(counter).points.add(new Point3D(arr[0],arr[1]));
				arr=map.gpsToPixel(b_vertex.point0.x(),b_vertex.point0.y());
				list.get(counter+1).points.add(new Point3D(arr[0],arr[1]));
				arr=map.gpsToPixel(b_vertex.point1.x(),b_vertex.point1.y());
				list.get(counter+2).points.add(new Point3D(arr[0],arr[1]));
				arr=map.gpsToPixel(b_vertex.end.x(),b_vertex.end.y());
				list.get(counter+3).points.add(new Point3D(arr[0],arr[1]));
				counter+=4;
			}
		}
		}