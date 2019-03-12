package fr.ensma.a3.ia.bataille_navale.GUI.pregame;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIView;
import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.gui_states.IllegalGUITransitionException;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.pregame_states.I_PreGameAutomaton;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.pregame_states.I_PreGameState;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.pregame_states.PreGameIdleState;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.pregame_states.PreGameReadyState;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.pregame_states.PreGameShipSelectedState;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.shipbar.IShipBarObserver;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.shipbar.ShipBarPresenter;

public class PreGamePresenter implements I_GUIPres, IShipBarObserver, I_PreGameAutomaton{
	
	private I_PreGameState idleState = new PreGameIdleState(this);
	private I_PreGameState shipSelectedState = new PreGameShipSelectedState(this);
	private I_PreGameState readyState = new PreGameReadyState(this);
	private I_PreGameState curstate = idleState;
	
	private I_PreGameView view = null;
	private PreGameModel model = null;
	
	private ShipBarPresenter shipBar = null;
	
	private ArrayList<IPreGameGUIObserver> observers = new ArrayList<IPreGameGUIObserver>();
	
	public PreGamePresenter() {
		this.model = new PreGameModel();
		this.shipBar = new ShipBarPresenter();
		this.shipBar.addObserver(this);
	}
	
	public I_GUIPres getShipBarPresenter() {
		return this.shipBar;
	}

	@Override
	public void setView(I_GUIView view) {
		this.view = (I_PreGameView) view;
	}

	@Override
	public I_GUIView getView() {
		return this.view;
	}
	
	// UI methods

	public void placementDone() {
		for(IPreGameGUIObserver obs : this.observers) {
			obs.notifyPreGameGUIDone();
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

	@Override
	public void notifyTorpClicked() {
		try {
			this.curstate.shipSelected();
			this.model.setCurShip(EShipTypes.Torp);
		} catch (IllegalGUITransitionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notifyDestClicked() {
		try {
			this.curstate.shipSelected();
			this.model.setCurShip(EShipTypes.Dest);
		} catch (IllegalGUITransitionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notifySubClicked() {
		try {
			this.curstate.shipSelected();
			this.model.setCurShip(EShipTypes.Sub);
		} catch (IllegalGUITransitionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notifyCruiClicked() {
		try {
			this.curstate.shipSelected();
			this.model.setCurShip(EShipTypes.Crui);
		} catch (IllegalGUITransitionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notifyCVNClicked() {
		try {
			this.curstate.shipSelected();
			this.model.setCurShip(EShipTypes.CVN);
		} catch (IllegalGUITransitionException e) {
			e.printStackTrace();
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
