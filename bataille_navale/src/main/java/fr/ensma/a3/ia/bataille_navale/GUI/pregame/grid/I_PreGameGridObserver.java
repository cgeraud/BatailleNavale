package fr.ensma.a3.ia.bataille_navale.GUI.pregame.grid;

import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public interface I_PreGameGridObserver {
	public void notifyTileSelected(Coordinates coord);
	public void notifyTileHovered(Coordinates coord);
}
