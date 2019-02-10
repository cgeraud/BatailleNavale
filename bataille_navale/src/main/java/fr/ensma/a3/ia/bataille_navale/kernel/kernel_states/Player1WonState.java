package fr.ensma.a3.ia.bataille_navale.kernel.kernel_states;

public class Player1WonState extends AbsKernelState {

	public Player1WonState(IKernelAutomaton kernel) {
		super(kernel);
	}
	
	@Override
	public void replay() {
		this.getKernel().setCurrentPlayer(null);
		this.getKernel().setCurrentOpponent(null);
		this.getKernel().setCurrentState(this.getKernel().getPreGameState());
		LOGGER.info("Reset game");
	}
	
	@Override
	public void quit() {
		LOGGER.info("Quit game");
	}

}
