package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotAttackException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotFlareException;
import fr.ensma.a3.ia.bataille_navale.map.IMapOpponent;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class FlareLauncher implements IAttack {

	@Override
	public int attack(IUnit bateau, IMapOpponent targetMap, Coordinates coos)
			throws ShipIsDisabledException, AttackOutOfMapException, ShipCannotAttackException, ShipCannotFlareException {
		if (targetMap.isOnMap(coos)) {
			return flareLauncher(bateau, targetMap, coos);
		}
		else {
			throw new AttackOutOfMapException(coos);
		}
	}
	
	private int flareLauncher(IUnit bateau, IMapOpponent targetMap, Coordinates coos) throws ShipCannotFlareException, ShipIsDisabledException {
		int res = (int)bateau.power() + 1;
		Coordinates target = new Coordinates(0,0);
		
		for(int j = res / 2 ; j >= -res / 2 ; j -= 1) {
			for(int i = -res / 2 ; i <= res / 2 ; i += 1){
				target.setX(coos.getX() + i);
				target.setY(coos.getY() + j);
				if(targetMap.isOnMap(target)) {
					bateau.flare(targetMap, target);
				}
			}
		}
		return 5;
	}
}
