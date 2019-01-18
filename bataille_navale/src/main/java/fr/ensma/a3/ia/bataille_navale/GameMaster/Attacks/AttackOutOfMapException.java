package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class AttackOutOfMapException extends Exception {

	private static final long serialVersionUID = 7151886548457160954L;
	
	public AttackOutOfMapException(Coordinates coord) {
		super("Attack coordinates out of bounds : (" + coord.getX() + 
				";" + coord.getY() + ").");
	}

}
