package fr.ensma.a3.ia.bataille_navale.game_elements.Ships;

public class ShipCannotAttackException extends Exception {


	private static final long serialVersionUID = -6137731561767082347L;

	public ShipCannotAttackException() {
		super("this ship can not attack (mind: not disabled)");
	}
}
