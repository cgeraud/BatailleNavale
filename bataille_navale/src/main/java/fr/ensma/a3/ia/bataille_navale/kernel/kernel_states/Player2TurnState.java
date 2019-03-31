package fr.ensma.a3.ia.bataille_navale.kernel.kernel_states;

public class Player2TurnState extends AbsKernelState {

	public Player2TurnState(IKernelAutomaton kernel) {
		super(kernel);
	}
	
	@Override
	public void changePlayer() {
		this.getKernel().setCurrentPlayer(this.getKernel().getPlayer1());
		this.getKernel().setCurrentOpponent(this.getKernel().getPlayer2());
		this.getKernel().setCurrentState(this.getKernel().getPlayer1TurnState());
		LOGGER.info("Player 1's turn");
		this.getKernel().notifyPlayer1Turn();
	}

	@Override
	public void playerLost() {
		this.getKernel().setCurrentState(this.getKernel().getPlayer1WonState());
		this.getKernel().notifyPlayer1Won();
		LOGGER.info("Player 1 has won !");
	}

}
