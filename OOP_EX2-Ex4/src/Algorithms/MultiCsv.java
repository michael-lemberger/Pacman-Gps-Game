package Algorithms;

import java.io.File;
import java.io.IOException;

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
	private static GisProject project = new GisProject();

	/**
	 * this constructor get directory address and call the RecursiveCsvSerch function.
	 * @param adress directory address to search for csv files.
	 * @throws Exception
	 */
	public MultiCsv(String adress) throws Exception {
		File currentDir = new File(adress);
		RecursiveCsvSerch(currentDir);
	}

	/**
	 * this function get adrress directory and add all the csv files in this path to the GisProject field in recursive way: if it is directory step into, else - add the csv file to the GisProject .
	 * @param dir
	 * @throws Exception
	 */
	public static void RecursiveCsvSerch(File dir) throws Exception {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					RecursiveCsvSerch(file);
				} else {
					GisLayer layer = new GisLayer(file.getCanonicalPath());
					project.add(layer);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * this function return the GisProject that contain all the layers (csv files).
	 * @return 
	 */
	public GisProject getProject() {
		return project;
	}
}
