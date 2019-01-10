package GIS;

import java.util.ArrayList;


import Geom.Point3D;

public class Node {
	public Point3D _point,inPixel;
	public String _name;
	public ArrayList <Node> _neighbors;
 public Node(Point3D point,Point3D inpixel,String name) {
	 this._name=name;
	 this._point=point;
	 this.inPixel=inpixel;
	 _neighbors=new ArrayList<Node>();
 }

 public void add(Node neighbor) {
	 _neighbors.add(neighbor);
 }

@Override
public String toString() {
	return ""+inPixel+":"+_name ;
}
 

}
