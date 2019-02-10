package fr.ensma.a3.ia.bataille_navale.kernel;

public interface IGameKernelObserver {
	void notifyPreGame();
	void notifyPlayer1Turn();
	void notifyPlayer2Turn();
	void notifyPlayer1Won();
	void notifyPlayer2Won();
}
