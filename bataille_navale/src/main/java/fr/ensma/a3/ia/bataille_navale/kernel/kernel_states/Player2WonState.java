package fr.ensma.a3.ia.bataille_navale.kernel.kernel_states;

public class Player2WonState extends AbsKernelState {

	public Player2WonState(IKernelAutomaton kernel) {
		super(kernel);
	}
	
	@Override
	public void replay() {
		this.getKernel().setCurrentPlayer(null);
		this.getKernel().setCurrentOpponent(null);
		this.getKernel().setCurrentState(this.getKernel().getPreGameState());
		this.getKernel().notifyPreGame();
		LOGGER.info("Reset game");
	}
	
	@Override
	public void quit() {
		this.getKernel().setCurrentPlayer(null);
		this.getKernel().setCurrentOpponent(null);
		this.getKernel().resetPlayers();
		this.getKernel().setCurrentState(this.getKernel().getInitGameState());
		this.getKernel().notifyQuit();
		LOGGER.info("Quit game");
	}

}
