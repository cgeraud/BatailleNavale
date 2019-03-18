package fr.ensma.a3.ia.bataille_navale.GUI.playgrid.player;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GUI.drawables.DrawableShip;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.kernel.GameKernel;

public class PlayerPlayGridModel {
	private ArrayList<DrawableShip> ships = new ArrayList<>();
	private DrawableShip mockedShip = null;
	private boolean mockedShipValid = false;
	private static PlayerPlayGridModel instance = null;
	
	private PlayerPlayGridModel() {
		// TODO placeholder work
		// Adding initial sailboat
		IUnit unit = GameKernel.getGameKernel().getPlayer1().getShips().get(0);
		
		DrawableShip newShip = new DrawableShip();
		newShip.setName(unit.getId());
		newShip.setOrigin(unit.getUnitCoordinates().get(0));
		newShip.setDirection(unit.getDirection());
		newShip.setType(ShipType.SailBoat);
		
		this.ships.add(newShip);
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
