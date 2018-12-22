package MainTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Algorithms.ShortestPathAlgo;
import GIS.Game;

class AlgoTest {

	@Test
	void IsEmptytest() {
		Game game=null;
		try {
			game = new Game("res//game_1543693911932_b.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ShortestPathAlgo s=new ShortestPathAlgo(game);
		boolean b=false;
		if(s.isEmpty()) {
			b=true;
		}
		assertTrue(b);
	}

}
