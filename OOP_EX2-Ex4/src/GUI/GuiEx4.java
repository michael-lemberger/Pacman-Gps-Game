package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Algorithms.ConnectedGraph;
import DB.DataBase;
import File_format.CsvGameWriter;
import File_format.Path2KML;
import GIS.Block;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.Game;
import GIS.Ghost;
import GIS.Map;
import GIS.Pacman;
import GIS.Player;
import Geom.Point3D;
import Robot.Play;
import Threads.Ex4Thread;
import Threads.Ex4_Auto;
import Threads.SimplePlayer;


public class GuiEx4 extends JFrame implements MouseListener{

	private static final long serialVersionUID = 01L;
	/**************************frame variables **********************************/
	private JTextField filename = new JTextField(), dir = new JTextField();
	BufferedImage image = null;
	int h;
	int w;
	// to change position 
	private int status=0;
	// unable the other menu bars while it's running 
	public boolean unable = true;
	public Game game= new Game();
	public Player player=new Player();
	public Play GamePlayer;
	public Map map;
	public GuiEx4() {
		/***********************menu bar ******************************/
		MenuBar menubar = new MenuBar();
		Menu menu=new Menu("File");
		MenuItem open =new MenuItem("Open");
		MenuItem save =new MenuItem("Save");
		Menu menu2=new Menu("Game");
		Menu menu3=new Menu("Score");
		MenuItem pacman =new MenuItem("Add pacman");
		MenuItem fruit =new MenuItem("Add fruit");
		MenuItem nully =new MenuItem("New game");
		MenuItem play =new MenuItem("Play");
		MenuItem handsfree =new MenuItem("Hands-free");
		MenuItem BestScoreEver =new MenuItem("Best score ever");
		MenuItem MyBestScore =new MenuItem("My best score");
		
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
					openFile=openFile.replace("\\", "\\\\");

					System.out.println(openFile);
					try {
						game = new Game(openFile);
						GamePlayer = new Play(openFile);
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
		handsfree.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				status=0;
				autoplay();
			}
		});

		BestScoreEver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataBase DB =new DataBase();
				DB.BestScoreEver();
				
			}
		});
		
