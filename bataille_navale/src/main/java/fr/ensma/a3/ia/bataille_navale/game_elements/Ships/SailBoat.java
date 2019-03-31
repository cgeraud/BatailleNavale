package fr.ensma.a3.ia.bataille_navale.game_elements.Ships;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.EAttackEffect;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IShellResult;
import fr.ensma.a3.ia.bataille_navale.map.IMapPlayer;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;
import fr.ensma.a3.ia.bataille_navale.utils.Shape;

public class SailBoat extends AbstractShip {

	
	private static Shape shipShape = null;
	public static Shape getShape() {
		if (shipShape == null) {
			shipShape = new Shape();
			shipShape.addRelativeTile(new Coordinates(0,0));
		}
		return shipShape;
	}
	
	public SailBoat(String id, IMapPlayer map, Direction dir, Coordinates ref, ShipType type) throws ShipAlreadyExistsException, ShipOutOfMapException, ShipsOverlappingException {
		super(id, map, getShape(), dir, ref, type);
		map.makeShipInvisible(this);
	}
	
	@Override
	public IShellResult takeDamage(float damage, Coordinates tilecoord) {
		AttackResult res = this.getTiles().get(0).takeDamage(damage);
		if(!this.isAlive()) {
			res.setFireResult(EAttackEffect.ShipSunk);
			res.setSunkShipId(this.getId());
			res.setCoolDownPenality(3);
		}
		return res;
	}
}
