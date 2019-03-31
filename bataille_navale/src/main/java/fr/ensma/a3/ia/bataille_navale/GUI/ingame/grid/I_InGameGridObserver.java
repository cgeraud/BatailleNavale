package fr.ensma.a3.ia.bataille_navale.GUI.ingame.grid;

import fr.ensma.a3.ia.bataille_navale.GUI.drawables.DrawableShip;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public interface I_InGameGridObserver {
	public void notifyPlayerCellClicked(Coordinates coord, DrawableShip ship);

	public void notifyOponentCellClicked(Coordinates findTile);
	

}
