package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class CrossAttack implements IAttack {
	
	
	public int crossAttack(IUnit bateau, Map targetMap, Coordinates coos) throws ShipIsDisabledException{
		int radius = 1;
		Coordinates attkCoord = new Coordinates(coos.getX(),coos.getY());
		if(targetMap.isOnMap(coos)) {
			bateau.attack(targetMap, attkCoord);
		}
		/*
		bateau.attack(targetMap, new Coordinates(x + 1, y));
		bateau.attack(targetMap, new Coordinates(x -1, y));
		bateau.attack(targetMap, new Coordinates(x, y + 1));
		bateau.attack(targetMap, new Coordinates(x, y - 1));
		*/
		return 2;
	}
	
	@Override
	public int attack(IUnit bateau, Map targetMap, Coordinates coos) throws ShipIsDisabledException, AttackOutOfMapException {
		if(targetMap.isOnMap(coos)) {
			return crossAttack(bateau, targetMap, coos);
		}
		else {
			throw new AttackOutOfMapException(coos);
		}
	}
}
