package fr.ensma.a3.ia.bataille_navale.GUI.pregame.shipbar;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIView;

public class ShipBarPresenter implements I_GUIPres {
	
	private ArrayList<IShipBarObserver> observers = new ArrayList<>();
	
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
	
	public boolean allShipsPlaced() {
		return this.model.allShipsPlaced();
	}
	
	public void shipPlaced() {
		this.model.checkButton();
		this.view.setButtonDisabled(this.model.getLastCheckedShip(), true);
	}

	public void buttonClicked(int indexOf) {
		this.model.setLastCheckedShip(indexOf);
		for(IShipBarObserver obs : observers) {
			switch(indexOf) {
			case 0:
				obs.notifyTorpClicked();
				break;
			case 1:
				obs.notifyDestClicked();
				break;
			case 2:
				obs.notifySubClicked();
				break;
			case 3:
				obs.notifyCruiClicked();
				break;
			case 4:
				obs.notifyCVNClicked();
				break;
			}
		}
	}
	
	public void addObserver(IShipBarObserver obs) {
		this.observers.add(obs);
	}
}
