package fr.ensma.a3.ia.bataille_navale.GUI.game_gui;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIView;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class GameGUIView extends HBox implements IGameGUIView {
	private GameGUIPresenter pres = null;
	private I_GUIView currentView = null;
	
	public GameGUIView(GameGUIPresenter pres) {
		this.pres = pres;
	}

	@Override
	public void setView(I_GUIView view) {
		this.getChildren().clear();
		this.currentView = view;
		this.getChildren().add((Node) view);
	}
}
