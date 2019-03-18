package fr.ensma.a3.ia.bataille_navale.GUI.playgrid.player;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GUI.drawables.DrawableShip;

public class PlayerPlayGridModel {
	private ArrayList<DrawableShip> ships = new ArrayList<>();
	private DrawableShip mockedShip = null;
	private boolean mockedShipValid = false;
	private static PlayerPlayGridModel instance = null;
	
	private PlayerPlayGridModel() {}
	
	public static PlayerPlayGridModel getPlayerPlayGridModel() {
		if(instance==null) {
			instance = new PlayerPlayGridModel();
		}
		return instance;
	}

	public ArrayList<DrawableShip> getShips() {
		return ships;
	}

	public void addShip(DrawableShip ship) {
		this.ships.add(ship);
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
}
