package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class BaseAttack implements IAttack {

	public int baseAttack(Map targetMap, Coordinates coos, float power) {
		targetMap.fireAt(coos, power);
		return 0;
	}
	
	@Override
	public int attack(Map targetMap, Coordinates coos, float power) {
		return baseAttack(targetMap, coos, power);
	}
}
