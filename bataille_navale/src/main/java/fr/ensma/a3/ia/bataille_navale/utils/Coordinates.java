package fr.ensma.a3.ia.bataille_navale.utils;

public class Coordinates {
	
	private int X;
	private int Y;
	
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + X;
		result = prime * result + Y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (X != other.X)
			return false;
		if (Y != other.Y)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "X:" + String.valueOf(X) + " Y: " + String.valueOf(Y);
	}

	public Coordinates copy() {
		Coordinates newCo = new Coordinates(this.X, this.Y);
		return newCo;
	}
}
