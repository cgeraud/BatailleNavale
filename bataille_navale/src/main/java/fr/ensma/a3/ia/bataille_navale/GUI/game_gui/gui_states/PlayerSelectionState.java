package fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states;

public class PlayerSelectionState extends AbsGUIState {
	
	public PlayerSelectionState(I_GUIAutomaton gui) {
		super(gui);
	}

	@Override
	public void gameInitialized() {
		this.getGUI().setCurrentState(this.getGUI().getShipPlacementState());
		LOGGER.info("Switching to ship placement screen");
	}
	
}
