package GIS;

import Geom.Geom_element;
import Geom.Point3D;

/**
 * This interface represents a GIS element with geometric representation and meta data such as:
 * Orientation, color, string, timing...
 * @author Michael Lemberger, Liron Arad, Maoz Grossman.
 *
 */
public class GisElement implements GIS_element {

	@Override
	public Geom_element getGeom() {
		
	}

	@Override
	public Meta_data getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub
		
	}

}
