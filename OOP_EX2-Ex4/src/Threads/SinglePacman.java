package Threads;

import java.util.Iterator;

import GIS.Pacman;
import GUI.MyFrame;
import Geom.Point3D;

public class SinglePacman extends Thread {
	Pacman pacman=null;
	MyFrame mf;
	int counter=0;
	boolean flag=true;
	
	public SinglePacman(Pacman pac,MyFrame mf) {
		this.pacman=pac;
		this.mf=mf;
	}
	private Point3D movment(Point3D pac,Point3D fruit) {
		double x =((fruit.x()-pac.x())/30);
		double y =(fruit.y()-pac.y())/30;
		double z= (fruit.z()-pac.z())/30;
		Point3D move= new Point3D(x,y,z);
		return move;
	}

	@Override
	public void run() {
		if(flag) {
		Iterator<Point3D>it = pacman.path.points.iterator();
		while(it.hasNext()) {
			Point3D f= it.next();
			counter++;
			Point3D a=new Point3D (pacman.get_p());
			double distance=a.distance2D(f);
			double steps=0;
			if(counter>1) {
				Point3D move= movment(pacman.get_p(),f);
				while(steps<=distance&&Math.abs(steps-distance)>0.000001) {
					pacman.get_p().add(move);
					steps+=pacman.get_p().distance2D(a);
					a=new Point3D (pacman.get_p());
					mf.repaint();
					try {
						
						long speed= (long) (500/pacman.get_speed());
						sleep(speed);
						System.out.println("dest is here: "+ f.toString()+" but we are here: "+ pacman.get_p()+"\n");
						}
					 catch (InterruptedException e) {
					}
				}

			}
			mf.repaint();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			System.out.println("Error");
			}
		}
		}
		flag=false;
	}

}
