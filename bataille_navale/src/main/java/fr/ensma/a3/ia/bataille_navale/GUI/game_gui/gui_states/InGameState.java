package fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states;

public class InGameState extends AbsGUIState {

	public InGameState(I_GUIAutomaton gui) {
		super(gui);
	}
	
	@Override
	public void gameInitialized() {
		this.getGUI().setCurrentState(this.getGUI().getShipPlacementState());
		LOGGER.info("Switching to ship placement screen");
	}

	@Override
	public void quit() {
		this.getGUI().setCurrentState(this.getGUI().getPlayerSelectionState());
		LOGGER.info("Switching to player selection screen");
	}
}