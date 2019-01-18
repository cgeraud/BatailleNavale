package fr.ensma.a3.ia.bataille_navale.game_elements.Ships;

public class ShipCannotFlareException extends Exception {

	private static final long serialVersionUID = -9194970751548731069L;

	public ShipCannotFlareException() {
		super("This ship can not use Flares");
	}
}
