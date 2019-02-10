package fr.ensma.a3.ia.bataille_navale.kernel.kernel_states;

public class Player1TurnState extends AbsKernelState {

	public Player1TurnState(IKernelAutomaton kernel) {
		super(kernel);
	}
	
	@Override
	public void changePlayer() {
		this.getKernel().setCurrentPlayer(this.getKernel().getPlayer2());
		this.getKernel().setCurrentOpponent(this.getKernel().getPlayer1());
		this.getKernel().setCurrentState(this.getKernel().getPlayer2TurnState());
		this.getKernel().notifyPlayer2Turn();
		LOGGER.info("Player 2's turn");
	}

	@Override
	public void playerLost() {
		this.getKernel().setCurrentState(this.getKernel().getPlayer2WonState());
		this.getKernel().notifyPlayer2Won();
		LOGGER.info("Player 2 has won !");
	}

}
