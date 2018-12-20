package MainTest;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DecimalFormat;

import org.junit.jupiter.api.Test;

import GIS.Pacman;
import Geom.Point3D;

class PathTest {

	@Test
	void test() {
		Point3D p = new Point3D(35.20697,32.10309);
		Point3D p1 = new Point3D(35.20750,32.10247);
		Point3D p2 = new Point3D(35.20768,32.10257);
		Pacman pacman = new Pacman(p,0,0,0);
		pacman.path.points.add(p1);
		pacman.path.points.add(p2);
		float distance = (float)(pacman.path.calc_distnce(0, 2))*100000;
		String s = null;
		s=s.format("%.2f",distance);
		assertEquals("102.16", s);
	}

}
