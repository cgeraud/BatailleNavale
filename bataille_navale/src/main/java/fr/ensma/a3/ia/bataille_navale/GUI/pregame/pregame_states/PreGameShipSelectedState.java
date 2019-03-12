package fr.ensma.a3.ia.bataille_navale.GUI.pregame.pregame_states;

import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states.IllegalGUITransitionException;

public class PreGameShipSelectedState extends AbsPreGameState {

	public PreGameShipSelectedState(I_PreGameAutomaton autom) {
		super(autom);
	}
	@Override
	public void shipPlaced() throws IllegalGUITransitionException {
		this.getAutom().setCurState(this.getAutom().getIdleState());
		logger.info("Ship Placed, switching to idle state");
	}
	
	@Override
	public void shipPlacementDone() throws IllegalGUITransitionException {
		this.getAutom().setCurState(this.getAutom().getReadyState());	
		logger.info("All Ship Placed, switching to ready state");
	}
	
	@Override
	public void shipSelected() throws IllegalGUITransitionException {
		logger.info("Damned user changed his choice, staying in ship selected mode");
	}
}
