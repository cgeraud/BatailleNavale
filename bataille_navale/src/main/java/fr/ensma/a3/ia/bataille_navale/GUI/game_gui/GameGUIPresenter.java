package fr.ensma.a3.ia.bataille_navale.GUI.game_gui;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states.I_GUIAutomaton;
import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states.I_GUIState;
import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states.IllegalGUITransitionException;
import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states.InGameState;
import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states.PlayerSelectionState;
import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states.ShipPlacementState;
import fr.ensma.a3.ia.bataille_navale.kernel.GameKernel;
import fr.ensma.a3.ia.bataille_navale.kernel.IGameKernelObserver;

public class GameGUIPresenter implements IGameKernelObserver, I_GUIAutomaton{
	
	private GameKernel gameModel = null;
	private IGameGUIView gameView = null;
	private I_GUIPres activePres = null;
	
	private I_GUIState playerSelState = new PlayerSelectionState(this);
	private I_GUIState shipPlaceState = new ShipPlacementState(this);
	private I_GUIState inGameState = new InGameState(this);
	private I_GUIState currState = this.playerSelState;
	
	public GameGUIPresenter() {
		this.gameModel = GameKernel.getGameKernel();
		
		// TODO initialization
		this.activePres = null;
		this.activePres.setView(null);
	}
	
	public void setView(IGameGUIView view) {
		this.gameView = view;
		this.gameView.setView(this.activePres.getView());
	}
	
	/*
	 * Observer methods
	 * States synced to kernel state.
	 */

	@Override
	public void notifyPreGame() {
		try {
			this.currState.gameInitialized();
		} catch (IllegalGUITransitionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notifyPlayer1Turn() {
		if(currState != inGameState) {
			// If game has not stated yet (aka first call)
			try {
				this.currState.startGame();
			} catch (IllegalGUITransitionException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void notifyPlayer2Turn() {}

	@Override
	public void notifyPlayer1Won() {}

	@Override
	public void notifyPlayer2Won() {}
	
	/*
	 * Automaton methods
	 */

	@Override
	public void setCurrentState(I_GUIState state) {
		this.currState = state;
	}

	@Override
	public I_GUIState getCurrentState() {
		return this.currState;
	}

	@Override
	public I_GUIState getPlayerSelectionState() {
		return this.playerSelState;
	}

	@Override
	public I_GUIState getShipPlacementState() {
		return this.shipPlaceState;
	}

	@Override
	public I_GUIState getInGameState() {
		return this.inGameState;
	}

}
