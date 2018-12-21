package GUI;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import File_format.CsvGameWriter;
import File_format.Path2KML;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.Game;
import GIS.Map;
import GIS.Pacman;
import GIS.Path;
import GIS.PointDis;
import Geom.Point3D;
import Threads.SimplePlayer;
import Threads.playThread;

public class MyFrame extends JFrame implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 01L;
	/**************************frame variables **********************************/
	private JTextField filename = new JTextField(), dir = new JTextField();
//	private JButton open = new JButton("Open"), save = new JButton("Save");
	BufferedImage image = null;
	playThread player = new playThread(this);
	int h;
	int w;
	public int status=0;
	//to add fruits or Pacmen.
	boolean reput=false;
	// unable the other menu bars while it's running 
	public boolean unable = true;
	public Game game= new Game();
	Map map;
	//string score
	public String s="";
	public ArrayList<Point3D[]> LineDraw=new ArrayList<Point3D[]>();


	public MyFrame() {
		/***********************menu bar ******************************/
		MenuBar menubar = new MenuBar();
		Menu menu=new Menu("file");
		MenuItem open =new MenuItem("open");
		MenuItem save =new MenuItem("save");
		Menu menu2=new Menu("game");
		MenuItem pacman =new MenuItem("add pacman");
		MenuItem fruit =new MenuItem("add fruit");
		MenuItem nully =new MenuItem("new game");
		MenuItem play =new MenuItem("play");
		/*******************Labels and buttons******************************************/
		/*open label */
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				unable=true;
				JFileChooser c = new JFileChooser();
				// Demonstrate "Open" dialog:
				int rVal = c.showOpenDialog(dir);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					filename.setText(c.getSelectedFile().getName());
					dir.setText(c.getCurrentDirectory().toString());
					String openFile= c.getCurrentDirectory().toString()+"\\"+c.getSelectedFile().getName();
					System.out.println(openFile);
					try {
						game = new Game(openFile);
						repaint();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "not a csv file!");
					}
				}
				if (rVal == JFileChooser.CANCEL_OPTION) {
					filename.setText("You pressed cancel");
					dir.setText("");
				}
			}
		});

		/*save label*/
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(unable) {
				JFileChooser c = new JFileChooser();
				// Demonstrate "save" dialog:
				int rVal = c.showSaveDialog(dir);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					filename.setText(c.getSelectedFile().getName());
					dir.setText(c.getCurrentDirectory().toString());
					String saveFile= c.getCurrentDirectory().toString()+"\\"+c.getSelectedFile().getName();
					System.out.println(saveFile);
					if(saveFile.endsWith(".csv")) {
						CsvGameWriter csv = new CsvGameWriter(game, saveFile);
					}
					else if(saveFile.endsWith(".kml")) {
					Path2KML kml = new Path2KML(game,saveFile);
					}
					else {
						JOptionPane.showMessageDialog(null, "name of file must end with '.csv' or '.kml'!");
					}
				}
				if (rVal == JFileChooser.CANCEL_OPTION) {
					filename.setText("You pressed cancel");
					dir.setText("");
				}
			}
			}
		});



		/*add pacman to screen*/
		pacman.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(unable) {
				status=1;
				reput=false;
				JOptionPane.showMessageDialog(null, "a click to change location \n double click to add a pacman");
			}
		}
		});
		/*add fruit to the screen*/
		fruit.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(unable) {
				status=2;
				reput=false;
				JOptionPane.showMessageDialog(null, "a click to change location \n double click to add a fruits ");
			}
			}
		});
		/*new game*/
		nully.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				status=0;
				nullify();
			}
		});
		
		/*run the game*/
		play.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				status=0;
				playGame();
			}
		});
		




		/************************additions******************************/
		menu.add(open);
		menu.add(save);
		menu2.add(pacman);
		menu2.add(fruit);
		menu2.add(nully);
		menu2.add(play);
		menubar.add(menu);
		menubar.add(menu2);
		setMenuBar(menubar);

		/*****************************image methods****************************/

		try {
			image = ImageIO.read(new File("res/Ariel1.png"));
		} catch (IOException e) {
		}
		this.setSize(1433,642);
		h=image.getHeight();
		w=image.getWidth();
		/****************************mouse listener*************************/
		addMouseListener(this);


		/***************************reads when the frame was changed************************************/


		//		this.getRootPane().addComponentListener(new ComponentAdapter() {
		//			public void componentResized(ComponentEvent e) {
		//				// This is only called when the user releases the mouse button.
		//				ratioh= (double)h/(double)getHeight();
		//				ratiow=(double)w/(double)getWidth();
		//				xl= (int) (xl/ratiow);
		//				yl= (int) (yl/ratioh);
		//				h=getHeight(); w=getWidth();
		//
		//
		//
		//			}
		//		});
	}
	int x;
	int y;
	Image scaledImage;
	String randomFruit="res\\bananagif.gif";



	/*****************************paint ********************************************************/
	public void paint(Graphics g) {

		if (image != null) {
			//Background image  
			scaledImage = image.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			g.drawImage(scaledImage, 0, 0, this);
			map = new Map(this.getWidth(),this.getHeight());
			/**Lines**/
			if (status==3) {
				for (Point3D[] path:LineDraw) {
					for(int i=0;i<path.length-1;i++) {
						Point3D p0=new Point3D(path[i]);
						Point3D p1=new Point3D(path[i+1]);
						
						int[] line = map.gpsToPixel(p0.x(), p0.y());
						int[] line2 = map.gpsToPixel(p1.x(), p1.y());
				        g.setColor(randomColor());
						g.drawLine(line[0],line[1], line2[0], line2[1]);
					}
					
				}
			}
			
			
			/**fruit**/

			
			Iterator<GIS_element> itFruits = game.fruits.iterator();
			while(itFruits.hasNext()) {
				try {
				Fruit fruit = (Fruit) itFruits.next();
				int[] pixel = map.gpsToPixel(fruit.get_p().x(), fruit.get_p().y());
				//					System.out.println(pixel[0]+", "+pixel[1]);
				g.drawImage(fruit.get_img(), pixel[0]-20,pixel[1]-20,40,40,this);
				}
				catch (Exception e) {
					System.out.println("couldn't find the fruit");
					break;
				}
			}
			


			if (status==2) {
				if(reput==false) {
					x=-8; y=-8;
				}
				Image  fruty=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
				try {
					fruty = new ImageIcon(randomFruit).getImage();
					g.drawImage(fruty, x-20,y-20,40,40,this);


				} catch (Exception e) {
					System.out.println("no fruit");
				}

			}
			
			/**pacmans**/
			Iterator<Pacman> itPacmans = game.pacmans.iterator();
			while(itPacmans.hasNext()) {
				Pacman pacman = itPacmans.next();
				int[] pixel = map.gpsToPixel(pacman.get_p().x(), pacman.get_p().y());
				//System.out.println(pixel[0]+", "+pixel[1]);
				
				
//				if (status==3) {
//					for(int i=0;i<pacman.path.points.size()-1;i++) {
//						Point3D p0=new Point3D(pacman.path.points.get(i));
//						Point3D p1=new Point3D(pacman.path.points.get(i+1));
//						int[] line = map.gpsToPixel(p0.x(), p0.y());
//						int[] line2 = map.gpsToPixel(p1.x(), p1.y());
////				        g.setColor(randomColor());
//						g.drawLine(line[0],line[1], line2[0], line2[1]);
//						
//					} 
//					
//				}
				g.drawImage(pacman.get_img(), pixel[0]-40,pixel[1]-30,80,60,this);
				
			}
			if(status==1) {
				if(reput==false) {
					x=-8; y=-8;
				}
				Image pacy=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
				try {
					pacy = new ImageIcon("res\\pacman1.gif").getImage();
					g.drawImage(pacy, x-30,y-30,60,60,this);


				} catch (Exception e) {
					System.out.println("no pacman");
				}
				
			}
		}
	}








	@Override
	public void mouseClicked(MouseEvent e) {
		if (status==2) {
			if(e.getClickCount()==2)
				AddFruit(e.getX(), e.getY());
		}
		if(status==1) {
			if(e.getClickCount()==2)
				Addpacman(e.getX(), e.getY());
		}

	}








	@Override
	public void mouseEntered(MouseEvent e) {


	}
	@Override
	public void mouseExited(MouseEvent e) {

	}
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("["+e.getX()+", "+e.getY()+"]\n" );
		reput=true;
		if(reput==true) {
			x=e.getX();
			y= e.getY();
			if (status==2) {
				if(e.getClickCount()!=2) {
					randomFruit=getFIcon();
					repaint();
				}
			}
			if (status==1) {
				if(e.getClickCount()!=2) {
					repaint();
				}
			}
		}

	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	
	

	/*****************************get a random fruit icon*************************************/
	public String getFIcon() {
		int i= (int) (Math.random()*6);
		String[]icon= {
				"res\\bananagif.gif"
				,"res\\pinapple.gif"
				,"res\\strawbery.gif"
				,"res\\orange.gif"
				,"res\\apple.gif"
				,"res\\pera.gif"
		};
		return icon[i];
	}
