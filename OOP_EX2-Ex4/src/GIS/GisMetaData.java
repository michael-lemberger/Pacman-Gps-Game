package GIS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Geom.Point3D;

public class GisMetaData implements Meta_data {
	private long utc = 0;
	private String[] data;
	private String color;

	public GisMetaData(String[] data) {
		String s=data[3];
		String2Long(s);
		
	}
	@Override
	/** returns the Universal Time Clock associated with this data; */
	public long getUTC() {
		// TODO Auto-generated method stub
		return utc;
	}

	@Override
	/** return a String representing this data */
	public String toString() {

	}

	/**
	 * @return the orientation: yaw, pitch and roll associated with this data;
	 */
	public Point3D get_Orientation() {
		//don't do
		return null;
	}



	  
	  }

