package File_format;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

 public class CsvGameReader {
 	/**
	 * makes an array of string to convert to game object. 
	 * @param adress path of csv file.
	 * @return  Matrix of Strings with the data of the csv file.
	 * @throws Exception if there is a problem.
	 * @author Michael Lemberger, Liron Arad, Maoz Grossman.
	 */
 	public  ArrayList<String> csvmaker (String adress) throws Exception 
	{
 		String line = "";
		String cvsSplitBy = "&";
		boolean flag=false;
		ArrayList<String> lines=null;
		try (BufferedReader br = new BufferedReader(new FileReader(adress))) 
		{ 
			String check="";
			while ((check=br.readLine()) != null) 
			{
				if(flag) {
					check=check.substring(0, check.length()-2);
					line += check+"&";
				}
				flag=true;
			}
		
			String[] data = line.split(cvsSplitBy);
			lines=new ArrayList<String>();
			for(int i=0;i<data.length;i++) {
				lines.add(data[i]);
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return lines;
	} 
}