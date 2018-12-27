package MainTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainTest {
	public static void main (String[]args) {
		try {
			csvmaker("res\\game_1543693911932_b.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static  ArrayList<String> csvmaker (String adress) throws Exception 
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
