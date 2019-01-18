package fr.ensma.a3.ia.bataille_navale.utils;

import java.util.ArrayList;

public class Shape {
	
	private ArrayList<Coordinates> relativeTiles = new ArrayList<Coordinates>();
	
	public Shape() {
	}
	
	public void addRelativeTile(Coordinates coos) {
		this.relativeTiles.add(coos);
	}
	
	public ArrayList<Coordinates> getRelativeTiles(){
		return this.relativeTiles;
	}
}
