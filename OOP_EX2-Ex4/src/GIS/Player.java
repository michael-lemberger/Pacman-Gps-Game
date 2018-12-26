package GIS;

import java.awt.Image;

import Geom.Point3D;

public class Player extends Pacman {
	public double azimute;
	
	public Player(Point3D p, int id, double speed, double radius, Image img) {
		super(p, id, speed, radius, img);
	}

	public double getAzimute() {
		return azimute;
	}

	public void setAzimute(double azimute) {
		this.azimute = azimute;
	}

	
	
}
