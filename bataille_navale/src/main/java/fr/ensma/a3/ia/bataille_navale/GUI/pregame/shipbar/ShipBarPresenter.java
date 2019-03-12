package fr.ensma.a3.ia.bataille_navale.GUI.pregame.shipbar;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIView;

public class ShipBarPresenter implements I_GUIPres {
	
	private I_ShipBarView view = null;
	private ShipBarModel model = null;
	
	public ShipBarPresenter() {
		this.model = new ShipBarModel();
	}

	@Override
	public void setView(I_GUIView view) {
		this.view = (I_ShipBarView) view;
	}

	@Override
	public I_GUIView getView() {
		return this.view;
	}

}
