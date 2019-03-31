package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotAttackException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotFlareException;
import fr.ensma.a3.ia.bataille_navale.map.IMapOpponent;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class FlareLauncher implements IAttack {

	@Override
	public ArrayList<IShellResult> attack(IUnit bateau, IMapOpponent targetMap, Coordinates coos)
			throws ShipIsDisabledException, AttackOutOfMapException, ShipCannotAttackException, ShipCannotFlareException {
		if (targetMap.isOnMap(coos)) {
			return flareLauncher(bateau, targetMap, coos);
		}
		else {
			throw new AttackOutOfMapException(coos);
		}
	}
	
	private ArrayList<IShellResult> flareLauncher(IUnit bateau, IMapOpponent targetMap, Coordinates coos) throws ShipCannotFlareException, ShipIsDisabledException {
		int cooldown = 5;
		int dia = (int)bateau.power() + 1;
		ArrayList<IShellResult> res = new ArrayList<IShellResult>();
		Coordinates target = new Coordinates(0,0);
		for(int j = dia / 2 ; j >= -dia / 2 ; j -= 1) {
			for(int i = -dia / 2 ; i <= dia / 2 ; i += 1){
				target.setX(coos.getX() + i);
				target.setY(coos.getY() + j);
				if(targetMap.isOnMap(target)) {
					res.add(bateau.flare(targetMap, target));
				}
			}
		}
		res.get(0).setCoolDownPenality(cooldown + 1);
		return res;
	}
}
