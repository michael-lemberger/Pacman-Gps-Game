package GIS;

import java.util.ArrayList;

import Geom.Point3D;

public class Node {
	Point3D _point;
	String _name;
	ArrayList <Node> _neighbors;
 public Node(Point3D point,String name) {
	 this._name=name;
	 this._point=point;
	 _neighbors=new ArrayList<Node>();
 }
 
 public void add(Node neighbor) {
	 _neighbors.add(neighbor);
 }
}
