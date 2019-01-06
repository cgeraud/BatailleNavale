package fr.ensma.a3.ia.bataille_navale.utils;

public class Coordinates {
	
	private static int X;
	private static int Y;
	
	public Coordinates(int x, int y) {
		X = x;
		Y = y;
	}
	
	public void setX(int value) {
		X = value;
	}
	public void setY(int value) {
		Y = value;
	}
	
	public int getX() {
		return X;
	}
	public int getY() {
		return Y;
	}

}
