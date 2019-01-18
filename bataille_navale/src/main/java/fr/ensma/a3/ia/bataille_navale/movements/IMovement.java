package fr.ensma.a3.ia.bataille_navale.movements;

import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.map.Map;

public interface IMovement {
	public void move(IUnit ship, int value, Map map);
}
