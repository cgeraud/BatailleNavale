package fr.ensma.a3.ia.bataille_navale.game_elements.Ships;

import fr.ensma.a3.ia.bataille_navale.map.IMap;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;
import fr.ensma.a3.ia.bataille_navale.utils.Shape;

public class Destroyer extends BattleShip {
	
	private static Shape shipShape = null;
	private static Shape getShape() {
		if (shipShape == null) {
			shipShape = new Shape();
			shipShape.addRelativeTile(new Coordinates(0,0));
			shipShape.addRelativeTile(new Coordinates(1,0));
			shipShape.addRelativeTile(new Coordinates(2,0));
		}
		return shipShape;
	}
	
	public Destroyer(String id, IMap map, Direction dir, Coordinates ref) throws ShipAlreadyExistsException, ShipOutOfMapException {
		super(id, map, getShape(), dir, ref);
	}

}
