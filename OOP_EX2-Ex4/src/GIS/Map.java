package GIS;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class Map {
	public Range _rangeX;
	public Range _rangeY;
	public Range _rangeLat;
	public Range _rangeLon;


	public Map(int x, int y) {
		this._rangeX = new Range(0, x);
		this._rangeY = new Range(0, y);
		this._rangeLat = new Range(32.101658,32.106046);
		this._rangeLon = new Range(35.20238,35.21236);
	}

	public double[] pixelToGps(double xLon, double yLat) {
		double[] ans = new double[2];

		double proportionX = this._rangeX.proportion(xLon);
		double percentageLon = this._rangeLon.percentge(proportionX);
		ans[0] = percentageLon;
	

		yLat = this._rangeY.getMax() - yLat; 
		ans[1] = (this._rangeY.getMax() *32.101658 + yLat*(32.106046-32.101658))/this._rangeY.getMax() ;
		System.out.println(ans[0]+","+ans[1]);
		return ans;
	}

	public int[] gpsToPixel(double xLon, double yLat) {
		int[] ans = new int[2];

		double proportionLon = this._rangeLon.proportion(xLon);
		double percentageX = this._rangeX.percentge(proportionLon);
		ans[0] = (int) percentageX;

		ans[1] = (int) ((this._rangeY.getMax() *yLat - this._rangeY.getMax() *32.106046)/(32.101658-32.106046));
		
		return ans;
	}




}