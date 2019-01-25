package fr.ensma.a3.ia.bataille_navale.movements;

public class ZeroMovementException extends Exception {

	private static final long serialVersionUID = -7860104846684793711L;
	
	public ZeroMovementException() {
		super("Move had no effect");
	}

}
