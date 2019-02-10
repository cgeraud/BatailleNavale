package fr.ensma.a3.ia.bataille_navale.kernel.kernel_states;

import java.util.logging.Logger;

import fr.ensma.a3.ia.bataille_navale.kernel.GameKernel;

public class AbsKernelState implements IKernelState{
	
	private IKernelAutomaton kernel;
	protected final static Logger LOGGER = Logger.getLogger(GameKernel.class.getName());
	
	
	public AbsKernelState(IKernelAutomaton kernel) {
		this.kernel = kernel;
	}
	
	public IKernelAutomaton getKernel() {
		return kernel;
	}

	@Override
	public void gameInitialized() throws IllegalKernelTransitionException {
		throw new IllegalKernelTransitionException();
	}

	@Override
	public void startGame() throws IllegalKernelTransitionException {
		throw new IllegalKernelTransitionException();
	}

	@Override
	public void changePlayer() throws IllegalKernelTransitionException {
		throw new IllegalKernelTransitionException();
	}

	@Override
	public void playerLost() throws IllegalKernelTransitionException {
		throw new IllegalKernelTransitionException();
	}

	@Override
	public void replay() throws IllegalKernelTransitionException {
		throw new IllegalKernelTransitionException();
	}

	@Override
	public void quit() throws IllegalKernelTransitionException {
		throw new IllegalKernelTransitionException();
	}

}
