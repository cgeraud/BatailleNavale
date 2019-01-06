package fr.ensma.a3.ia.bataille_navale.game_elements;

import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class TorpedoBoat extends AbstractShip {
	
	private static final int length = 2;
	
	TorpedoBoat(Direction dir, Coordinates ref, Map mymap) {
		super(length, dir, ref, mymap);
	}

}
