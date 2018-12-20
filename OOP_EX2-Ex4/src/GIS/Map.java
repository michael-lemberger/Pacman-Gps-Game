package GIS;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;


 /**
  * This class represent a map of a game board.
  * The map has the dimensions of the frame.
  * These dimensions are represented by an Range objects.
  * each map have ranges of coordinates and ranges of  image pixels.
  *
  * @author Michael Lemberger, Liron Arad, Maoz Grossman.
  */
public class Map {
	public Range _rangeX;
	public Range _rangeY;
	public Range _rangeLat;
	public Range _rangeLon;

	/**
	 * This Map constructor get x and y numbers as arguments, this arguments are the dimensions of an image.
	 * @param x width in pixels.
	 * @param y height in pixels
	 */
	public Map(int x, int y) {
		this._rangeX = new Range(0, x);
		this._rangeY = new Range(0, y);
		this._rangeLat = new Range(32.101658,32.106046);
		this._rangeLon = new Range(35.20238,35.21236);
	}

	/**
	 * This function converts two pixels to coordinates on the map.
	 * @param xLon x pixel.
	 * @param yLat y pixel.
	 * @return array in size two of coordinates.
	 */
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

	/**
	 * This function converts two coordinates to pixels on the map.
	 * @param xLon longitude of a gps point.
	 * @param yLat latitude of a gps point.
	 * @return array in size two of pixels.
	 */
	public int[] gpsToPixel(double xLon, double yLat) {
		int[] ans = new int[2];

		double proportionLon = this._rangeLon.proportion(xLon);
		double percentageX = this._rangeX.percentge(proportionLon);
		ans[0] = (int) percentageX;

		ans[1] = (int) ((this._rangeY.getMax() *yLat - this._rangeY.getMax() *32.106046)/(32.101658-32.106046));
		
		return ans;
	}




}