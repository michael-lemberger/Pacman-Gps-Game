package MainTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GIS.Map;
import Geom.Point3D;

class MapTest {

	@Test
	void gpsToPixelTest() {
		Map map = new Map(1388,642);
		Point3D p = new Point3D(35.20748504322766,32.10247135202493);
		int[] pixels = map.gpsToPixel(p.x(), p.y());
		assertEquals(710, pixels[0]);
	}
	
	@Test
	void pixelToGpsTest() {
		Map map = new Map(1388,642);
		double[] gps =map.pixelToGps(710,523);
		assertEquals(35.20748504322766,gps[0]);
		
	}

}
