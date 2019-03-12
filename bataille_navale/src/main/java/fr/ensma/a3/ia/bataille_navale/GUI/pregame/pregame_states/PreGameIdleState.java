package fr.ensma.a3.ia.bataille_navale.GUI.pregame.pregame_states;

import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states.IllegalGUITransitionException;

public class PreGameIdleState extends AbsPreGameState {

	public PreGameIdleState(I_PreGameAutomaton autom) {
		super(autom);
	}
	
	@Override
	public void shipSelected() throws IllegalGUITransitionException {
		this.getAutom().setCurState(this.getAutom().getShipSelectedState());
		logger.info("Ship selected, switching to ship selected state");
	}
}
