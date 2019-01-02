package Geom;

public class Line {
	Point3D inPixel1,inPixel2;
	double ax,b;
	public Line(Point3D inPixel1,Point3D inPixel2) {
		LineEquasion(this.inPixel1=inPixel1,this.inPixel2=inPixel2);
	}
	
	public void LineEquasion(Point3D inPixel1,Point3D inPixel2) {
		if(inPixel1.x()==inPixel2.x())
			ax=inPixel1.x();
		else
		ax=(inPixel1.y()-inPixel2.y())/(inPixel1.x()-inPixel2.x());
		b=ax*(-(inPixel1.x()))+inPixel1.y();
	}
	
	public boolean isCutting(Line line) {
		double result;
		if(line.ax==0) {
			result=(line.b+b)/ax;
			return true;
		}
		else if(line.ax!=0) {
			result=line.b+(ax*line.ax);
			if(result>=line.inPixel1.y()&&result<=line.inPixel2.y()) {
				return true;
			}
		}
		return false;
	}
}
