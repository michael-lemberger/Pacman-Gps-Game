package GIS;

public class Range {
	private double _max;
	private  double _min;

	public Range(double min, double max) {
		this._max = max;
		this._min = min;
	}
	
	public boolean isIn(double x) {
		if(x <= this._max || x >= this._min) 
			return true;
		return false;
	}
	
	public double dx() {
		return (this._max - this._min);
	}
	
	public double proportion(double x) {
		if(isIn(x)) {
		return ((x-this._min)/dx());
		}
		return Double.MAX_VALUE;
	}
	
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
