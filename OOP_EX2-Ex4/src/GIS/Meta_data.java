package GIS;

import Geom.Point3D;

public interface Meta_data {
	 /**
	  * the Universal Time Clock associated with this data;
	  * @return getutc long time
	  */
	public long getUTC();
	/** return a String representing this data */
	public String toString();
	/**
	 * @return the orientation: yaw, pitch and roll associated with this data;
	 */
	public Point3D get_Orientation();
}
