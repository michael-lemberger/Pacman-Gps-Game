package GIS;

import java.util.ArrayList;
import java.util.Iterator;

import Geom.Point3D;

public class Node {
	public Point3D _point;
	public String _name;
	public ArrayList <Node> _neighbors;
	public Path path;
 public Node(Point3D point,String name) {
	 this._name=name;
	 this._point=point;
	 _neighbors=new ArrayList<Node>();
 }

 public void add(Node neighbor) {
	 _neighbors.add(neighbor);
 }
 
 public double getDist() {
	return path.calc_distnce(0,path.points.size()-1);
 }
 
 public ArrayList<String> getPath(){
	 ArrayList<String>names=new ArrayList<String>();
	for(Node n:_neighbors) {
		names.add(n._name);
	}
	return names;
 }
}
