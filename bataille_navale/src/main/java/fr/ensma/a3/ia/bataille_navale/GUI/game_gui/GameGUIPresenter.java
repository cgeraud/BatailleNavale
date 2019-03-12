package fr.ensma.a3.ia.bataille_navale.GUI.game_gui;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIView;
import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states.I_GUIAutomaton;
import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states.I_GUIState;
import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states.IllegalGUITransitionException;
import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states.InGameState;
import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states.PlayerSelectionState;
import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states.ShipPlacementState;
import fr.ensma.a3.ia.bataille_navale.GUI.initgame.InitGamePresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.initgame.InitGameView;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.PreGamePresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.PreGameView;
import fr.ensma.a3.ia.bataille_navale.kernel.GameKernel;
import fr.ensma.a3.ia.bataille_navale.kernel.IGameKernelObserver;

public class GameGUIPresenter implements I_GUIPres, IGameKernelObserver, I_GUIAutomaton{
	
	private GameKernel gameModel = null;
	private IGameGUIView gameView = null;
	private I_GUIPres activePres = null;
	
	private I_GUIState playerSelState = new PlayerSelectionState(this);
	private I_GUIState shipPlaceState = new ShipPlacementState(this);
	private I_GUIState inGameState = new InGameState(this);
	private I_GUIState currState = this.playerSelState;
	
	public GameGUIPresenter() {
		this.gameModel = GameKernel.getGameKernel();
		this.currState = this.getPlayerSelectionState();
	}
	
	public void setView(I_GUIView view) {
		this.gameView = (IGameGUIView) view;
		this.updateView();
	}
	
	private void updateView() {
		if(this.gameView != null) {
			this.gameView.setView(this.activePres.getView());
		}
	}
	
	@Override
	public I_GUIView getView() {
		return this.gameView;
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
	
	@Override
	public void notifyQuit() {
		try {
			this.currState.quit();
		} catch (IllegalGUITransitionException e) {
			e.printStackTrace();
		}
	}
	
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
		this.activePres = new InitGamePresenter();
		this.activePres.setView(new InitGameView(this.activePres));
		this.updateView();
		return this.playerSelState;
	}

	@Override
	public I_GUIState getShipPlacementState() {
		this.activePres = new PreGamePresenter();
		this.activePres.setView(new PreGameView(this.activePres));
		this.updateView();
		return this.shipPlaceState;
	}

	@Override
	public I_GUIState getInGameState() {
		// TODO switch to in-game screen
		this.updateView();
		return this.inGameState;
	}
}
