package fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIView;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class CellPresenter implements I_GUIPres{
	
	private I_CellView view = null;
	private CellModel model = null;
	
	private ArrayList<I_CellObserver> observers = new ArrayList<>();
	
	public CellPresenter() {
		this.model = new CellModel();
	}

	@Override
	public void setView(I_GUIView view) {
		this.view = (I_CellView) view;
		updateView();
	}

	@Override
	public I_GUIView getView() {
		return this.view;
	}

	public void onClick() {
		for(I_CellObserver obs : observers) {
			obs.cellClicked(this);
		}
	}
	
	public void onMouseEnter() {
		for(I_CellObserver obs : observers) {
			obs.cellEntered(this);
		}
		updateView();
	}

	public void setDisable(boolean value) {
		this.view.setDeactivated(value);
	}
	
	private void updateView() {
		switch(this.model.getContent()) {
		case Empty:
			this.view.setContent(" ");
			break;
		case Ship:
			this.view.setContent("#");
			break;
		case DamagedShip:
			this.view.setContent("U");
			break;
		case DestroyedShip:
			this.view.setContent("_");
			break;
		case FutureShipNotValid:
			this.view.setContent("X");
			break;
		case FutureShipValid:
			this.view.setContent("V");
			break;
		case Unknown:
			this.view.setContent(" ");
			break;
		case Shell:
			switch(this.model.getSplashType()) {
			case Damaged:
				this.view.setContent("v");
				break;
			case Destroyed:
				this.view.setContent("V");
				break;
			case Splash:
				this.view.setContent("O");
				break;
			}
			break;
		}
	}
	
	public void displayShipTile(ShipType type, int tileId, Direction dir) {
		this.model.setContent(E_CellContent.Ship);
		this.model.setShipType(type);
		this.model.setShipTile(tileId);
		this.model.setShipDirection(dir);
		updateView();
	}
	
	public void displayTileDamaged() {
		this.model.setContent(E_CellContent.DamagedShip);
		updateView();
	}
	
	public void displayTileDestroyed() {
		this.model.setContent(E_CellContent.DestroyedShip);
		updateView();
	}
	
	public void mockShipTile(ShipType type, int tileId, Direction dir, boolean valid) {
		if(valid) {
			this.model.setContent(E_CellContent.FutureShipValid);
		} else {
			this.model.setContent(E_CellContent.FutureShipNotValid);
		}
		this.model.setShipType(type);
		this.model.setShipTile(tileId);
		this.model.setShipDirection(dir);
		updateView();
	}
	
	public void displayEmpty() {
		this.model.setContent(E_CellContent.Empty);
		updateView();
	}
	
	public void displayUnknown() {
		this.model.setContent(E_CellContent.Unknown);
		updateView();
	}
	
	public void displayShell(E_ShellEffect shell_result) {
		this.model.setContent(E_CellContent.Shell);
		this.model.setSplashType(shell_result);
		updateView();
	}
	
	/*
	 * Observer part
	 */
	
	public void addObserver(I_CellObserver obs) {
		this.observers.add(obs);
	}
	
	public void removeObserver(I_CellObserver obs) {
		this.observers.remove(obs);
	}
}
