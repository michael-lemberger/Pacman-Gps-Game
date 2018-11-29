package Algorithms;

import java.io.File;
import java.io.IOException;

import GIS.GisLayer;

public class MultiCsv {
	public static void main(String[] args) throws Exception {
		File currentDir = new File("C:\\Users\\liron\\Downloads\\Ex2"); // current directory
//		displayDirectoryContents(currentDir);
		listFile("C:\\Users\\liron\\Downloads\\Ex2");
		
	}

	public static void displayDirectoryContents(File dir) throws Exception {
		try {
			
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					System.out.println("directory:" + file.getCanonicalPath());
					
					displayDirectoryContents(file);
				} else {
					System.out.println("     file:" + file.getCanonicalPath());
					String temp= ""+file.getCanonicalPath();
					
					String s=temp.replace("\\", "\\\\");
					System.out.println("kelev"+s);
					GisLayer g = new GisLayer(s);
					
					g.writeFileKML(temp);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void listFile(String pathname) throws Exception {
	    File f = new File(pathname);
	    File[] listfiles = f.listFiles();
	    for (int i = 0; i < listfiles.length; i++) {
	        if (listfiles[i].isDirectory()) {
	            File[] internalFile = listfiles[i].listFiles();
	            for (int j = 0; j < internalFile.length; j++) {
	                System.out.println(internalFile[j]);
	                if (internalFile[j].isDirectory()) {
	                    String name = internalFile[j].getAbsolutePath();
	                    listFile(name);
	                }

	            }
	        } else {
	            System.out.println(listfiles[i]);
	            String s =""+ listfiles[i];
	            String s1=s.replace("\\", "\\\\");
				System.out.println("kelev"+s1);
				GisLayer g = new GisLayer(s1);
				
				g.writeFileKML("C:\\Users\\d.kml");
	        }

	    }

	}
}
