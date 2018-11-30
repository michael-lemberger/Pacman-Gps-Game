package Algorithms;

import java.io.File;
import java.io.IOException;

import GIS.GisLayer;
import GIS.GisProject;

public class MultiCsv {
	public static void main(String[] args) throws Exception {
		listFile("C:\\Users\\liron\\Downloads\\Ex2");

	}

	public static void listFile(String pathname) throws Exception {
		File f = new File(pathname);
		File[] listfiles = f.listFiles();
		GisLayer gis;
		GisProject project= new GisProject();
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
				s=s.replace("\\", "\\\\");
				System.out.println("kelev: "+s);
				try {
					gis = new GisLayer(s);
					gis.writeFileKML("C:\\MyCoordination.kml");
					project.add(new GisLayer(s));
					System.out.println(project);
				} catch (Exception e) {

				}



			}

		}

	}
}
