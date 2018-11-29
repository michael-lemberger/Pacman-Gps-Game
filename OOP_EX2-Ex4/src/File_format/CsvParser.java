package File_format;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import GIS.GisElement;
import GIS.GisMetaData;
import Geom.Point3D;

public class CsvParser {
	
	public CsvParser() {
		
	}

	public static String csvmaker (String adress) throws Exception 
	{
		
		String line = "";
		String cvsSplitBy = ",";
		int counter = 0;
		
		try (BufferedReader br = new BufferedReader(new FileReader(adress))) 
		{ 
			String check="";
			while ((check=br.readLine()) != null) 
			{
				counter++;
				if(counter>2)
				line += br.readLine()+"\n";
//				String[] data = line.split(cvsSplitBy);
//				
//				counter++;
//				if(counter>2) {
					
				}
			}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return line;
		} 
		
	}

