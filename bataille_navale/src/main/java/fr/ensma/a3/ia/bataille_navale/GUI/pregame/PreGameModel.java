package fr.ensma.a3.ia.bataille_navale.GUI.pregame;

public class PreGameModel {
	private EShipTypes curShip;
	
	public PreGameModel() {}

	public EShipTypes getCurShip() {
		return curShip;
	}

	public void setCurShip(EShipTypes curShip) {
		this.curShip = curShip;
	}

}
