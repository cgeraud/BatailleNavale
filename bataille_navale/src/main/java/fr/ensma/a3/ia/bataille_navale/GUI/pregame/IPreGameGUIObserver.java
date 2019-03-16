package fr.ensma.a3.ia.bataille_navale.GUI.pregame;

import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public interface IPreGameGUIObserver {
	void notifyPreGameGUIDone();
	void tryPlacingShipAt(EShipTypes type, Direction dir, int x, int y);
}
