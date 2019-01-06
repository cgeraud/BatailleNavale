package fr.ensma.a3.ia.bataille_navale.game_elements;

import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class Submarine extends AbstractShip {
	
	private static final int length = 3;
	
	Submarine(Direction dir, Coordinates ref, Map mymap) {
		super(length, dir, ref, mymap);
	}

}
