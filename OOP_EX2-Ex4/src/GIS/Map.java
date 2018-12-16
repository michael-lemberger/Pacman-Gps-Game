package GIS;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class Map {
	private BufferedImage _img= null;
	private Raster _data=null;
	public Range _rangeX;
	public Range _rangeLat;
	public Range _rangeLon;


	public Map(BufferedImage ariel1) {
		this._img=ariel1;
		int w = ariel1.getWidth();
		int h = ariel1.getHeight();
		this._rangeX = new Range(0, 1386);
		this._rangeLat = new Range(32.101658,32.106046);
		this._rangeLon = new Range(35.20238,35.21236);
	}

	public double[] pixelToGps(double xLon, double yLat) {
		double[] ans = new double[2];

		double proportionX = this._rangeX.proportion(xLon);
		double percentageLon = this._rangeLon.percentge(proportionX);
		ans[0] = percentageLon;

		yLat = 642 - yLat; 
		ans[1] = (642*32.101658 + yLat*(32.106046-32.101658))/642;

		return ans;
	}

	public double[] gpsToPixel(double xLon, double yLat) {
		double[] ans = new double[2];

		double proportionLon = this._rangeLon.proportion(xLon);
		double percentageX = this._rangeX.percentge(proportionLon);
		ans[0] = percentageX;

		ans[1] = (642*yLat - 642*32.106046)/(32.101658-32.106046);
		return ans;
	}




}
