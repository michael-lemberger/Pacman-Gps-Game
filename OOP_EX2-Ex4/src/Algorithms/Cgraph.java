package Algorithms;

import java.util.ArrayList;
import java.util.Iterator;

import GIS.Block;
import GIS.Game;
import GIS.Map;
import GIS.Node;
import Geom.Line;
import Geom.Point3D;
import graph.Graph;
/**
 * Crates a New ArrayList of GIS.Nodes
 * Every Node is a vertex.
 * Algorithm Calculates the neighbors of every vertex,
 * considering the blocks(Uses line class,every line is an edge).
 * At the end adds all the edges to graph.Graph 
 * @author Maoz grossman, Michael lemberger, Liron Arad
 */

public class Cgraph {
	public Game game = new Game();
	public ArrayList<Node> nodes = new ArrayList<>();
	public ArrayList<Line> lines = new ArrayList<>();
	public Map map;
	public Graph graph;
	
	public Cgraph(Game game,Map map) {
		this.game = game;
		this.graph=new Graph();
		this.map=map;
		buildGraph();
	}
	
	private void buildGraph() {
		buildNodes();
		buildLines();
		for(Node n:nodes) {
			graph.Node node=new graph.Node(n._name);
			graph.add(node);
		}
		Iterator<Node>vertexes=nodes.iterator();
		while(vertexes.hasNext()) {
			Node vertex=vertexes.next();
		IsConnected(vertex,this.nodes);
		}
	}
	/**
	 * Add the vertexes to the ArrayList.
	 */
	private void buildNodes() {
		//add the player node
		int[] pixels = map.gpsToPixel(game.player._p.x(), game.player._p.y());
		Point3D inpixel = new Point3D(pixels[0], pixels[1]);
		Node player = new Node(game.player._p, inpixel, "a");
		this.nodes.add(player);

		//add block corners nodes
		Iterator<Block> block_it = this.game.blocks.iterator();
		int counter = 1;
		while(block_it.hasNext()) {
			Block block = block_it.next();

			pixels = map.gpsToPixel(block.start.x(), block.start.y());
			inpixel = new Point3D(pixels[0]-40, pixels[1]+40);
			Node block_LU_Corner = new Node(block.start, inpixel, ""+counter++);
			this.nodes.add(block_LU_Corner);

			pixels = map.gpsToPixel(block.point0.x(), block.point0.y());
			inpixel = new Point3D(pixels[0]+40, pixels[1]+40);
			Node block_RU_Corner = new Node(block.point0, inpixel, ""+counter++);
			this.nodes.add(block_RU_Corner);

			pixels = map.gpsToPixel(block.point1.x(), block.point1.y());
			inpixel = new Point3D(pixels[0]-40, pixels[1]-40);
			Node block_LD_Corner = new Node(block.point1, inpixel, ""+counter++);
			this.nodes.add(block_LD_Corner);

			pixels = map.gpsToPixel(block.end.x(), block.end.y());
			inpixel = new Point3D(pixels[0]+40, pixels[1]-40);
			Node block_RD_Corner = new Node(block.end, inpixel, ""+counter++);
			this.nodes.add(block_RD_Corner);

		}


	}
	/**
	 * Build the edges of the blocks.
	 */
	private void buildLines() {
		Iterator<Block> BlockLines= game.blocks.iterator();
		while(BlockLines.hasNext()) {
		Block b=BlockLines.next();
		int[] start=map.gpsToPixel(b.start.x(),b.start.y());
		int[] end=map.gpsToPixel(b.end.x(),b.end.y());
		int[] p0=map.gpsToPixel(b.point0.x(),b.point0.y());
		int[] p1=map.gpsToPixel(b.point1.x(),b.point1.y());
		Line line1=new Line(new Point3D(start[0],start[1]),new Point3D(p0[0],p0[1]));
		Line line2=new Line(new Point3D(p1[0],p1[1]),new Point3D(start[0],start[1]));
		Line line3=new Line(new Point3D(end[0],end[1]),new Point3D(p0[0],p0[1]));
		Line line4=new Line(new Point3D(p1[0],p1[1]),new Point3D(end[0],end[1]));
		lines.add(line1);
		lines.add(line2);
		lines.add(line3);
		lines.add(line4);
		}
	}
	/**
	 * Finds all the neighbors.
	 * If two points edge is cutting
	 * one of the block edges we than 
	 * know that those two points arr'nt
	 * connected.
	 * Uses line class,every line is an edge.
	 * @param vertex
	 * @param nodes
	 */
	public void IsConnected(Node vertex,ArrayList<Node> nodes) {
			Iterator<Node>other=nodes.iterator();
			while(other.hasNext()) {
				Node is_neighbor=other.next();
				if(!vertex._name.equals(is_neighbor._name)) {
					Line check=new Line(vertex.inPixel,is_neighbor.inPixel);
					boolean flag=false;
					for(Line l:lines) {
						if(check.isCutting(l)) {
							flag=true;
							break;
						}
					}
					if(!flag) {
						graph.addEdge(vertex._name,is_neighbor._name,vertex._point.distance2D(is_neighbor._point));
						vertex._neighbors.add(is_neighbor);
					}
			}
			}
	}

	@Override
	public String toString() {
		return "Cgraph [graph=" + graph.toString() + "]";
	}
}
