package fr.ensma.a3.ia.bataille_navale.GUI.pregame.pregame_states;

import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states.IllegalGUITransitionException;

public interface I_PreGameState {
	
	void shipSelected() throws IllegalGUITransitionException;
	void shipPlaced() throws IllegalGUITransitionException;
	void shipPlacementDone() throws IllegalGUITransitionException;
}
