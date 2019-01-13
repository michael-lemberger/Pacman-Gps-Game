package Threads;

import java.util.ArrayList;

import Algorithms.Cgraph;
import Algorithms.DijkstraAlgo;
import GIS.Game;
import GIS.Node;
import GUI.GuiEx4;
import Geom.Point3D;
import Robot.Play;
/**
 * The main Thread, Based on the thread we got from
 * boaz.
 * we added the usage of DijakstraAlgo and Cgraph.
 * we added the rotate function Iterating over the path
 * and giving the general direction.
 * @author Michael Lemberger,Liron Arad,Maoz Grossman.
 *
 */
public class Ex4_Auto extends Thread{
	GuiEx4 gui;
	boolean play=true;
	DijkstraAlgo D;
	static Cgraph C;
	boolean flag;
	int counter=1;
	Point3D position;
	public Ex4_Auto(GuiEx4 guiex4) {
		this.gui= guiex4;
		flag=false;
		position= new Point3D(0,0,0);
		}
		public void run() {
			while(play) {
			// 1) Create a "play" from a file (attached to Ex4)
			Play play1 = gui.GamePlayer;
			
			// 2) Set your ID's - of all the group members
			play1.setIDs(308214105,204577514,203668116);
			
			// 3)Get the GPS coordinates of the "arena"
			String map_data = play1.getBoundingBox();
			System.out.println("Bounding Box info: "+map_data);
			
			// 4) get the game-board data
			ArrayList<String> board_data = play1.getBoard();
			for(int i=0;i<board_data.size();i++) {
				System.out.println(board_data.get(i));
			}
			System.out.println();
//			System.out.println("Init Player Location should be set using the bounding box info");
			
			// 5) Set the "player" init location - should be a valid location
//			play1.setInitLocation(32.1040,35.2061);
			
			// 6) Start the "server"
			play1.start(); // default max time is 100 seconds (1000*100 ms).
			
			// 7) "Play" as long as there are "fruits" and time
//			for(int i=0;i<10;i++) {
			int i=0;
				while(play1.isRuning()) {
					i++;
			// 7.1) this is the main command to the player (on the server side)	
				System.out.println("***** "+i+"******");
				
				rotate_null(); 
			// 7.2) get the current score of the game
				String info = play1.getStatistics();
				System.out.println(info);
			// 7.3) get the game-board current state
				board_data = play1.getBoard();
				gui.game=new Game(board_data);
				
				if(!flag) {
					 C=new Cgraph(gui.game,gui.map) ;
					 System.out.println("new graph");
					 flag=true;
				}
				try {
				D=new DijkstraAlgo(gui.game,C);
				}
				catch (Exception e) {
					// TODO: handle exception
				}
				
				
				rotate(); 

				gui.repaint();
				try {
				sleep(100);
				}
				catch (Exception e) {
				}
				for(int a=0;a<board_data.size();a++) {
					System.out.println(board_data.get(a));
				}
				
				System.out.println();
			}
			// 8) stop the server - not needed in the real implementation.
			play1.stop();
			System.out.println("**** Done Game (user stop) ****");
//			
			// 9) print the data & save to the course DB
			String info = play1.getStatistics();
			System.out.println(info);
			play=false;
			}
		}
		/**
		 * Finds the target
		 * gets the general direction.
		 */
		private void rotate() {
			ArrayList<String> path=new ArrayList<String>();
			path.addAll(D.path);
			Node n=null;
			if(counter<path.size()) {
			String s=path.get(counter);
			for(Node n1:C.nodes) {
				if(n1._name.equals(s)) {
					n=n1;
					break;
				}
			}
			gui.rotate((int)(n.inPixel.x()),(int)(n.inPixel.y()));
			Point3D p=gui.game.player._p;
			int [] arr=gui.map.gpsToPixel(p.x(), p.y());
			Point3D inPixel=new Point3D(arr[0],arr[1]);
			if(inPixel.equalsXY(n.inPixel)) {
				counter++;
			}
			}
			else {
				if(D.fruit!=null) {
				Point3D p=new Point3D(D.fruit.get_p().x(),D.fruit.get_p().y());
				int [] arr=gui.map.gpsToPixel(p.x(),p.y());
				Node fruit=new Node(p,new Point3D (arr[0],arr[1]),"b");
				gui.rotate((int)(fruit.inPixel.x()),(int)(fruit.inPixel.y()));
				if(!gui.game.contains(D.fruit)) {
					counter=1;
					for(int i=0;i<100;i++)
					System.out.println(counter);
					flag=false;
				}
				position=p;
			}
			}
			}

		private void rotate_null() {
			double degree= gui.azimute;
			gui.GamePlayer.rotate(degree);
		}
		}