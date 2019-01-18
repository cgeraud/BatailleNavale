package fr.ensma.a3.ia.bataille_navale.game_elements.Ships;

public class ShipOutOfMapException extends Exception {

	private static final long serialVersionUID = 3913763032047461423L;
	
	public ShipOutOfMapException(){
		super("Ship is Out of Map");
	}

}
