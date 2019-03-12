package fr.ensma.a3.ia.bataille_navale.GUI.pregame;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import javafx.scene.layout.HBox;

public class PreGameView extends HBox implements I_PreGameView {
	
	private PreGamePresenter presenter = null;
	
	public PreGameView(I_GUIPres pres) {
		this.presenter = (PreGamePresenter) pres;
	}
}
