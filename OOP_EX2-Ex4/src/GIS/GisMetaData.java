package GIS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Geom.Point3D;

public class GisMetaData implements Meta_data {
	private static long utc;
	public String[] data;
	public String color;
	
	public GisMetaData() {
		data=null;
		utc=getUTC();
		}

	public GisMetaData(String[] data) {
		this.data=data;
		utc=this.getUTC();
	}
	public GisMetaData(GisMetaData data2) {
		this.data = data2.getData(); 
	}
	@Override
	/** @returns the Universal Time Clock associated with this data;
	 */
	public long getUTC() {
		try {
			if(data!=null) {
		String temp= data[3];
		temp=temp.replace('/', '-');
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		
		    Date d = f.parse(temp);
		   utc = d.getTime();
			}
			else {
		 utc =System.currentTimeMillis()/1000;
		}
		}
		catch(Exception E){
			 utc =System.currentTimeMillis()/1000;	
		}
		return utc;
	}
	

	public String[] getData() {
		return data;
	}
	@Override
	/** return a String representing this data 
	 */
	public String toString(){
		try {
			return "MAC: "+data[0]+", SSID: "+data[1]+", AuthMode: "+data[2]+", FirstSeen: "+data[3]+", Channel: "+data[4]+", RSSI: "+data[5]+", AccuracyMeters: "+data[9]+", Type: "+data[10];
		}
		catch(Exception e) {
		return null;
		}

	}

	/**
	 * @return the orientation: yaw, pitch and roll associated with this data;
	 */
	public Point3D get_Orientation() {
		//don't do
		return null;
	}



	  
	  }

