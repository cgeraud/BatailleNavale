package fr.ensma.a3.ia.bataille_navale.game_elements;

import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class TorpedoBoat extends AbstractShip {
	
	private static final int length = 2;
	
	public TorpedoBoat(Map map, Direction dir, Coordinates ref) {
		super(map, length, dir, ref);
	}

}
