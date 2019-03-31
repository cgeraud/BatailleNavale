package fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states;

public class EndGameState extends AbsGUIState {

	public EndGameState(I_GUIAutomaton gui) {
		super(gui);
	}
	
	@Override
	public void quit() throws IllegalGUITransitionException {
		this.getGUI().switchToPlayerSelectionScreen();
		this.getGUI().setCurrentState(this.getGUI().getPlayerSelectionState());
	}
}
