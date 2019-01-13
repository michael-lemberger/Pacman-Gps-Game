package MainTest;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import Geom.Line;
import Geom.Point3D;

class LineTest {

	@Test
	void test() {
		Line l1=new Line (new Point3D(2,5),new Point3D(0,3));
		Line l2=new Line (new Point3D(2,0),new Point3D(2,5));
		assertTrue(l1.isCutting(l2));
	}

}
