package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public interface IAttack {
	public int attack(Map targetMap, Coordinates coos, float power);
}
