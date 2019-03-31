package fr.ensma.a3.ia.bataille_navale.GUI.playgrid.player;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GUI.drawables.DrawableShip;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.kernel.GameKernel;

public class PlayerPlayGridModel {
	private ArrayList<DrawableShip> ships = new ArrayList<>();
	private DrawableShip mockedShip = null;
	private DrawableShip currentlySelectedShip = null;
	private boolean mockedShipValid = false;
	private static PlayerPlayGridModel instance = null;
	
	private PlayerPlayGridModel() {
		updateModel();
	}
	
	public void updateModel() {
		this.ships =  GameKernel.getGameKernel().getShipList(GameKernel.getGameKernel().getPlayer1());
	}
	
	public static PlayerPlayGridModel getPlayerPlayGridModel() {
		if(instance==null) {
			instance = new PlayerPlayGridModel();
		}
		return instance;
	}

	public ArrayList<DrawableShip> getShips() {
		return ships;
	}
	
	public DrawableShip getMockedShip() {
		return mockedShip;
	}

	public void setMockedShip(DrawableShip mockedShip) {
		this.mockedShip = mockedShip;
	}

	public boolean isMockedShipValid() {
		return mockedShipValid;
	}

	public void setMockedShipValid(boolean valid) {
		this.mockedShipValid = valid;
	}

	public DrawableShip getCurrentlySelectedShip() {
		return currentlySelectedShip;
	}

	public void setCurrentlySelectedShip(DrawableShip currentlySelectedShip) {
		this.currentlySelectedShip = currentlySelectedShip;
	}
}
