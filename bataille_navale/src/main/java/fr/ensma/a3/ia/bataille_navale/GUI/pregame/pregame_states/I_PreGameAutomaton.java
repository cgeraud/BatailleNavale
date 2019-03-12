package fr.ensma.a3.ia.bataille_navale.GUI.pregame.pregame_states;

public interface I_PreGameAutomaton {
	
	I_PreGameState getCurState();
	void setCurState(I_PreGameState state);
	I_PreGameState getIdleState();
	I_PreGameState getShipSelectedState();
	I_PreGameState getReadyState();
	
}
