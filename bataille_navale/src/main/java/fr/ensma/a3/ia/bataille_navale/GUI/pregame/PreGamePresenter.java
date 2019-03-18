package fr.ensma.a3.ia.bataille_navale.GUI.pregame;

import java.util.ArrayList;
import java.util.logging.Logger;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIView;
import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states.IllegalGUITransitionException;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.grid.I_PreGameGridObserver;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.grid.PreGamePlayGridPresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.pregame_states.I_PreGameAutomaton;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.pregame_states.I_PreGameState;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.pregame_states.PreGameIdleState;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.pregame_states.PreGameReadyState;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.pregame_states.PreGameShipSelectedState;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.shipbar.IShipBarObserver;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.shipbar.ShipBarPresenter;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.kernel.GameKernel;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class PreGamePresenter implements I_GUIPres, 
IShipBarObserver, I_PreGameAutomaton, I_PreGameGridObserver{
	
	private Logger LOGGER = Logger.getLogger(PreGamePresenter.class.getName());
	
	private I_PreGameState idleState = new PreGameIdleState(this);
	private I_PreGameState shipSelectedState = new PreGameShipSelectedState(this);
	private I_PreGameState readyState = new PreGameReadyState(this);
	private I_PreGameState curstate = idleState;
	
	private I_PreGameView view = null;
	private PreGameModel model = null;
	
	private ShipBarPresenter shipBar = null;
	private PreGamePlayGridPresenter grid = null;
	
	private ArrayList<IPreGameGUIObserver> observers = new ArrayList<IPreGameGUIObserver>();
	
	public PreGamePresenter() {
		this.model = new PreGameModel();
		this.shipBar = new ShipBarPresenter();
		this.grid = new PreGamePlayGridPresenter();
		this.grid.addObserver(this);
		this.shipBar.addObserver(this);
	}
	
	public I_GUIPres getShipBarPresenter() {
		return this.shipBar;
	}
	
	public I_GUIPres getGridPresenter() {
		return this.grid;
	}

	@Override
	public void setView(I_GUIView view) {
		this.view = (I_PreGameView) view;
		this.shipBar.setView(this.view.getBarView());
		this.grid.setView(this.view.getGridView());
	}

	@Override
	public I_GUIView getView() {
		return this.view;
	}
	
	// UI methods

	public void placementDone() {
		LOGGER.info("Player has finished placing his ships.");
		for(IPreGameGUIObserver obs : this.observers) {
			obs.notifyPreGameGUIDone();
		}
	}
	
	public void rotateShip() {
		LOGGER.info("Player has changed his ship's orientation.");
		if(this.model.getMockedShip().getDirection()==Direction.Horizontal) {
			this.model.getMockedShip().setDirection(Direction.Vertical);
		} else {
			this.model.getMockedShip().setDirection(Direction.Horizontal);
		}
		this.grid.updateGrid();
	}
	
	public void shipSuccessfullyPlaced(String name, Coordinates origin) {
		// On successful placement, draw final ship
		this.grid.addShip(name, this.model.getMockedShip().getType(), 
				this.model.getMockedShip().getDirection(), origin);
		// Remove mock
		this.grid.removeMockedShip();
		// Draw again
		this.grid.updateGrid();
		
		// Acknowledge ship placement on bar
		this.shipBar.shipPlaced();
		
		// Do transition
		if(this.shipBar.allShipsPlaced()) {
			try {
				this.curstate.shipPlacementDone();
				this.view.setOkButtonAccess(true);
			} catch (IllegalGUITransitionException e) {
				e.printStackTrace();
			}
		} else {
			try {
				this.curstate.shipPlaced();
			} catch (IllegalGUITransitionException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Observer management part
	 */
	
	public void addObserver(IPreGameGUIObserver obs) {
		this.observers.add(obs);
	}
	
	public void remObserver(IPreGameGUIObserver obs) {
		this.observers.remove(obs);
	}
	
	/*
	 * Observer methods
	 */
	
	// ShipBar

	@Override
	public void notifyTorpClicked() {
		try {
			this.curstate.shipSelected();
			this.model.getMockedShip().setType(ShipType.TorpedoBoat);
		} catch (IllegalGUITransitionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notifyDestClicked() {
		try {
			this.curstate.shipSelected();
			this.model.getMockedShip().setType(ShipType.Destroyer);
		} catch (IllegalGUITransitionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notifySubClicked() {
		try {
			this.curstate.shipSelected();
			this.model.getMockedShip().setType(ShipType.Submarine);
		} catch (IllegalGUITransitionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notifyCruiClicked() {
		try {
			this.curstate.shipSelected();
			this.model.getMockedShip().setType(ShipType.Cruiser);
		} catch (IllegalGUITransitionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notifyCVNClicked() {
		try {
			this.curstate.shipSelected();
			this.model.getMockedShip().setType(ShipType.AircraftCarrier);
		} catch (IllegalGUITransitionException e) {
			e.printStackTrace();
		}
	}
	
	// Grid
	
	@Override
	public void notifyTileSelected(Coordinates coord) {
		if(this.curstate==this.shipSelectedState) {
			LOGGER.info("Trying to place " + this.model.getMockedShip().getDirection() 
					+ this.model.getMockedShip().getType() + " at (" 
					+ coord.getX() + "," + coord.getY() + ").");
			
			for(IPreGameGUIObserver obs : this.observers) {
				obs.tryPlacingShipAt(this.model.getMockedShip().getType(), 
						this.model.getMockedShip().getDirection(), coord);
			}
		}
	}
	
	@Override
	public void notifyTileHovered(Coordinates coord) {
		if(this.curstate==this.shipSelectedState) {
			this.model.getMockedShip().setOrigin(coord);
			this.grid.placeMockedShip(this.model.getMockedShip(),
					GameKernel.getGameKernel().getPlayer1().canAddNewShip("MockedShip", 
							this.model.getMockedShip().getType(), this.model.getMockedShip().getDirection(), coord));
			this.grid.updateGrid();
		}
	}
	
	/*
	 	Automaton Management methods
	 */
	
	@Override
	public I_PreGameState getCurState() {
		return this.curstate;
	}

	@Override
	public void setCurState(I_PreGameState state) {
		this.curstate = state;
	}

	@Override
	public I_PreGameState getIdleState() {
		return this.idleState;
	}

	@Override
	public I_PreGameState getShipSelectedState() {
		return this.shipSelectedState;
	}

	@Override
	public I_PreGameState getReadyState() {
		return this.readyState;
	}
}
