package fr.ensma.a3.ia.bataille_navale.game_elements;

public class ShipIsDisabledException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ShipIsDisabledException() {
		super("Error: ship disabled");
	}
}
