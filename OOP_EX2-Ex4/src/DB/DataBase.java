package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DataBase {
	public  void BestScoreEver() {
		String best = "SELECT * FROM logs  ORDER BY logs.Point DESC LIMIT "+ 10;
		getData(best);
	}
	public  void MyBestScore() {
		String best ="SELECT * FROM logs WHERE FirstID="+
				308214105+ " ORDER BY logs.Point DESC LIMIT "+ 5;
		getData(best);
	}
	private  void getData(String allCustomersQuery )
	{
		String jdbcUrl="jdbc:mysql://ariel-oop.xyz:3306/oop"; //?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
		String jdbcUser="student";
		String jdbcPassword="student";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = 
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
			
			
			Statement statement = connection.createStatement();
							
			//select data
			
//			String allCustomersQuery = "SELECT * FROM logs  ORDER BY logs.Point DESC LIMIT "+ 10;
			ResultSet resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("  FirstID\t\tSecondID\t\tThirdID\t\tLogTime\t\t\t\t\t\tPoint\t\tSomeDouble");
			String score="";
			while(resultSet.next())
				
			{
				 score = ""+score+""+  resultSet.getInt("FirstID")+"   " +
						resultSet.getInt("SecondID")+"    " +
						resultSet.getInt("ThirdID")+"   " +
						resultSet.getTimestamp("LogTime") +"   " +
						resultSet.getDouble("Point") +"	  " +"\n";

			}
			score = "  FirstID    SecondID    ThirdID    LogTime    Point   \n"+score;
			resultSet.close();		
			statement.close();		
			connection.close();	
			JOptionPane.showMessageDialog(null, score);
		}
		
		catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("Vendor Error: " + sqle.getErrorCode());
		}
		
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}



}
