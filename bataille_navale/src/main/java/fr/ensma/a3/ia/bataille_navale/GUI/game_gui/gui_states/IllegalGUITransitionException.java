package fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states;

public class IllegalGUITransitionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 645456725378370567L;
	
	public IllegalGUITransitionException() {
		super("Higher-level GUI illegal transition");
	}

}
