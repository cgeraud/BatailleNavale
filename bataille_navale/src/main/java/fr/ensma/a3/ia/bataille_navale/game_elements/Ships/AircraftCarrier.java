package fr.ensma.a3.ia.bataille_navale.game_elements.Ships;

import fr.ensma.a3.ia.bataille_navale.map.IMapPlayer;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;
import fr.ensma.a3.ia.bataille_navale.utils.Shape;

public class AircraftCarrier extends BattleShip {
	
	private static Shape shipShape = null;
	public static Shape getShape() {
		if (shipShape == null) {
			shipShape = new Shape();
			shipShape.addRelativeTile(new Coordinates(0,0));
			shipShape.addRelativeTile(new Coordinates(1,0));
			shipShape.addRelativeTile(new Coordinates(2,0));
			shipShape.addRelativeTile(new Coordinates(0,1));
			shipShape.addRelativeTile(new Coordinates(1,1));
			shipShape.addRelativeTile(new Coordinates(2,1));
			shipShape.addRelativeTile(new Coordinates(3,1));
			shipShape.addRelativeTile(new Coordinates(4,1));
		}
		return shipShape;
	}
	
	public AircraftCarrier(String id, IMapPlayer map, Direction dir, Coordinates ref, ShipType type) throws ShipAlreadyExistsException, ShipOutOfMapException, ShipsOverlappingException {
		super(id, map, getShape(), dir, ref, type);
	}
	

}