MyBestScore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataBase DB =new DataBase();
				DB.MyBestScore();
				
			}
		});


		/************************additions******************************/
		menu.add(open);
		menu.add(save);
		//				menu2.add(pacman);
		//				menu2.add(fruit);
		menu2.add(nully);
		menu2.add(play);
		menu2.add(handsfree);
		//
		menu3.add(BestScoreEver);
		menu3.add(MyBestScore);
		//
		menubar.add(menu);
		menubar.add(menu2);
		menubar.add(menu3);
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



	}
	int x;
	int y;
	Image scaledImage;
	String randomFruit="res\\bananagif.gif";


	/*****************************paint ********************************************************/
	public void paint(Graphics g) {
		
		
		/*********************layer1-background******************************/
		if (image != null) {
			//Background image  
			scaledImage = image.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			g.drawImage(scaledImage, 0, 0, this);
			map = new Map(this.getWidth(),this.getHeight());
			player= game.player;

			/***************************layer2- blocks*********************************/

			Iterator<Block> itBlocks = game.blocks.iterator();
			while(itBlocks.hasNext()) {
				try {
					Block block= itBlocks.next();
					int dimension[]= block.setDimension(this.map);
					g.setColor(Color.lightGray);
					g.fillRect(dimension[0], dimension[1], dimension[2],dimension[3]);
				}
				catch (Exception e) {
					System.out.println("the block doesn't existe");
					break;
				}
			}

			/******************************layer3- objects***************************/

			/**fruit**/


			Iterator<GIS_element> itFruits = game.fruits.iterator();
			while(itFruits.hasNext()) {
				try {
					Fruit fruit = (Fruit) itFruits.next();
					int[] pixel = map.gpsToPixel(fruit.get_p().x(), fruit.get_p().y());
					//					System.out.println(pixel[0]+", "+pixel[1]);
					g.drawImage(getimage("fruit"), pixel[0]-20,pixel[1]-20,40,40,this);
				}
				catch (Exception e) {
					System.out.println("couldn't find the fruit");
					break;
				}
			}



			//					if (status==2) {
			//						Image  fruty=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			//						try {
			//							fruty = new ImageIcon(randomFruit).getImage();
			//							g.drawImage(fruty, x-20,y-20,40,40,this);
			//
			//
			//						} catch (Exception e) {
			//							System.out.println("no fruit");
			//						}
			//
			//					}



			/**pacmans**/
			Iterator<Pacman> itPacmans = game.pacmans.iterator();
			while(itPacmans.hasNext()) {
				Pacman pacman = itPacmans.next();
				int[] pixel = map.gpsToPixel(pacman.get_p().x(), pacman.get_p().y());

				g.drawImage(getimage("pacman"), pixel[0]-30,pixel[1]-30,60,60,this);

			}


			//					if(status==1) {
			//						Image pacy=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			//						try {
			//							pacy = new ImageIcon("res\\pacman1.gif").getImage();
			//							g.drawImage(pacy, x-30,y-30,60,60,this);
			//
			//
			//						} catch (Exception e) {
			//							System.out.println("no pacman");
			//						}
			//						
			//					}



			/**Ghost**/
			Iterator<Ghost> itGhost = game.ghosts.iterator();
			while(itGhost.hasNext()) {
				Ghost ghost = itGhost.next();
				int[] pixel = map.gpsToPixel(ghost.get_p().x(),ghost.get_p().y());

				g.drawImage(getimage("ghost"), pixel[0]-25,pixel[1]-25,50,50,this);

			}


			/**Me Player**/
			if (status==0)
			g.drawImage(getimage("player"),x-30,y-30,60,60,this);
			else
			try {
				int[] pixel = map.gpsToPixel(player.get_p().x(),player.get_p().y());
				g.drawImage(getimage("player"),pixel[0]-30,pixel[1]-30,60,60,this);
			}
			catch (Exception e) {
				//						System.out.println("nap");
			}

		}
	}








	@Override
	public void mouseClicked(MouseEvent e) {

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
		x=e.getX();
		y= e.getY();
		if(status==0)
		try {
			setInitLocation(x,y);
			double[] arr = map.pixelToGps(x, y);
			Point3D p = new Point3D(arr[0], arr[1]);
			Image img=null;
			this.game.player=new Player(p, 1, 2, 2, img);
			
		} catch (Exception e1) {
			
		}
		else {
			rotate( x, y);
		}

	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}


	//		private void AddFruit(int x,int y) {
	//				
	//				map = new Map(this.getWidth(),this.getHeight());
	//				double[]gps=map.pixelToGps(x, y);
	//				
	//				boolean fruitExist = false;
	//				Iterator<GIS_element> it = this.game.fruits.iterator();
	//				while(it.hasNext()) {
	//					Fruit prevFruit = (Fruit) it.next();
	//					if((gps[0] == prevFruit.get_p().x()) && (gps[1] == prevFruit.get_p().y())) {
	//						fruitExist = true;
	//						break;
	//					}
	//				}
	//				
	//				if(fruitExist == false) {
	//				Image temp= new ImageIcon(randomFruit).getImage();
	//				Fruit F= new Fruit(new Point3D(gps[0],gps[1]),game.fruits.size(),temp);
	//				game.fruits.add(F);
	//				System.out.println("number of fruits in the game: " + game.fruits.size());
	//				repaint();
	//				}
	//			}
	//			
	//			private void Addpacman(int x2, int y2) {
	//				map = new Map(this.getWidth(),this.getHeight());
	//				double[]gps=map.pixelToGps(x, y);
	//				
	//				boolean pacmanExist = false;
	//				Iterator<Pacman> iter = this.game.pacmans.iterator();
	//				while(iter.hasNext()) {
	//					Pacman prevPacman = iter.next();
	//					if((gps[0] == prevPacman.get_p().x()) && (gps[1] == prevPacman.get_p().y())) {
	//						pacmanExist = true;
	//						break;
	//					}
	//				}
	//				
	//				if(pacmanExist == false) {
	//				Image temp= new ImageIcon("res//pacman1.gif").getImage();
	//				Pacman P= new Pacman(new Point3D(gps[0],gps[1]),game.pacmans.size(),1,1,temp );
	//				game.pacmans.add(P);
	//				System.out.println("number of pacmans in the game: " + game.pacmans.size());
	//				}
	//			}

	
	private void setInitLocation(int x,int y) throws Exception {
		double [] coordes= map.pixelToGps(x,y);
		System.out.println(coordes[1]+","+coordes[0]);
		this.GamePlayer.setInitLocation(coordes[1],coordes[0]);
	}
	
	
	
	public double azimute=0;
	public void rotate(int x,int y) {
		double[]coords=map.pixelToGps(x,y);
		System.out.println(coords[0]+","+coords[1]);
		player.setAzimute(coords[0],coords[1]);
		azimute=player.getAzimute();
	}

	private Image getimage(String iconimg) {
		if(iconimg.equalsIgnoreCase("pacman")) {
			return new ImageIcon("res\\pacman1.gif").getImage();

		}
		if(iconimg.equalsIgnoreCase("fruit")) {
			return new ImageIcon("res\\strawbery.gif").getImage();
		}
		if(iconimg.equalsIgnoreCase("player")) {
			return new ImageIcon("res\\player.gif").getImage();
		}
		if(iconimg.equalsIgnoreCase("ghost")) {
			return new ImageIcon("res\\gengar.gif").getImage();
		}
		return null;

	}


	private void nullify() {
		game= new Game(); 
	}

	private void playGame() {
		status=1;
		Ex4Thread play=new Ex4Thread(this);
		play.start();

	}
	private void autoplay() {
		status=1;
		Ex4_Auto auto= new Ex4_Auto(this) ;
		auto.start();
	}

	/********************main************************/
	public static void main(String[] args) {
		String path = "res/Pac-man theme remix - By Arsenic1987.mp3";
		SimplePlayer player = new SimplePlayer(path);
		Thread t = new Thread(player);
		t.start();
		GuiEx4 ejemplo= new GuiEx4(); 
		ejemplo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ejemplo.setVisible(true);

	}

}