package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotAttackException;
import fr.ensma.a3.ia.bataille_navale.map.IMapOpponent;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class CrossAttack implements IAttack {
	
	
	public int crossAttack(IUnit bateau, IMapOpponent targetMap, Coordinates coos) throws ShipIsDisabledException, ShipCannotAttackException{
		int cooldown = 2;
		int radius = 1;
		Coordinates attkCoord = new Coordinates(0,0);
		IShellResult res = null;
		
		for(int dx = -radius; dx <= radius; dx++) {
			for(int dy = -radius+Math.abs(dx); dy <= radius-Math.abs(dx); dy++) {
				attkCoord.setX(coos.getX()+dx);
				attkCoord.setY(coos.getY()+dy);
				if(targetMap.isOnMap(attkCoord)) {
					res = bateau.attack(targetMap, attkCoord);
					cooldown += res.getCoolDownPenalty();
				}
			}
		}
		
		return cooldown;
	}
	
	@Override
	public int attack(IUnit bateau, IMapOpponent targetMap, Coordinates coos) throws ShipIsDisabledException, AttackOutOfMapException, ShipCannotAttackException {
		if(targetMap.isOnMap(coos)) {
			return crossAttack(bateau, targetMap, coos);
		}
		else {
			throw new AttackOutOfMapException(coos);
		}
	}
}
