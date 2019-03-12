package fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states;

public interface I_GUIAutomaton {
	void setCurrentState(I_GUIState state);
	I_GUIState getCurrentState();
	I_GUIState getPlayerSelectionState();
	I_GUIState getShipPlacementState();
	I_GUIState getInGameState();
	
	void switchToPlayerSelectionScreen();
	void switchToShipPlacementScreen();
	void switchToInGameScreen();
}
