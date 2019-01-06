package GIS;

import java.awt.Image;
import java.util.Iterator;

import javax.swing.ImageIcon;

public class Images {

	public Images() {
		
	}
	
	/*****************************get icon*************************************/
	
	public Image pacimg = new ImageIcon("res\\pacman1.gif").getImage();
	public static Image playerimg = new ImageIcon("res\\player.gif").getImage();
	
	public Image getFruitIcon() {
		int i= (int) (Math.random()*6);
		String[]icon= {
				"res\\bananagif.gif"
				,"res\\pinapple.gif"
				,"res\\strawbery.gif"
				,"res\\orange.gif"
				,"res\\apple.gif"
				,"res\\pera.gif"
		};
		return(new ImageIcon(icon[i]).getImage());
	}

	public Image getGhostIcon() {
		int i= (int) (Math.random()*5);
		String[]icon= {
				"res\\gengar.gif"
				,"res\\ghost.gif"
				,"res\\ghostRed.gif"
				,"res\\gostPink.gif"
				,"res\\gostYellow.gif"
		};
		return (new ImageIcon(icon[i]).getImage());
	}
}
