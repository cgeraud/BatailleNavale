package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class CrossAttack implements IAttack {
	
	
	public int crossAttack(IUnit bateau, Map targetMap, Coordinates coos) throws ShipIsDisabledException{
		int x = coos.getX();
		int y = coos.getY();
		System.out.println(bateau.attack(targetMap, coos));
		System.out.println(bateau.attack(targetMap, new Coordinates(x + 1, y)));
		System.out.println(bateau.attack(targetMap, new Coordinates(x -1, y)));
		System.out.println(bateau.attack(targetMap, new Coordinates(x, y + 1)));
		System.out.println(bateau.attack(targetMap, new Coordinates(x, y - 1)));
		return 2;
	}
	@Override
	public int attack(IUnit bateau, Map targetMap, Coordinates coos) throws ShipIsDisabledException {
		return crossAttack(bateau, targetMap, coos);
	}
}
