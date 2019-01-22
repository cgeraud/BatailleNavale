package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotAttackException;
import fr.ensma.a3.ia.bataille_navale.map.IMap;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class BaseAttack implements IAttack {

	public int baseAttack(IUnit bateau, IMap targetMap, Coordinates coos) throws ShipIsDisabledException, ShipCannotAttackException {
		bateau.attack(targetMap, coos);
		return 0;
	}
	
	@Override
	public int attack(IUnit bateau, IMap targetMap, Coordinates coos) throws ShipIsDisabledException, AttackOutOfMapException, ShipCannotAttackException {
		if(targetMap.isOnMap(coos)) {
			return baseAttack(bateau, targetMap, coos);
		}
		else {
			throw new AttackOutOfMapException(coos);
		}
	}
}
