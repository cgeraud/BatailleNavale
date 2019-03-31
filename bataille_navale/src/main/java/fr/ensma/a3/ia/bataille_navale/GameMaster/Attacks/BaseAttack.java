package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotAttackException;
import fr.ensma.a3.ia.bataille_navale.map.IMapOpponent;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class BaseAttack implements IAttack {

	public ArrayList<IShellResult> baseAttack(IUnit bateau, IMapOpponent targetMap, Coordinates coos) throws ShipIsDisabledException, ShipCannotAttackException {
		ArrayList<IShellResult> res = new ArrayList<IShellResult>();
		res.add(bateau.attack(targetMap, coos));
		res.get(0).setCoolDownPenality(res.get(0).getCoolDownPenalty() + 1);
		return res;
	}
	
	@Override
	public ArrayList<IShellResult> attack(IUnit bateau, IMapOpponent targetMap, Coordinates coos) throws ShipIsDisabledException, AttackOutOfMapException, ShipCannotAttackException {
		if(targetMap.isOnMap(coos)) {
			return baseAttack(bateau, targetMap, coos);
		}
		else {
			throw new AttackOutOfMapException(coos);
		}
	}
}
