package fr.ensma.a3.ia.bataille_navale.movements;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.game_elements.ITile;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.map.Map;

public class Translation implements IMovement{
	
	@Override
	public void move(IUnit ship, int value, Map map) {
		ArrayList<ITile> tiles = ship.getTiles();
	}

}
