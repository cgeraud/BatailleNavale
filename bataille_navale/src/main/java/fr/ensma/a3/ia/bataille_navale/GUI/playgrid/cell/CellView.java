package fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class CellView extends BorderPane implements I_CellView, EventHandler<ActionEvent>{
	
	private CellPresenter presenter = null;
	
	private Button button = new Button("");
	
	public CellView(I_GUIPres presenter) {
		this.presenter = (CellPresenter) presenter;
		this.setWidth(32.0f);
		this.setHeight(32.0f);
		this.setCenter(button);
		
		this.button.setPrefWidth(this.button.getMaxWidth());
		this.button.setPrefHeight(this.button.getMaxHeight());
		this.button.addEventHandler(ActionEvent.ANY, this);
	}

	@Override
	public void handle(ActionEvent arg0) {
		this.presenter.onClick();
	}

	@Override
	public void setContent(String content) {
		this.button.setText(content);
	}
	
	@Override
	public void setDeactivated(boolean disable) {
		this.button.setDisable(disable);
	}

}
