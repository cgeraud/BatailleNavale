package fr.ensma.a3.ia.bataille_navale.game_elements.Ships;

public class ShipsOverlappingException extends Exception {
	
	private static final long serialVersionUID = 7714981800278435649L;

	public ShipsOverlappingException(){
		super("Ship is overlapping another one.");
	}

}
