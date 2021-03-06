package Algorithms;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import GIS.GisLayer;
import GIS.GisProject;

/**
 * this class represent a MultiCsv object.
 * the class support the RecursiveCsvSerch function that get directory address and search for all the csv files.
 * the class as a GisProject field to save all the csv files (layers).
 * @author Michael Lemberger, Liron Arad, Maoz Grossman
 *
 */
public class MultiCsv {
	public static GisProject project = new GisProject();
	public static Set<File> fileset =new HashSet<File>();

	/**
	 * this constructor get directory address and call the RecursiveCsvSerch function.
	 * @param adress directory address to search for csv files.
	 * @throws Exception runtime
	 */
	public MultiCsv(String adress) throws Exception {
		File currentDir = new File(adress);
		RecursiveCsvSerch(currentDir);
		add2project();
	}

	/**
	 * this function get adrress directory and add all the csv files in this path to the GisProject field in recursive way: if it is directory step into, else - add the csv file to the GisProject .
	 * @param dir files
	 * @throws Exception e
	 */
	public static void RecursiveCsvSerch(File dir) throws Exception {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					RecursiveCsvSerch(file);
				} else {
					if(file.getCanonicalPath().endsWith(".csv")) 
					fileset.add(file);

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void add2project() throws IOException, Exception {
		for(File f: fileset) {
			project.add(new GisLayer(f.getPath()));
		}
	}
	
	/**
	 * this function return the GisProject that contain all the layers (csv files).
	 * @return project
	 */
	public GisProject getProject() {
		return project;
	}
}
