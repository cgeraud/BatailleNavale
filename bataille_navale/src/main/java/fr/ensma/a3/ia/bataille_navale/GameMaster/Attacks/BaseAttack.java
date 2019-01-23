package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotAttackException;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class BaseAttack implements IAttack {

	public int baseAttack(IUnit bateau, Map targetMap, Coordinates coos) throws ShipIsDisabledException, ShipCannotAttackException {
		IShellResult res = bateau.attack(targetMap, coos);
		return res.getCoolDownPenalty();
	}
	
	@Override
	public int attack(IUnit bateau, Map targetMap, Coordinates coos) throws ShipIsDisabledException, AttackOutOfMapException, ShipCannotAttackException {
		if(targetMap.isOnMap(coos)) {
			return baseAttack(bateau, targetMap, coos);
		}
		else {
			throw new AttackOutOfMapException(coos);
		}
	}
}
