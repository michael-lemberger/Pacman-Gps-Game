package MainTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import com.mysql.fabric.xmlrpc.base.Data;

import File_format.CsvGameReader;

class CsvGameReaderTest {

	@Test
	void CsvrReaderTest1() throws Exception {
		//csv reader test to game_1543684662657.csv
		String adress ="res\\game_1543684662657.csv";
		CsvGameReader game1 = new CsvGameReader();
		ArrayList<String>data = game1.csvmaker(adress);
		String s=data.get(0);
		String[] line=s.split(",");
		assertEquals("32.1045513",line[2]);
	}
	
	@Test
	void CsvrReaderTest2() throws Exception {
		//csv reader test to game_1543685769754.csv
		String adress ="res\\game_1543685769754.csv";
		CsvGameReader game2 = new CsvGameReader();
		ArrayList<String>data = game2.csvmaker(adress);
		String s=data.get(14);
		String[] line=s.split(",");
		assertEquals("35.20436658", line[3]);
	}
}
