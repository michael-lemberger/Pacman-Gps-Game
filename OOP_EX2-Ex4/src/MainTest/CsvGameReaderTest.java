package MainTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import File_format.CsvGameReader;

class CsvGameReaderTest {

	@Test
	void CsvrReaderTest1() throws Exception {
		//csv reader test to game_1543684662657.csv
		String adress ="C:\\Users\\liron\\Desktop\\לימודים\\מונחה עצמים\\Ex3 (2)\\Ex3\\Ex3_data\\data\\game_1543684662657.csv";
		CsvGameReader game1 = new CsvGameReader();
		String[][] data = game1.csvmaker(adress);
		assertEquals("32.1045513", data[0][2]);
	}
	
	@Test
	void CsvrReaderTest2() throws Exception {
		//csv reader test to game_1543685769754.csv
		String adress ="C:\\Users\\liron\\Desktop\\לימודים\\מונחה עצמים\\Ex3 (2)\\Ex3\\Ex3_data\\data\\game_1543685769754.csv";
		CsvGameReader game2 = new CsvGameReader();
		String[][] data = game2.csvmaker(adress);
		assertEquals("35.20436658", data[14][3]);
	}

}
