package fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states;

public interface I_GUIState {
	void gameInitialized() throws IllegalGUITransitionException;
	void startGame() throws IllegalGUITransitionException;
	void quit() throws IllegalGUITransitionException;
	void gameFinished() throws IllegalGUITransitionException;
}
