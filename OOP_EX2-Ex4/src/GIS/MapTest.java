package GIS;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import Geom.Point3D;


class MapTest {

	@Test
	void pixelToGpsTest() {
		BufferedImage ariel1 = null;
		File f = null;
		try{
			f = new File("res/Ariel1.png");
			ariel1 = ImageIO.read(f);
		}catch(IOException e){
			System.out.println(e);
		}
		Map map = new Map(ariel1);
		Point3D p = new Point3D(map.pixelToGps(731,544)[0], map.pixelToGps(731,544)[1]);
		assertEquals(35.20764362193362, p.x());
		assertEquals(32.102327819314645, p.y());
	}
	
	@Test
	void gpsToPixelTest() {
		BufferedImage ariel1 = null;
		File f = null;
		try{
			f = new File("res/Ariel1.png");
			ariel1 = ImageIO.read(f);
		}catch(IOException e){
			System.out.println(e);
		}
		Map map = new Map(ariel1);
		double[] pixels = new double[2];
		pixels[0] = map.gpsToPixel(35.20764362193362, 32.102327819314645)[0];
		pixels[1] = map.gpsToPixel(35.20764362193362, 32.102327819314645)[1];
		assertEquals(731,(int)pixels[0]);
		assertEquals(543, (int)pixels[1]);
	}

}
