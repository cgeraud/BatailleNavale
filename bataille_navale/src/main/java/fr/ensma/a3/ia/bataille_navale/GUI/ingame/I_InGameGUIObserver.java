package fr.ensma.a3.ia.bataille_navale.GUI.ingame;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GUI.ingame.actionBar.EPossibleActions;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell.E_ShellEffect;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IShellResult;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public interface I_InGameGUIObserver {
	
	public ArrayList<EPossibleActions> notifyPlayerClickedShip(String shipName);

	public void notifyPlayerSelectedAction(EPossibleActions action);

	public void notifyPlayerClickedOponentCell(Coordinates tilecoos);

	void notifyPlayerClickedPlayersCell(Coordinates start, Coordinates end);

	public void notifyAbandon();

}
