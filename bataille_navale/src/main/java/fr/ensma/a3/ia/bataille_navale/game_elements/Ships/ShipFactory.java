package fr.ensma.a3.ia.bataille_navale.game_elements.Ships;

import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.map.IMapPlayer;
import fr.ensma.a3.ia.bataille_navale.map.ShipDoesNotExistException;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class ShipFactory {
	public static IUnit CreateShip(String id, ShipType type, IMapPlayer map, Direction dir, Coordinates ref) throws ShipAlreadyExistsException, ShipOutOfMapException, ShipsOverlappingException, ShipDoesNotExistException {
		IUnit toReturn = null;
		switch(type) {
		case Destroyer:
			toReturn = new Destroyer(id, map, dir, ref);
			break;
		case Cruiser:
			toReturn = new Cruiser(id, map, dir, ref);
			break;
		case AircraftCarrier:
			toReturn = new AircraftCarrier(id, map, dir, ref);
			break;
		case Submarine:
			toReturn = new Submarine(id, map, dir, ref);
			break;
		case TorpedoBoat:
			toReturn = new TorpedoBoat(id, map, dir, ref);
			break;
		default:
			throw new ShipDoesNotExistException(type.toString());
		}
		return toReturn;	
	}
}
