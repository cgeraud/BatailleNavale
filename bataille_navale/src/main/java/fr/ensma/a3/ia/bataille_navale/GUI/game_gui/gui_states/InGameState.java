package fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states;

public class InGameState extends AbsGUIState {

	public InGameState(I_GUIAutomaton gui) {
		super(gui);
	}
	
	@Override
	public void gameInitialized() {
		// TODO - replay
	}

	@Override
	public void quit() {
		// TODO - revert to player selection
	}
}
