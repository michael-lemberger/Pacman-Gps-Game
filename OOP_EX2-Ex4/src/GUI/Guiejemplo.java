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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Algorithms.PpConvertor;




public class Guiejemplo extends JFrame implements MouseListener{
	BufferedImage image = null;
	PpConvertor pp=new PpConvertor();
	int h;
	int w;
	double ratioh;
	double ratiow;
	int xl=910;
	int yl=396;
	int status=0;
	//to add fruits or pacmen
	boolean reput=false;
	
	

	public Guiejemplo() {
		/***********************menu bar ******************************/
		MenuBar menubar = new MenuBar();
		Menu menu=new Menu("file");
		MenuItem m1 =new MenuItem("open");
		MenuItem m2 =new MenuItem("save");
		Menu menu2=new Menu("game");
		MenuItem pacman =new MenuItem("add pacman");
		MenuItem fruit =new MenuItem("add fruit");

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
		menu.add(m1);
		menu.add(m2);
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
	//paint 
	public void paint(Graphics g) {

		if (image != null) {
			scaledImage = image.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			g.drawImage(scaledImage, 0, 0, this);
			
			/*******************************fruit*********************************************/

			Image  fruit=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			try {
				fruit = new ImageIcon("res\\strawbery.gif").getImage();
				g.drawImage(fruit, xl,yl,60,60,this);

			} catch (Exception e) {

			}
			if (status==2) {
				if(reput==false) {
					x=-1; y=-1;
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




	@Override
	public void mouseClicked(MouseEvent e) {

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		 

	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

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
		Guiejemplo ejemplo= new Guiejemplo(); 
		ejemplo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ejemplo.setVisible(true);
		System.out.println(ejemplo.getWidth()+"X"+ejemplo.getHeight());
	
	}

}
