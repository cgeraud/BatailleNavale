package fr.ensma.a3.ia.bataille_navale.GUI.game_gui;

import java.util.logging.Logger;

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
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipAlreadyExistsException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipOutOfMapException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipsOverlappingException;
import fr.ensma.a3.ia.bataille_navale.kernel.GameKernel;
import fr.ensma.a3.ia.bataille_navale.kernel.IGameKernelObserver;
import fr.ensma.a3.ia.bataille_navale.kernel.kernel_states.IllegalKernelTransitionException;
import fr.ensma.a3.ia.bataille_navale.map.ShipDoesNotExistException;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class GameGUIPresenter implements I_GUIPres, IGameKernelObserver, I_GUIAutomaton, IPreGameGUIObserver{
	
	//private GameGUIModel gameModel = null;
	private IGameGUIView gameView = null;
	private I_GUIPres activePres = null;
	
	private Logger LOGGER = Logger.getLogger(GameGUIPresenter.class.getName());
	
	private I_GUIState playerSelState = new PlayerSelectionState(this);
	private I_GUIState shipPlaceState = new ShipPlacementState(this);
	private I_GUIState inGameState = new InGameState(this);
	private I_GUIState currState = this.playerSelState;
	
	public GameGUIPresenter() {
		//this.gameModel = new GameGUIModel();
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
	
	// Pre-game
	
	@Override
	public void notifyPreGameGUIDone() {
		try {
			GameKernel.getGameKernel().startGame();
		} catch (IllegalKernelTransitionException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void tryPlacingShipAt(ShipType type, Direction dir, Coordinates origin) {
		// GUI-wise, the controlled player is always player 1
		String id = "";

		switch(type) {
		case AircraftCarrier:
			id = "CVN_1";
			break;
		case Cruiser:
			id = "Cruiser_1";
			break;
		case Destroyer:
			id = "Destroyer_1";
			break;
		case Submarine:
			id = "Submarine_1";
			break;
		case TorpedoBoat:
			id = "TorpedoBoat_1";
			break;
		default:
			break;
		}
		
		try {
			GameKernel.getGameKernel().getPlayer1().addNewShip(id, type, dir, origin);
			LOGGER.info(id + " placed successfully.");
			((PreGamePresenter)this.activePres).shipSuccessfullyPlaced(id);
		} catch (ShipAlreadyExistsException | ShipDoesNotExistException e) {
			e.printStackTrace();
		} catch (ShipOutOfMapException | ShipsOverlappingException e) {
			LOGGER.info("Try placing your ship elsewhere.");
		};
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
