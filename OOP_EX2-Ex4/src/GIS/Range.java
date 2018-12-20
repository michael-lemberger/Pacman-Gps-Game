package GIS;

/**
 * This class represnt a range between two values.
 * @author Liron Arad,Michael Lemberger,Maoz Grossman.
 */
public class Range {
	private double _max;
	private  double _min;

	/**
	 * This constructor get minimum value and maximum value of a range and build an Range object.
	 * @param min minimum value.
	 * @param max maximum value.
	 */
	public Range(double min, double max) {
		this._max = max;
		this._min = min;
	}
	
	/**
	 * This function check if x value is in the range or is'nt.
	 * @param x a value to check.
	 * @return true or false.
	 */
	public boolean isIn(double x) {
		if((x <= this._max) && (x >= this._min)) 
			return true;
		return false;
	}
	
	/**
	 * This function compute the length of the range.
	 * @return the length of the range.
	 */
	public double dx() {
		return (this._max - this._min);
	}
	
	/**
	 * This function compute the proportion of value on the range.
	 * @return the proportion of value on the range.
	 */
	public double proportion(double x) {
		if(isIn(x)) {
		return ((x-this._min)/dx());
		}
		return Double.MAX_VALUE;
	}
	
	/**
	 * This function compute the percentage of value on the range.
	 * @return the percentage of value on the range.
	 */
	public double percentge(double x) {
		return (this._min+(x*dx()));
	}
	
	
	public double getMax() {
		return _max;
	}

	public double getMin() {
		return _min;
	}

}
