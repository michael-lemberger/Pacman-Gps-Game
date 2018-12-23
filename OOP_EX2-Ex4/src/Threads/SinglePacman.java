package Threads;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import GIS.Fruit;
import GIS.GIS_element;
import GIS.Pacman;
import GIS.Path;
import GUI.MyFrame;
import Geom.Point3D;
import javazoom.jl.player.Player;

/**
 *This class represent a single pacman movement.
 *The class work as a thread, get pacman and myFrame objects and play the movement of the pacman on the game board.
 * @author Liron Arad, Michael Lemberger, Maoz Grossman.
 *
 */
public class SinglePacman extends Thread {
	/**/
	Pacman pacman=null;
	MyFrame mf;
	int counter=0;
	boolean flag=true;
	int step=0;
	boolean right=false;
	/**
	 * This constructor gets pacman and myFrame objects and build a singlPacman object.
	 * @param pac
	 * @param mf
	 */
	public SinglePacman(Pacman pac,MyFrame mf) {
		this.pacman=pac;
		this.mf=mf;
		AddPath();
		
		
	}
	/**
	 * This function calculates the motion of the pecman along the vector until the pacman reach the fruit.
	 * @param pac pacman.
	 * @param fruit fruit target.
	 * @return new point of the pacman.
	 */
	private Point3D movment(Point3D pac,Point3D fruit,double part) {
		double x =((fruit.x()-pac.x())/(100/part));
		double y =(fruit.y()-pac.y())/(100/part);
		double z= (fruit.z()-pac.z())/(100/part);
		Point3D move= new Point3D(x,y,z);
		if(move.x()<0)
			right=true;
			else right=false;
		return move;
	}


	/**
	 * This function run the pacman motion.
	 * For each fruit in the path the function call the movement function.
	 * The function print the pacman in the new point until the pacman reach the fruit.
	 * The function move the pacman along the list of fruits in the path.
	 * the function sleep's the thread to create motion on the screen.
	 */
	@Override
	public void run() {
		if(flag) {
		Iterator<Point3D>it = pacman.path.points.iterator();
		double speed=pacman.get_speed();
		while(it.hasNext()) {
			Point3D f= it.next();
			counter++;
			Point3D a=new Point3D (pacman.get_p());
			double distance=a.distance2D(f);
			double steps=0;
			if(counter>1) {
				Point3D move= movment(pacman.get_p(),f,speed);
				while(steps<=distance&&Math.abs(steps-distance)>0.000001) {
					pacman.get_p().add(move);
					steps+=pacman.get_p().distance2D(a);
					a=new Point3D (pacman.get_p());
					mf.repaint();
					try {
						sleep((long)(80));
						}
					 catch (InterruptedException e) {
					}
				}
				remove(f);
				try{

		             FileInputStream fis = new FileInputStream("res/crunch.mp3");
		             Player playMP3 = new Player(fis);
		             
		             playMP3.play();

		        }  catch(Exception e){
		             System.out.println(e);
		        }

			}
			mf.repaint();
		}
		}
		mf.s+=" pacman number "+pacman.get_id()+" ate "+ (counter-1) +" fruits\n";
			if(ShowScore()) {
				JOptionPane.showMessageDialog(null, mf.s);
			mf.game.fruits=new ArrayList<>();
			mf.status=0;
			mf.unable=true;
			mf.LineDraw=new ArrayList<Point3D[]>();
			}
		flag=false;
	}
	
	
	
	private void remove(Point3D p) {
		Iterator<GIS_element>it = mf.game.fruits.iterator();
		while(it.hasNext()) {
			try {
			Fruit f =  (Fruit) it.next();
			
			if( f.get_p().equalsXY(p)) {
				f._img=null;
			}
			}
			catch (Exception e) {

				return;
			}
		}
		
	}
	
	private boolean ShowScore() {
		String[] lines = mf.s.split("\r\n|\r|\n");
		
		return lines.length==mf.game.pacmans.size();
		
	}
	
	private void AddPath() {
		Path path= new Path (pacman.path.points);
		Point3D[]arr= new Point3D[ pacman.path.points.size()];
		for(int i=0;i<path.points.size();i++) {
			arr[i]=new Point3D(path.points.get(i));
		}
		mf.LineDraw.add(arr);
	}
	
}