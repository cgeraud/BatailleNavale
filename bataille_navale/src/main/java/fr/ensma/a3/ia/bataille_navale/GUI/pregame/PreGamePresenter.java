package fr.ensma.a3.ia.bataille_navale.GUI.pregame;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIView;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.shipbar.ShipBarPresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.shipbar.ShipBarView;

public class PreGamePresenter implements I_GUIPres{
	
	private I_PreGameView view = null;
	private PreGameModel model = null;
	
	private ShipBarPresenter shipBar = null;
	
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

}
