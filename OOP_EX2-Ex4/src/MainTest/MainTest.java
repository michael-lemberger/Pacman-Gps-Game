package MainTest;

import Algorithms.MultiCsv;
import Algorithms.ShortestPathAlgo;
import GIS.Game;
import GIS.GisLayer;

public class MainTest {

	public static void main(String[] args) throws Exception {
		
		Game game=new Game ("C:\\Users\\Simple Man\\Desktop\\מדעי המחשב\\Ex3 (2) (2)\\Ex3\\Ex3_data\\data\\game_1543684662657.csv");
		ShortestPathAlgo s=new ShortestPathAlgo(game);
		
	}

}
