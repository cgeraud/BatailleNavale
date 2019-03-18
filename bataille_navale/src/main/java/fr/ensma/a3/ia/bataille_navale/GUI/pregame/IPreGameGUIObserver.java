package fr.ensma.a3.ia.bataille_navale.GUI.pregame;

import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public interface IPreGameGUIObserver {
	void notifyPreGameGUIDone();
	void tryPlacingShipAt(ShipType type, Direction dir, Coordinates origin);
}
