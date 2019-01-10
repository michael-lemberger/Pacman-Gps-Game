package Geom;


public class Line {
	Point3D inPixel1,inPixel2;
	public double ax,b,Y,X;
	public Line(Point3D inPixel1,Point3D inPixel2) {
		this.inPixel2=inPixel2;
		this.inPixel1=inPixel1;
		if(inPixel1.x()==inPixel2.x()) {
			X=inPixel1.x();
			Y=0;
		}
		
		else if(inPixel1.y()==inPixel2.y()) {
			Y=inPixel2.y();
			X=0;
		}
		else {
		LineEquasion(this.inPixel1,this.inPixel2);
		X=0;
		Y=0;
		}
	}
	
	public void LineEquasion(Point3D inPixel1,Point3D inPixel2) {
		ax=((inPixel1.y())-(inPixel2.y()))/(inPixel1.x()-inPixel2.x());
		b=ax*(-(inPixel1.x()))+(inPixel1.y());//y-y0=ax(x-x0)=>y=(ax*x)-(ax*x0)+y0
	}
	

	@Override
	public String toString() {
		return "Line [inPixel1=" + inPixel1 + ", inPixel2=" + inPixel2 + ", Y=" + Y + ", X=" + X + "]";
	}

	public boolean isCutting(Line line) {
		double result=0;
		if(line.X!=0) {//y=ax*x+b
			result=ax*line.X+b;
			if(result>=(line.inPixel1.y())&&result<=(line.inPixel2.y())) {
				return true;
			}
		}
		else if(line.Y!=0) {//-ax*x=-ax*x0+y0-y/:-ax
			result=(this.b-line.Y)/(-(this.ax));
			if(result>=line.inPixel1.x()&&result<=line.inPixel2.x()) {
				return true;
			}
		}
		return false;
	}
}
