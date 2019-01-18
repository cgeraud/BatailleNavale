package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class CrossAttack implements IAttack {
	
	
	public int crossAttack(IUnit bateau, Map targetMap, Coordinates coos) throws ShipIsDisabledException{
		int radius = 1;
		Coordinates attkCoord = new Coordinates(0,0);
		
		for(int dx = -radius; dx <= radius; dx++) {
			for(int dy = -radius+Math.abs(dx); dy <= radius-Math.abs(dx); dy++) {
				attkCoord.setX(coos.getX()+dx);
				attkCoord.setY(coos.getY()+dy);
				if(targetMap.isOnMap(attkCoord)) {
					bateau.attack(targetMap, attkCoord);
				}
			}
		}
		
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
