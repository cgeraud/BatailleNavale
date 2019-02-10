package fr.ensma.a3.ia.bataille_navale.kernel.kernel_states;

public interface IKernelState {
	void gameInitialized() throws IllegalKernelTransitionException;
	void startGame() throws IllegalKernelTransitionException;
	void changePlayer() throws IllegalKernelTransitionException;
	void playerLost() throws IllegalKernelTransitionException;
	void replay() throws IllegalKernelTransitionException;
	void quit() throws IllegalKernelTransitionException;
}
