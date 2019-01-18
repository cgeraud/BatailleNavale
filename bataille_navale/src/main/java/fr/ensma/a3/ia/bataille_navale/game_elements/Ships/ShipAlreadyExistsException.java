package fr.ensma.a3.ia.bataille_navale.game_elements.Ships;

public class ShipAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 5464008929860411249L;

	public ShipAlreadyExistsException() {
		super("Ship id already exists");
	}
}
