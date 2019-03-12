package fr.ensma.a3.ia.bataille_navale.GUI.pregame;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.shipbar.ShipBarView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class PreGameView extends HBox implements I_PreGameView, EventHandler<ActionEvent> {
	
	private PreGamePresenter presenter = null;
	
	private ShipBarView shipBar = null;
	private Button okButton = null;
	
	public PreGameView(I_GUIPres pres) {
		this.presenter = (PreGamePresenter) pres;
		this.setAlignment(Pos.CENTER);
		
		this.shipBar = new ShipBarView(this.presenter.getShipBarPresenter());
		this.presenter.getShipBarPresenter().setView(this.shipBar);
		
		this.okButton = new Button("Done !");
		this.setOkButtonAccess(true); //TODO access is false on initialisation
		
		this.getChildren().addAll(this.shipBar, this.okButton);
	}

	@Override
	public void handle(ActionEvent arg0) {
		this.presenter.placementDone();
	}

	@Override
	public void setOkButtonAccess(boolean access) {
		this.okButton.setDisable(!access);
		if(access) {
			this.okButton.addEventHandler(ActionEvent.ANY, this);
		}
		else {
			this.okButton.removeEventHandler(ActionEvent.ANY, this);
		}
	}
}
