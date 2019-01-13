package GIS;

import java.util.ArrayList;


import Geom.Point3D;
/**
 * Node containing data of a vertex:
 * 1. name
 * 2. neighbors
 * 3. point in gps point and pixel.
 * @author Michael lemberger,Maoz grossman,liron arrad
 *
 */
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

	public String toString() {
		return ""+inPixel+":"+_name ;
	}


}
