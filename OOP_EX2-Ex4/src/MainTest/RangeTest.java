package MainTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import GIS.Range;

class RangeTest {

	@Test
	void isInRange() {
		Range range = new Range(0, 100);
		assertTrue(range.isIn(34));;
	}
	
	@Test
	void isNotInRange() {
		Range range = new Range(0,100);
		assertFalse(range.isIn(-120));
	}
	
	@Test
	void dxTest() {
		Range range = new Range(0,100);
		assertEquals(100,range.dx() );
	}
	
	@Test
	void proportionTest() {
		Range range = new Range(0,100);
		assertEquals(0.25, range.proportion(25));
	}
	
	@Test
	void percentageTest() {
		Range range = new Range(0,100);
		assertEquals(25, range.percentge(0.25));
	}

}
