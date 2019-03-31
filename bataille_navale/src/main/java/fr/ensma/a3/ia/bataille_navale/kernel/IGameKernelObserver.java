package fr.ensma.a3.ia.bataille_navale.kernel;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IShellResult;

public interface IGameKernelObserver {
	void notifyPreGame();
	void notifyGameStarted();
	void notifyPlayer1Turn();
	void notifyPlayer2Turn();
	void notifyPlayer1Won();
	void notifyPlayer2Won();
	void notifyQuit();
	void notifyFireResults(ArrayList<IShellResult> res);
	void simpleActionSelected();
}