private void AddFruit(int x,int y) {
		
		map = new Map(this.getWidth(),this.getHeight());
		double[]gps=map.pixelToGps(x, y);
		
		boolean fruitExist = false;
		Iterator<GIS_element> it = this.game.fruits.iterator();
		while(it.hasNext()) {
			Fruit prevFruit = (Fruit) it.next();
			if((gps[0] == prevFruit.get_p().x()) && (gps[1] == prevFruit.get_p().y())) {
				fruitExist = true;
				break;
			}
		}
		
		if(fruitExist == false) {
		Image temp= new ImageIcon(randomFruit).getImage();
		Fruit F= new Fruit(new Point3D(gps[0],gps[1]),game.fruits.size(),temp);
		game.fruits.add(F);
		System.out.println("number of fruits in the game: " + game.fruits.size());
		repaint();
		}
	}
	
	private void Addpacman(int x2, int y2) {
		map = new Map(this.getWidth(),this.getHeight());
		double[]gps=map.pixelToGps(x, y);
		
		boolean pacmanExist = false;
		Iterator<Pacman> iter = this.game.pacmans.iterator();
		while(iter.hasNext()) {
			Pacman prevPacman = iter.next();
			if((gps[0] == prevPacman.get_p().x()) && (gps[1] == prevPacman.get_p().y())) {
				pacmanExist = true;
				break;
			}
		}
		
		if(pacmanExist == false) {
		Image temp= new ImageIcon("res//pacman1.gif").getImage();
		Pacman P= new Pacman(new Point3D(gps[0],gps[1]),game.pacmans.size(),1,1,temp );
		game.pacmans.add(P);
		System.out.println("number of pacmans in the game: " + game.pacmans.size());
		}
	}

	private Color randomColor() {
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		return new Color(r, g, b);

	}
	
	private void nullify() {
		game= new Game(); 
	}
	
	private void playGame() {
		if(player.isAlive()) {
			player.close();
		}
		s="";
		unable=false;
		player=new playThread(this);
		player.run();
		
	}



	/********************main************************/
	public static void main(String[] args) {
		String path = "res/Pac-man theme remix - By Arsenic1987.mp3";
		SimplePlayer player = new SimplePlayer(path);
		Thread t = new Thread(player);
		t.start();
		MyFrame ejemplo= new MyFrame(); 
		ejemplo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ejemplo.setVisible(true);

	}

}
