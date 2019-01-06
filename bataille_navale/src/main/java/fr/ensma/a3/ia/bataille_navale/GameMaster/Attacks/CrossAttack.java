package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

import fr.ensma.a3.ia.bataille_navale.game_elements.IOffensif;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class CrossAttack implements IAttack {
	
	
	public int crossAttack(IOffensif bateau, Map targetMap, Coordinates coos) throws ShipIsDisabledException{
		int x = coos.getX();
		int y = coos.getY();
		bateau.attack(targetMap, coos);
		bateau.attack(targetMap, new Coordinates(x + 1, y));
		bateau.attack(targetMap, new Coordinates(x -1, y));
		bateau.attack(targetMap, new Coordinates(x, y + 1));
		bateau.attack(targetMap, new Coordinates(x, y - 1));
		return 2;
	}
	@Override
	public int attack(IOffensif bateau, Map targetMap, Coordinates coos) throws ShipIsDisabledException {
		return crossAttack(bateau, targetMap, coos);
	}
}
