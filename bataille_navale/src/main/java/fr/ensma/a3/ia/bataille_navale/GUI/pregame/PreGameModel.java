package fr.ensma.a3.ia.bataille_navale.GUI.pregame;

import fr.ensma.a3.ia.bataille_navale.GUI.drawables.DrawableShip;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class PreGameModel {
	private DrawableShip mockedShip = new DrawableShip();
	private boolean mockedShipValid = false;
	
	public PreGameModel() {
		this.mockedShip.setName("MockedShip");
		this.mockedShip.setDirection(Direction.Horizontal);
		this.mockedShip.setType(ShipType.TorpedoBoat);
		this.mockedShip.setOrigin(new Coordinates(0,0));
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
