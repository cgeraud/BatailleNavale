package fr.ensma.a3.ia.bataille_navale.game_elements.Ships;

import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class ShipFactory {
	public static IUnit CreateShip(String id, ShipType type, Map map, Direction dir, Coordinates ref) throws ShipAlreadyExistsException {
		IUnit toReturn = null;
		switch(type) {
		case Destroyer:
			toReturn = new Destroyer(id, map, dir, ref);
			break;
		case Cruiser:
			toReturn = new Destroyer(id, map, dir, ref);
			break;
		case AircraftCarrier:
			toReturn = new Destroyer(id, map, dir, ref);
			break;
		case Submarine:
			toReturn = new Destroyer(id, map, dir, ref);
			break;
		case TorpedoBoat:
			toReturn = new Destroyer(id, map, dir, ref);
			break;
		default:
			break;
		}
		return toReturn;	
	}
}