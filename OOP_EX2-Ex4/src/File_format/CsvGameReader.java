package File_format;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class CsvGameReader {

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
		int lineCounter = 0;
		String [][] Matrix=new String[0][7];
		try (BufferedReader br = new BufferedReader(new FileReader(adress))) 
		{ 
			String check="";
			while ((check=br.readLine()) != null) 
			{
				lineCounter++;
				if(lineCounter>1) {
					check=check.substring(0, check.length()-2);
					line += check+",";
				}		
			}
		
			String[] data = line.split(cvsSplitBy);
			Matrix=new String [lineCounter][7];
			int StringsCounter=0;
			for (int i=0;i<Matrix.length-1;i++) {
				for (int j = 0; j < Matrix[i].length; j++) {
					if(StringsCounter == data.length)
						break;
					Matrix[i][j]=data[StringsCounter++];
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



