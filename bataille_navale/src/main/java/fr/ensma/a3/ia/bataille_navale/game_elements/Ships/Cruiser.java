package fr.ensma.a3.ia.bataille_navale.game_elements.Ships;

import fr.ensma.a3.ia.bataille_navale.map.IMap;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;
import fr.ensma.a3.ia.bataille_navale.utils.Shape;

public class Cruiser extends BattleShip {
	
	private static Shape shipShape = null;
	private static Shape getShape() {
		if (shipShape == null) {
			shipShape = new Shape();
			shipShape.addRelativeTile(new Coordinates(0,0));
			shipShape.addRelativeTile(new Coordinates(1,0));
			shipShape.addRelativeTile(new Coordinates(2,0));
			shipShape.addRelativeTile(new Coordinates(3,0));
		}
		return shipShape;
	}
	
	public Cruiser(String id, IMap map, Direction dir, Coordinates ref) throws ShipAlreadyExistsException, ShipOutOfMapException, ShipsOverlappingException {
		super(id, map, getShape(), dir, ref);
	}

}
