package fr.ensma.a3.ia.bataille_navale.kernel.kernel_states;

public class PreGameState extends AbsKernelState {

	public PreGameState(IKernelAutomaton kernel) {
		super(kernel);
	}
	
	@Override
	public void startGame() {
		this.getKernel().setCurrentPlayer(this.getKernel().getPlayer1());
		this.getKernel().setCurrentOpponent(this.getKernel().getPlayer2());
		this.getKernel().setCurrentState(this.getKernel().getPlayer1TurnState());;
		LOGGER.info("Game started : player 1 begins");
	}
}
