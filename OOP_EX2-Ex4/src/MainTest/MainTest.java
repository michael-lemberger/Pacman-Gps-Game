package MainTest;

import java.util.Iterator;

import Algorithms.MultiCsv;
import Algorithms.ShortestPathAlgo;
import GIS.Game;
import GIS.GisLayer;
import GIS.Pacman;

public class MainTest {

	public static void main(String[] args) throws Exception {
		
		Game game=new Game ("res/game_1543684662657.csv");
		ShortestPathAlgo s=new ShortestPathAlgo(game);
		Iterator<Pacman>pacmans=game.pacmans.iterator();
		int i=1;
		while (pacmans.hasNext()) {
			Pacman p = (Pacman) pacmans.next();
			System.out.println("-p"+i+"-");
			System.out.println(p.path.toString()+"\n");
			i++;
		}
	}

}
