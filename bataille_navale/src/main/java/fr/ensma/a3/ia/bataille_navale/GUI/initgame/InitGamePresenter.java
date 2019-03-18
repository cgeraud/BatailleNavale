package fr.ensma.a3.ia.bataille_navale.GUI.initgame;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIView;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Player;
import fr.ensma.a3.ia.bataille_navale.kernel.GameKernel;
import fr.ensma.a3.ia.bataille_navale.kernel.kernel_states.IllegalKernelTransitionException;

public class InitGamePresenter implements I_GUIPres{
	
	private I_InitGameView view = null;
	//private InitGameModel model = null;
	
	public InitGamePresenter() {
		//this.model = new InitGameModel();
	}

	@Override
	public void setView(I_GUIView view) {
		this.view = (I_InitGameView) view;
	}

	@Override
	public I_GUIView getView() {
		return this.view;
	}
	
	/*
	 * UI interaction methods
	 */

	public void p_vs_e_selected() {
		try {
			GameKernel.getGameKernel().setPlayer1(new Player());
			GameKernel.getGameKernel().setPlayer2(new Player());
		} catch (IllegalKernelTransitionException e) {
			e.printStackTrace();
		}
	}

}
