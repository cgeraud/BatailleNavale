package fr.ensma.a3.ia.bataille_navale.game_elements;

import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class AircraftCarrier extends AbstractShip {
	
	private static final int length = 5;
	
	public AircraftCarrier(Direction dir, Coordinates ref) {
		super(length, dir, ref);
	}

}
