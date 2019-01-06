package fr.ensma.a3.ia.bataille_navale.game_elements;

import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class Cruiser extends AbstractShip {
	
	private static final int length = 4;
	
	Cruiser(Direction dir, Coordinates ref, Map mymap) {
		super(length, dir, ref, mymap);
	}

}
