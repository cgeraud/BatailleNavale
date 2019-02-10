package fr.ensma.a3.ia.bataille_navale.kernel.kernel_states;

public class GameInitState extends AbsKernelState {
	
	public GameInitState(IKernelAutomaton kernel) {
		super(kernel);
	}

	@Override
	public void gameInitialized() {
		this.getKernel().setCurrentState(this.getKernel().getPreGameState());
		this.getKernel().notifyPreGame();
		LOGGER.info("Game initialized");
	}
	
}
