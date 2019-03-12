package fr.ensma.a3.ia.bataille_navale.GUI.pregame;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIView;

public class PreGamePresenter implements I_GUIPres{
	
	private I_PreGameView view = null;
	private PreGameModel model = null;
	
	public PreGamePresenter() {
		this.model = new PreGameModel();
	}

	@Override
	public void setView(I_GUIView view) {
		this.view = (I_PreGameView) view;
	}

	@Override
	public I_GUIView getView() {
		return this.view;
	}

}
