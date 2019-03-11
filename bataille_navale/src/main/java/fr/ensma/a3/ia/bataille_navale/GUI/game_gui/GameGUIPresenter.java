package fr.ensma.a3.ia.bataille_navale.GUI.game_gui;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.kernel.GameKernel;
import fr.ensma.a3.ia.bataille_navale.kernel.IGameKernelObserver;

public class GameGUIPresenter implements IGameKernelObserver{
	
	private GameKernel gameModel = null;
	private IGameGUIView gameView = null;
	private I_GUIPres activePres = null;
	
	public GameGUIPresenter() {
		this.gameModel = GameKernel.getGameKernel();
		
		// TODO initialization
		this.activePres = null;
		this.activePres.setView(null);
	}
	
	public void setView(IGameGUIView view) {
		this.gameView = view;
		this.gameView.setView(this.activePres.getView());
	}
	
	/*
	 * Automation-type methods
	 * States synced to kernel state.
	 */

	@Override
	public void notifyPreGame() {
		// TODO On game initialized, go to pre-game screen
		
	}

	@Override
	public void notifyPlayer1Turn() {
		// TODO Change to player 1
		
	}

	@Override
	public void notifyPlayer2Turn() {
		// TODO Change to player 2
		
	}

	@Override
	public void notifyPlayer1Won() {
		// TODO Show player 1 victory
		
	}

	@Override
	public void notifyPlayer2Won() {
		// TODO Show player 2 victory
		
	}

}
