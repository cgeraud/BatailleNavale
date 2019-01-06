package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class CrossAttack implements IAttack {
	
	
	public int crossAttack(Map targetMap, Coordinates coos, float power) {
		int x = coos.getX();
		int y = coos.getY();
		targetMap.fireAt(coos, power);
		targetMap.fireAt(new Coordinates(x + 1, y), power);
		targetMap.fireAt(new Coordinates(x -1, y), power);
		targetMap.fireAt(new Coordinates(x, y + 1), power);
		targetMap.fireAt(new Coordinates(x, y - 1), power);
		return 2;
	}
	@Override
	public int attack(Map targetMap, Coordinates coos, float power) {
		return crossAttack(targetMap, coos, power);
	}
}
