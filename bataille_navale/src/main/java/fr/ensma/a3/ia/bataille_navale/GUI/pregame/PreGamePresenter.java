package fr.ensma.a3.ia.bataille_navale.GUI.pregame;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIView;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.shipbar.ShipBarPresenter;

public class PreGamePresenter implements I_GUIPres{
	
	private I_PreGameView view = null;
	private PreGameModel model = null;
	
	private ShipBarPresenter shipBar = null;
	
	private ArrayList<IPreGameGUIObserver> observers = new ArrayList<IPreGameGUIObserver>();
	
	public PreGamePresenter() {
		this.model = new PreGameModel();
		this.shipBar = new ShipBarPresenter();
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

}
