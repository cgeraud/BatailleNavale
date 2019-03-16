package fr.ensma.a3.ia.bataille_navale.GUI.pregame.grid;

public interface I_PreGameGridObserver {
	public void notifyTileSelected(int x, int y);
	public void notifyTileHovered(int x, int y);
}
