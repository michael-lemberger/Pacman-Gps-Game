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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import GIS.Fruit;
import GIS.GIS_element;
import GIS.Game;
import GIS.Map;






public class MyFrame extends JFrame implements MouseListener{
	private JTextField filename = new JTextField(), dir = new JTextField();
	private JButton open = new JButton("Open"), save = new JButton("Save");
	BufferedImage image = null;
	int h;
	int w;
	double ratioh;
	double ratiow;
	int xl=910;
	int yl=396;
	int status=0;
	//to add fruits or Pacmen.
	boolean reput=false;
	Game game;
	Map map;



	public MyFrame() {
		/***********************menu bar ******************************/
		MenuBar menubar = new MenuBar();
		Menu menu=new Menu("file");
		MenuItem open =new MenuItem("open");
		MenuItem save =new MenuItem("save");
		Menu menu2=new Menu("game");
		MenuItem pacman =new MenuItem("add pacman");
		MenuItem fruit =new MenuItem("add fruit");

		/*******************Labels and buttons******************************************/
		/*open label */
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
						System.out.println(game);
						repaint();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
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
				JFileChooser c = new JFileChooser();
				// Demonstrate "save" dialog:
				int rVal = c.showSaveDialog(dir);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					filename.setText(c.getSelectedFile().getName());
					dir.setText(c.getCurrentDirectory().toString());
					String openFile= c.getCurrentDirectory().toString()+"\\"+c.getSelectedFile().getName();
					System.out.println(openFile);
				}
				if (rVal == JFileChooser.CANCEL_OPTION) {
					filename.setText("You pressed cancel");
					dir.setText("");
				}
			}
		});



		/*add pacman to screen*/
		pacman.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				status=1;
				reput=false;
				JOptionPane.showMessageDialog(null, "you chose to add pacmans");
			}
		});
		/*add fruit to the screen*/
		fruit.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				status=2;
				reput=false;
				JOptionPane.showMessageDialog(null, "a click to change location \n double click to add a fruits ");
			}
		});





		/************************additions******************************/
		menu.add(open);
		menu.add(save);
		menu2.add(pacman);
		menu2.add(fruit);
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
		this.getRootPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				// This is only called when the user releases the mouse button.
				ratioh= (double)h/(double)getHeight();
				ratiow=(double)w/(double)getWidth();
				xl= (int) (xl/ratiow);
				yl= (int) (yl/ratioh);
				h=getHeight(); w=getWidth();



			}
		});
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

			/**fruit**/

			Image  fruit=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			try {
				fruit = new ImageIcon("res\\strawbery.gif").getImage();
				g.drawImage(fruit, xl,yl,60,60,this);

			} catch (Exception e) {

			}
			if(this.game!=null) {
				Iterator<GIS_element> it = game.fruits.iterator();
				while(it.hasNext()) {
					Fruit fruitCsv = (Fruit) it.next();
					map = new Map(this.getWidth(),this.getHeight());
					int[] pixel = map.gpsToPixel(fruitCsv.get_p().x(), fruitCsv.get_p().y());
					System.out.println(pixel[0]+", "+pixel[1]);
					g.drawImage(fruitCsv.get_img(), pixel[0],pixel[1],60,60,this);
					repaint();
				}
			}
			if (status==2) {
				if(reput==false) {
					x=-8; y=-8;
				}
				Image  strawbarry=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
				try {
					strawbarry = new ImageIcon(randomFruit).getImage();
					g.drawImage(strawbarry, x,y,60,60,this);

				} catch (Exception e) {
					System.out.println("no fruit");
				}

			}
		}
	}
	
	
	
	
	
	 private class Fruity {

	        Image fruity;
	        int x = 150;
	        int y = 125;

	        public Fruity(Image image) {
	            fruity = image;
	        }

	        public void drawFireball(Graphics g) {
	            g.drawImage(fruity, x, y, 75, 50, null);
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
		reput=true;
		if(reput==true) {
			x=e.getX();
			y= e.getY();
			if (status==2)
				randomFruit=getIcon();
		}
		repaint();

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		//		System.out.println(e.getPoint());
		//        Point point = e.getPoint();
		//         repaint();

	}


	public  void getXYfromLatLon(double latitude, double longitude) {
		// get x value
		int mapWidth = image.getWidth(), mapHeight = image.getHeight();
		int pX = (int)((longitude+180.)*(mapWidth/360.));

		// convert from degrees to radians
		double latRad = Math.toRadians(latitude);

		// get y value
		double mercN = Math.log(1+(latRad/2.));
		int pY = (int)((mapHeight/2.)-(mapHeight*mercN/(2.*Math.PI)));
		System.out.println("x = "+pX+", y = "+pY);
	}
	/*****************************get a random fruit icon*************************************/
	public String getIcon() {
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







	/********************main************************/
	public static void main(String[] args) {
		MyFrame ejemplo= new MyFrame(); 
		ejemplo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ejemplo.setVisible(true);
		//		System.out.println(ejemplo.getWidth()+"X"+ejemplo.getHeight());

	}

}
