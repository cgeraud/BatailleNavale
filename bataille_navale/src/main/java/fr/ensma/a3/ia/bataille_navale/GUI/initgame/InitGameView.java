package fr.ensma.a3.ia.bataille_navale.GUI.initgame;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class InitGameView extends VBox implements I_InitGameView, EventHandler<ActionEvent> {
	
	private InitGamePresenter presenter;
	
	private Button button;
	
	public InitGameView(I_GUIPres pres) {
		this.presenter = (InitGamePresenter) pres;
		
		this.button = new Button("Player vs Computer");
		this.button.addEventHandler(ActionEvent.ANY, this);
		
		this.getChildren().add(this.button);
		this.setAlignment(Pos.CENTER);
	}

	@Override
	public void handle(ActionEvent arg0) {
		presenter.p_vs_e_selected();
	}
}
