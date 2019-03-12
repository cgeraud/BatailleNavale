package fr.ensma.a3.ia.bataille_navale.GUI.pregame.pregame_states;

import java.util.logging.Logger;

import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states.IllegalGUITransitionException;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.PreGamePresenter;

public class AbsPreGameState implements I_PreGameState {
	
	protected Logger logger = Logger.getLogger(PreGamePresenter.class.getName());
	
	private I_PreGameAutomaton autom;
	
	public AbsPreGameState(I_PreGameAutomaton autom) {
		this.autom = autom;
	}
	
	public I_PreGameAutomaton getAutom() {
		return this.autom;
	}

	@Override
	public void shipSelected() throws IllegalGUITransitionException {
		throw new IllegalGUITransitionException();
		
	}

	@Override
	public void shipPlaced() throws IllegalGUITransitionException {
		throw new IllegalGUITransitionException();
		
	}

	@Override
	public void shipPlacementDone() throws IllegalGUITransitionException {
		throw new IllegalGUITransitionException();
	}

}
