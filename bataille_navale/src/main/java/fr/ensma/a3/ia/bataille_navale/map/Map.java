package fr.ensma.a3.ia.bataille_navale.map;

import fr.ensma.a3.ia.bataille_navale.game_elements.IAttaquable;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class Map {
	
	private static final int Height = 10;
	private static final int Width = 10;
	private IAttaquable[][] grid;
	
	public Map() {
		grid = new IAttaquable[Height][Width];
		
	}

	public void fireAt(Coordinates target, float damage) {
		// TODO Auto-generated method stub
		
	}
	
	public void addShipToMap(IAttaquable myship, Coordinates ref, Direction dir) {
		
	}

}
