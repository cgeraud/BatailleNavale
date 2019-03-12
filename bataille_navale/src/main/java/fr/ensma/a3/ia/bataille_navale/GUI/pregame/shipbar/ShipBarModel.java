package fr.ensma.a3.ia.bataille_navale.GUI.pregame.shipbar;

public class ShipBarModel {
	private int checkedShips = 0;
	private int lastCheckedShip = -1;
	
	public void checkButton(int index) {
		this.checkedShips += 1;
		this.lastCheckedShip = index;
	}
	
	public void unCheckButton() {
		this.checkedShips -= 1;
	}
	
	public int getLastCheckedShip() {
		return this.lastCheckedShip;
	}
	
	public boolean allShipsPlaced() {
		return this.checkedShips == 5;
	}
}
