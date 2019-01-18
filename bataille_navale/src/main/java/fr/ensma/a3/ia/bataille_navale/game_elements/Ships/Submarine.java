package fr.ensma.a3.ia.bataille_navale.game_elements.Ships;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;
import fr.ensma.a3.ia.bataille_navale.utils.Shape;

public class Submarine extends AbstractShip {
	
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
	
	
	public Submarine(String id, Map map, Direction dir, Coordinates ref) throws ShipAlreadyExistsException, ShipOutOfMapException {
		super(id, map, getShape(), dir, ref);
	}
	
	@Override
	public AttackResult flare(Map target, Coordinates coos) throws ShipCannotFlareException, ShipIsDisabledException {
			float damage = power();
			if(Math.abs(damage)<1.0) {
				throw new ShipIsDisabledException();
		}
		return target.fireAt(coos, 0);
	}
}
