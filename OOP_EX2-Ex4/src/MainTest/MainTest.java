package MainTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainTest {
	public static void main (String[]args) {
		try {
			csvmaker("res\\game_1543693911932_b.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static  String[][] csvmaker (String adress) throws Exception 
	{
 		String line = "";
		String cvsSplitBy = ",";
		int lineCounter = 0;
		int colCounter=0;
		String [][] Matrix=null;
		try (BufferedReader br = new BufferedReader(new FileReader(adress))) 
		{ 
			String check="";
			while ((check=br.readLine()) != null) 
			{	
				if(lineCounter!=0) {
				int a=countChar(check, ',')+1;
				if(a>colCounter) {
					colCounter=a;
				}
				}
				lineCounter++;
				if(lineCounter>1) {
					check=check.substring(0, check.length()-2);
					line += check+",";
				}
			}
		
			String[] data = line.split(cvsSplitBy);
			Matrix=new String [lineCounter][colCounter];
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
 	
 	public static int countChar(String str, char c)
 	{
 	    int count = 0;

 	    for(int i=0; i < str.length(); i++)
 	    {    if(str.charAt(i) == c)
 	            count++;
 	    }

 	    return count;
 	}
}
