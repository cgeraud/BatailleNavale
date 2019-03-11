package fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states;

public class ShipPlacementState extends AbsGUIState {

	public ShipPlacementState(I_GUIAutomaton gui) {
		super(gui);
	}
	
	@Override
	public void startGame() {
		this.getGUI().setCurrentState(this.getGUI().getInGameState());
		LOGGER.info("Switching to in-game screen");
	}

}
