package fr.ensma.a3.ia.bataille_navale.GUI.endGame;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIView;
import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.GameGUIPresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.initgame.I_InitGameView;

public class EndGamePresenter implements I_GUIPres{

	private I_EndGameView view = null;
	private I_EndGameObserver observer;
	
	public EndGamePresenter() {}
	
	@Override
	public void setView(I_GUIView view) {
		this.view = (I_EndGameView) view;
		
	}

	@Override
	public I_GUIView getView() {
		return this.view;
	}
	
	public void setWinner(String winner) {
		this.view.setWinner(winner);
	}

	public void replay() {
		observer.replayPushed();
	}

	public void addObserver(GameGUIPresenter gameGUIPresenter) {
		this.observer = gameGUIPresenter;
	}

}
