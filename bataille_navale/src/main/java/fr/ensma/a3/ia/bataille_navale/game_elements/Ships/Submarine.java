package fr.ensma.a3.ia.bataille_navale.game_elements.Ships;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.EFlareResult;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.map.IMapOpponent;
import fr.ensma.a3.ia.bataille_navale.map.IMapPlayer;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;
import fr.ensma.a3.ia.bataille_navale.utils.Shape;

public class Submarine extends BattleShip {
	
	private static Shape shipShape = null;
	public static Shape getShape() {
		if (shipShape == null) {
			shipShape = new Shape();
			shipShape.addRelativeTile(new Coordinates(0,0));
			shipShape.addRelativeTile(new Coordinates(1,0));
			shipShape.addRelativeTile(new Coordinates(2,0));
		}
		return shipShape;
	}
	
	
	public Submarine(String id, IMapPlayer map, Direction dir, Coordinates ref) throws ShipAlreadyExistsException, ShipOutOfMapException, ShipsOverlappingException {
		super(id, map, getShape(), dir, ref);
	}
	
	@Override
	public EFlareResult flare(IMapOpponent target, Coordinates coos) throws ShipCannotFlareException, ShipIsDisabledException {
		float damage = this.power();
		if(Math.abs(damage)<1.0) {
			throw new ShipIsDisabledException();
		}
		return target.revealMap(coos);
	}
}
