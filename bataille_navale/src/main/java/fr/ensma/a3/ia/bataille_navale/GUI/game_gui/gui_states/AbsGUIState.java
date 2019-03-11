package fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states;

import java.util.logging.Logger;

import fr.ensma.a3.ia.bataille_navale.kernel.GameKernel;
public abstract class AbsGUIState implements I_GUIState {
	
	private I_GUIAutomaton gui = null;
	protected final static Logger LOGGER = Logger.getLogger(GameKernel.class.getName());
	
	public AbsGUIState(I_GUIAutomaton gui) {
		this.gui = gui;
	}
	
	public I_GUIAutomaton getGUI() {
		return this.gui;
	}

	@Override
	public void gameInitialized() throws IllegalGUITransitionException {
		throw new IllegalGUITransitionException();
	}

	@Override
	public void startGame() throws IllegalGUITransitionException {
		throw new IllegalGUITransitionException();
	}

	@Override
	public void quit() throws IllegalGUITransitionException {
		throw new IllegalGUITransitionException();
	}

}
