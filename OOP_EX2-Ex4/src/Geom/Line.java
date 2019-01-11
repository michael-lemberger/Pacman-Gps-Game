package Geom;


public class Line {
	Point3D inPixel1,inPixel2;
	public double ax,b,Y,X;
	double [] MMY,MMX;
	public Line(Point3D inPixel1,Point3D inPixel2) {
		this.inPixel2=inPixel2;
		this.inPixel1=inPixel1;
		MMX=MinMax(inPixel1.x(),inPixel2.x());
		MMY=MinMax(inPixel1.y(),inPixel2.y());
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
				if((line.X>=MMX[0]&&line.X<=MMX[1]))
				return true;
			}
		}
		else if(line.Y!=0) {//-ax*x=-ax*x0+y0-y/:-ax
			result=(this.b-line.Y)/(-(this.ax));
			if(result>=line.inPixel1.x()&&result<=line.inPixel2.x()) {
				if((line.Y>=MMY[0]&&line.Y<=MMY[1]))
				return true;
			}
		}
		return false;
	}
	
	private double [] MinMax(double a,double b) {
		double [] arr= new double [2];
		if(a>b) {
			arr[0]=b;
			arr[1]=a;
		}
		else {
			arr[0]=a;
			arr[1]=b;
		}
		return arr;
	}
}
