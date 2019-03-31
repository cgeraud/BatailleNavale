package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotAttackException;
import fr.ensma.a3.ia.bataille_navale.map.IMapOpponent;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class CrossAttack implements IAttack {
	
	
	public ArrayList<IShellResult> crossAttack(IUnit bateau, IMapOpponent targetMap, Coordinates coos) throws ShipIsDisabledException, ShipCannotAttackException{
		int cooldown = 2;
		int radius = 1;
		Coordinates attkCoord = new Coordinates(0,0);
		ArrayList<IShellResult> res = new ArrayList<IShellResult>();
		
		for(int dx = -radius; dx <= radius; dx++) {
			for(int dy = -radius+Math.abs(dx); dy <= radius-Math.abs(dx); dy++) {
				attkCoord.setX(coos.getX()+dx);
				attkCoord.setY(coos.getY()+dy);
				if(targetMap.isOnMap(attkCoord)) {
					res.add(bateau.attack(targetMap, attkCoord));
				}
			}
		}
		for(IShellResult shell : res) {
			res.get(0).setCoolDownPenality(res.get(0).getCoolDownPenalty() + shell.getCoolDownPenalty());
		}
		res.get(0).setCoolDownPenality(res.get(0).getCoolDownPenalty() + cooldown + 1);
		return res;
	}
	
	@Override
	public ArrayList<IShellResult> attack(IUnit bateau, IMapOpponent targetMap, Coordinates coos) throws ShipIsDisabledException, AttackOutOfMapException, ShipCannotAttackException {
		if(targetMap.isOnMap(coos)) {
			return crossAttack(bateau, targetMap, coos);
		}
		else {
			throw new AttackOutOfMapException(coos);
		}
	}
}
