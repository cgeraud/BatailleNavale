package fr.ensma.a3.ia.bataille_navale.GUI.pregame.grid;

import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public interface I_PreGameGridObserver {
	public void notifyPlaceShip(ShipType type, Coordinates origin, Direction dir);
}
