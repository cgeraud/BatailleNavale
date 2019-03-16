package fr.ensma.a3.ia.bataille_navale.GUI.pregame;

import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class PreGameModel {
	private EShipTypes curShip;
	private Direction shipDir = Direction.Horizontal;
	
	public PreGameModel() {}

	public EShipTypes getCurShip() {
		return curShip;
	}

	public void setCurShip(EShipTypes curShip) {
		this.curShip = curShip;
	}

	public Direction getShipDir() {
		return shipDir;
	}

	public void setShipDir(Direction shipDir) {
		this.shipDir = shipDir;
	}

}
