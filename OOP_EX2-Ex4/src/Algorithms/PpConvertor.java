package Algorithms;

public class PpConvertor {

	public double[] pt2px(double x,double y,double w, double h) {
		double a=(x*w)/360;
		double b=(y*h)/180;
		double[]d= {a,b};
		return d;
	}
	
	public double[] px2pt(double x,double y,double w, double h) {
		double a=(x*w)/360;
		double b=(y*h)/180;
		double[]d= {a,b};
		return d;
	}
}
