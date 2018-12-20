package File_format;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import GIS.Game;
import GIS.Pacman;
import Geom.Point3D;

/**
 * This class export game to a kml file.
 * @author liron
 *
 */
public class Path2KML {
	
	Game game;
	String date;
    String[]color= {"3f0407","3f043c","24043f","040f3f",
			"04323f","043f21","043f08","3f3904","3f2504",
			"3f0f04","ea1b16","2fea16","16e8ea","eae716"};
	/**
	 * This constructor gets game and output path and make a kml file from the game.
	 * @param game
	 * @param output
	 */
	public Path2KML(Game game,String output) {
		this.game=game;
		PathToKML(output);
	}
	/**
	 * gets the current time for the time stamp.
	 * @return string current date and time.
	 */
	public String getTime() {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss ").format(Calendar.getInstance().getTime());
		timeStamp=timeStamp.replace("_", "T").replace(" ", "Z");
		date=timeStamp;
		return timeStamp;
	}


	/**
	 * adds the amount of time taken to get to the next point to getTime.
	 * @param date
	 * @param time
	 * @return
	 */
	public String plusTime(String date,int time) {
		if(time<=0) {
			return date;
		}
		double s=0,m=0,h=0;
		if(time>=60) {
			if(time>=3600) {
				m=time/60;
				s=m*100;
				s%=100;
				h=m/60;
			}
			m=time/60;
			s=m*100;
			s%=100;
			h=0;
		}
		else {
			s=time;
		}
		
		String cr=date.substring(11,19);
		cr.replace("Z","");	
		String [] sr=cr.split(":");
		int addedTime[]=new int [3];
		addedTime [0]=Integer.parseInt(sr[0])+(int)(h);
		addedTime [1]=Integer.parseInt(sr[1])+(int)(m);
		addedTime [2]=Integer.parseInt(sr[2])+(int)(s);
		if(addedTime[2]>59) {
			int count=addedTime[2]/60;
			while(count>0) {
				addedTime [2]-=60;
				count--;
			}
			
			if(addedTime[1]==59) {
				addedTime[1]=0;
				if(addedTime[0]==23) {
					addedTime[0]=0;
				}
				else {
					addedTime[0]++;
				}
			}
			else {
				addedTime[1]++;
			}
			
		}
		
		if(addedTime[1]>59) {
			int count=addedTime[1]/60;
			while(count>0) {
				addedTime [1]-=60;
				count--;
			}
			if(addedTime[0]==23) {
				addedTime[0]=0;
			}
			else{
				addedTime[0]++;
			}
		}
	
		if(addedTime[0]>23) {
			int count=addedTime[0]/24;
			while(count>0) {
				addedTime [0]-=24;
				count--;
			}
		}
		String s1="";
		s1=String.format("%02d:%02d:%02d",addedTime[0],addedTime[1],addedTime[2]);
		return date.substring(0,10)+"T"+s1+"Z";
	}
	/**
	 * Convert Path to Kml file
	 * @param output
	 */
	public  void PathToKML( String output) {
		ArrayList<String> content = new ArrayList<String>();
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" + "<Document>";
		content.add(kmlstart);

		String kmlend = "</Document>\n"+"</kml>";
		try{
			
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			Iterator <Pacman> it1=game.pacmans.iterator();
			
			while(it1.hasNext()) {
				Pacman pac=it1.next();
				Iterator <Point3D> it2=pac.path.points.iterator();
				int counter=0;
				Point3D point=null;
				String time=getTime();
				while(it2.hasNext()) {
				Point3D p=it2.next();
				if(point!=null) 
				time=plusTime(time,(int)(((pac.path.calc_distnce(0,counter)*1000)-(pac.get_radius()*pac.path.points.size()-1))/pac.get_speed()));
				
				if(counter==0) {
				String kmlelement ="<Placemark>\n"
						+"<name>"+"Pacman"+"</name>\n" +
						"<TimeStamp>\n"+
		                "<when>"+time+"</when>\n"
		                +"</TimeStamp>"
						+"<description>\n"
						+"<speed>"+"speed"+pac.get_speed()+"/n"+"</speed>\n"
						+"<radius>"+"radius"+pac.get_radius()+"</radius>\n"
						+"</description>\n" 
						+"<Point>\n" +
						"<coordinates>"+p.x()+","+p.y()+","+p.z()+"</coordinates>\n" +
						"</Point>\n" +
						"</Placemark>\n";
				content.add(kmlelement);
				}
				else {
					String kmlelement ="<Placemark>\n"
							+"<name>"+"Fruit"+"</name>\n" +
							"<TimeStamp>\n"+
			                "<when>"+time+"</when>\n"
			                +"</TimeStamp>"
							+"<description>\n"
							+"</description>\n" 
							+"<Point>\n" +
							"<coordinates>"+p.x()+","+p.y()+","+p.z()+"</coordinates>\n" +
							"</Point>\n" +
							"</Placemark>\n";
					content.add(kmlelement);
				}
				point=p;
				counter++;
			}
			}
			Iterator <Pacman> it3=game.pacmans.iterator();
			int colorcheck=0;
			while(it3.hasNext()) {
				Pacman pac=it3.next();
				String s="";
				for(int i=0;i<pac.path.points.size();i++) {
					s+=""+pac.path.points.get(i)+"\n";
				}
				String kmlelement =""+"<Placemark>\n"+
			  "<name>\n"+"Path\n"+"</name>\n"+
				"<description>\n"+"Path\n"+"</description>\n"+
			  "<LineString>\n"+
				"<coordinates>\n"+
				s
				+"</coordinates>\n"+
				"</LineString>\n"+
				"</Placemark>\n";
				content.add(kmlelement);
				if(colorcheck!=color.length-1)
					colorcheck++;
				else
					colorcheck=0;
			}
			
			content.add(kmlend);
			String csv = content.toString().replaceAll(", <", "  <").replace("[", "").replace("]", "");
			bw.write(csv);
			bw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
