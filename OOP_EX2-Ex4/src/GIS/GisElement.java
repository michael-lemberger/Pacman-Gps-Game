package GIS;

import java.util.Date;

import javax.xml.crypto.Data;

import Geom.Geom_element;
import Geom.Point3D;

/**
 * This class represents a GIS element with geometric representation and meta data such as:
 * color, string, timing...
 * 
 * @author Michael Lemberger, Liron Arad, Maoz Grossman.
 *
 */
public class GisElement implements GIS_element {
	Point3D _p;
	GisMetaData _metaData;

	public GisElement (Point3D p, GisMetaData metaData) throws Exception {
		this._p = p;
		this._metaData = metaData;
	}

	@Override
	public Geom_element getGeom() {
		return _p;
	}

	@Override
	public Meta_data getData() {
		return _metaData;
	}

	@Override
	public void translate(Point3D vec) {
		
	}
   public String toString() {
	return this._metaData.toString()+""+this._p;
	   
   }



}
