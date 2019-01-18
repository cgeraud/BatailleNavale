package fr.ensma.a3.ia.bataille_navale.game_elements.Ships;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public abstract class BattleShip extends AbstractShip{

	public BattleShip(String id, Map map, int len, Direction dir, Coordinates ref) throws ShipAlreadyExistsException {
		super(id, map, len, dir, ref);
	}

	@Override
	public AttackResult attack(Map map, Coordinates target) throws ShipIsDisabledException {
		
		float damage = power();
		
		if(Math.abs(damage)<1e-10) {
			throw new ShipIsDisabledException();
		}
		return map.fireAt(target, damage);
	}
}
