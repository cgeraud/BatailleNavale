package fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell;

import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class CellModel {
	private E_CellContent content = E_CellContent.Empty;
	private E_ShellEffect splashType = E_ShellEffect.Splash;
	private ShipType shipType = ShipType.TorpedoBoat;
	private int shipTile = 0;
	private Direction shipDirection = Direction.Horizontal;
	
	public E_CellContent getContent() {
		return content;
	}
	public void setContent(E_CellContent content) {
		this.content = content;
	}
	
	public ShipType getShipType() {
		return shipType;
	}
	public void setShipType(ShipType shipType) {
		this.shipType = shipType;
	}
	
	public int getShipTile() {
		return shipTile;
	}
	public void setShipTile(int shipTile) {
		this.shipTile = shipTile;
	}
	
	public Direction getShipDirection() {
		return shipDirection;
	}
	public void setShipDirection(Direction shipDirection) {
		this.shipDirection = shipDirection;
	}
	
	public E_ShellEffect getSplashType() {
		return splashType;
	}
	public void setSplashType(E_ShellEffect splashType) {
		this.splashType = splashType;
	}
}
