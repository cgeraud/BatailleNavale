package fr.ensma.a3.ia.bataille_navale.map;

public class ShipDoesNotExistException extends Exception {

	private static final long serialVersionUID = -2056006540444651561L;
	
	public ShipDoesNotExistException(String id) {
		super("Ship \"" + id + "\" does not exist on this map");
	}

}
