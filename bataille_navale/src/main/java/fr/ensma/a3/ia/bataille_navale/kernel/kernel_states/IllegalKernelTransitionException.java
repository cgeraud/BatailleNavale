package fr.ensma.a3.ia.bataille_navale.kernel.kernel_states;

public class IllegalKernelTransitionException extends Exception {

	private static final long serialVersionUID = 7916342515944800893L;
	
	public IllegalKernelTransitionException() {
		super("Illegal transition taken.");
	}
}
