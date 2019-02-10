package fr.ensma.a3.ia.bataille_navale.kernel.kernel_states;

import fr.ensma.a3.ia.bataille_navale.GameMaster.IPlayer;
import fr.ensma.a3.ia.bataille_navale.kernel.IGameKernelObserver;

public interface IKernelAutomaton extends IGameKernelObserver {
	void setCurrentState(IKernelState state);
	IKernelState getCurrentState();
	IKernelState getPreGameState();
	IKernelState getPlayer1TurnState();
	IKernelState getPlayer2TurnState();
	IKernelState getPlayer1WonState();
	IKernelState getPlayer2WonState();
	
	void setCurrentPlayer(IPlayer player);
	void setCurrentOpponent(IPlayer player);
	IPlayer getPlayer1();
	IPlayer getPlayer2();
}
