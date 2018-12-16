package File_format;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import GIS.GisElement;
import GIS.GisMetaData;
import Geom.Point3D;


public class CsvParser {
	
	/**
	 * makes an array of string to convert to Gis-class 
	 * @param adress String from where to convert
	 * @return  Matrix of Strings
	 * @throws Exception if there is a problem
	 */

	public  String[][] csvmaker (String adress) throws Exception 
	{
		
		String line = "";
		String cvsSplitBy = ",";
		int counterLine = 0;
		String [][] Matrix=new String[0][11];
		try (BufferedReader br = new BufferedReader(new FileReader(adress))) 
		{ 
			String check="";
			while ((check=br.readLine()) != null) 
			{
				counterLine++;
				if(counterLine>1)
				line += check+",";
				}
			
			
			String[] data = line.split(cvsSplitBy);
			Matrix=new String [counterLine][11];
			int counter=0;
			for (int i=0;i<Matrix.length-1;i++) {
				for (int j = 0; j < Matrix[i].length; j++) {
					if(counter>data.length)
						break;
					Matrix[i][j]=data[counter++];
				}
			}
			
			
			}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return Matrix;
		} 
		
	}

