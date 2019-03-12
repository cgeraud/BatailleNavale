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
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.IPreGameGUIObserver;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.PreGamePresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.PreGameView;
import fr.ensma.a3.ia.bataille_navale.kernel.GameKernel;
import fr.ensma.a3.ia.bataille_navale.kernel.IGameKernelObserver;
import fr.ensma.a3.ia.bataille_navale.kernel.kernel_states.IllegalKernelTransitionException;

public class GameGUIPresenter implements I_GUIPres, IGameKernelObserver, I_GUIAutomaton, IPreGameGUIObserver{
	
	private GameGUIModel gameModel = null;
	private IGameGUIView gameView = null;
	private I_GUIPres activePres = null;
	
	private I_GUIState playerSelState = new PlayerSelectionState(this);
	private I_GUIState shipPlaceState = new ShipPlacementState(this);
	private I_GUIState inGameState = new InGameState(this);
	private I_GUIState currState = this.playerSelState;
	
	public GameGUIPresenter() {
		// Dummy model (placeholder)
		this.gameModel = new GameGUIModel();
		this.currState = this.getPlayerSelectionState();
	}
	
	public void setView(I_GUIView view) {
		this.gameView = (IGameGUIView) view;
		this.switchToPlayerSelectionScreen();
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
	public void notifyPlayer1Turn() {}

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
	
	@Override
	public void notifyGameStarted() {
		try {
			this.currState.startGame();
		} catch (IllegalGUITransitionException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void notifyPreGameGUIDone() {
		try {
			GameKernel.getGameKernel().startGame();
		} catch (IllegalKernelTransitionException e1) {
			e1.printStackTrace();
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

	@Override
	public void switchToPlayerSelectionScreen() {
		this.activePres = new InitGamePresenter();
		this.activePres.setView(new InitGameView(this.activePres));
		this.updateView();
	}

	@Override
	public void switchToShipPlacementScreen() {
		PreGamePresenter pres = new PreGamePresenter();
		this.activePres = pres;
		this.activePres.setView(new PreGameView(pres));
		pres.addObserver(this);
		this.updateView();
	}

	@Override
	public void switchToInGameScreen() {
		// TODO switch to in-game screen
		this.updateView();
	}
}
