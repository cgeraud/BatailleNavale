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
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.EShipTypes;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.IPreGameGUIObserver;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.PreGamePresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.PreGameView;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.AircraftCarrier;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.Cruiser;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.Destroyer;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipAlreadyExistsException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipOutOfMapException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipsOverlappingException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.Submarine;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.TorpedoBoat;
import fr.ensma.a3.ia.bataille_navale.kernel.GameKernel;
import fr.ensma.a3.ia.bataille_navale.kernel.IGameKernelObserver;
import fr.ensma.a3.ia.bataille_navale.kernel.kernel_states.IllegalKernelTransitionException;
import fr.ensma.a3.ia.bataille_navale.map.ShipDoesNotExistException;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;
import fr.ensma.a3.ia.bataille_navale.utils.Shape;

public class GameGUIPresenter implements I_GUIPres, IGameKernelObserver, I_GUIAutomaton, IPreGameGUIObserver{
	
	private GameGUIModel gameModel = null;
	private IGameGUIView gameView = null;
	private I_GUIPres activePres = null;
	
	private Logger LOGGER = Logger.getLogger(GameGUIPresenter.class.getName());
	
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
	public void tryPlacingShipAt(EShipTypes type, Direction dir, int x, int y) {
		// GUI-wise, the controlled player is always player 1
		String id = "";
		ShipType shipType = ShipType.SailBoat;
		Coordinates ref = new Coordinates(x, y);
		Shape shape = null;
		
		switch(type) {
		case CVN:
			id = "CVN_1";
			break;
		case Crui:
			id = "Cruiser_1";
			break;
		case Dest:
			id = "Destroyer_1";
			break;
		case Sub:
			id = "Submarine_1";
			break;
		case Torp:
			id = "TorpedoBoat_1";
			break;
		}
		
		shipType = getKernelShipEnum(type);
		shape = GameKernel.getGameKernel().getShipShape(shipType);
		
		try {
			GameKernel.getGameKernel().getPlayer1().addNewShip(id, shipType, dir, ref);
			LOGGER.info(id + " placed successfully.");
			((PreGamePresenter)this.activePres).shipSuccessfullyPlaced(id, shape, x, y);
		} catch (ShipAlreadyExistsException | ShipDoesNotExistException e) {
			e.printStackTrace();
		} catch (ShipOutOfMapException | ShipsOverlappingException e) {
			LOGGER.info("Try placing your ship elsewhere.");
		};
	}
	
	public static ShipType getKernelShipEnum(EShipTypes type) {
		ShipType shipType = null;
		switch(type) {
		case CVN:
			shipType = ShipType.AircraftCarrier;
			break;
		case Crui:
			shipType = ShipType.Cruiser;
			break;
		case Dest:
			shipType = ShipType.Destroyer;
			break;
		case Sub:
			shipType = ShipType.Submarine;
			break;
		case Torp:
			shipType = ShipType.TorpedoBoat;
			break;
		}
		return shipType;
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
