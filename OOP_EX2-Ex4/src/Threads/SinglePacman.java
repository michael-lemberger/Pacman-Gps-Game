package Threads;

import java.util.Iterator;

import GIS.Pacman;
import GUI.MyFrame;
import Geom.Point3D;

public class SinglePacman extends Thread {
	Pacman pacman=null;
	MyFrame mf;
	int counter=0;
	public SinglePacman(Pacman pac,MyFrame mf) {
		this.pacman=pac;
		this.mf=mf;
	}
	private Point3D movment(Point3D pac,Point3D fruit) {
		Point3D move= new Point3D((fruit.x()-pac.x())/10,(fruit.y()-pac.y())/10,(fruit.z()-pac.z())/10);
		return move;
	}

	@Override
	public void run() {
		Iterator<Point3D>it = pacman.path.points.iterator();
		while(it.hasNext()) {
			Point3D f= it.next();
			counter++;
			if(counter>1) {
				Point3D move= movment(pacman.get_p(),f);
				while(!pacman.get_p().equals(f)) {
					pacman.get_p().add(move);
					mf.repaint();
					try {
						long speed= (long) (500/pacman.get_speed());
						sleep(speed);
					} catch (InterruptedException e) {
					}
				}

			}
			mf.repaint();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			System.out.println("kelev");
			}
		}

	}

}
