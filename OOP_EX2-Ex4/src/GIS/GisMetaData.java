package GIS;

import Geom.Point3D;

public class GisMetaData implements Meta_data {

	@Override
	/** returns the Universal Time Clock associated with this data; */
	public long getUTC() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	/** return a String representing this data */
	public String toString();
	
	/**
	 * @return the orientation: yaw, pitch and roll associated with this data;
	 */
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

}
