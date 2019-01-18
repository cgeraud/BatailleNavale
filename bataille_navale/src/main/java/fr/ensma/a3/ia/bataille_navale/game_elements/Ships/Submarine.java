package fr.ensma.a3.ia.bataille_navale.game_elements.Ships;

import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class Submarine extends AbstractShip {
	
	private static final int length = 3;
	
	public Submarine(String id, Map map, Direction dir, Coordinates ref) throws ShipAlreadyExistsException {
		super(id, map, length, dir, ref);
	}

}
