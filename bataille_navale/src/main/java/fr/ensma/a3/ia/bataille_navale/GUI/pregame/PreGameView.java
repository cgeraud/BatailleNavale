package fr.ensma.a3.ia.bataille_navale.GUI.pregame;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.I_PlayGridView;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.PlayGridView;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.shipbar.I_ShipBarView;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.shipbar.ShipBarView;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class PreGameView extends HBox implements I_PreGameView, 
EventHandler<Event>{
	
	private PreGamePresenter presenter = null;
	
	private ShipBarView shipBar = null;
	private Button okButton = null;
	private PlayGridView grid = null;
	
	public PreGameView(I_GUIPres pres) {
		this.presenter = (PreGamePresenter) pres;
		this.setAlignment(Pos.CENTER);
		
		this.shipBar = new ShipBarView(this.presenter.getShipBarPresenter());
		this.grid = new PlayGridView(this.presenter.getGridPresenter());
		
		this.okButton = new Button("Done !");
		this.setOkButtonAccess(false);
		
		this.addEventHandler(KeyEvent.ANY, this);
		
		this.getChildren().addAll(this.shipBar, this.grid, this.okButton);
	}
	
	@Override
	public I_PlayGridView getGridView() {
		return this.grid;
	}

	@Override
	public I_ShipBarView getBarView() {
		return this.shipBar;
	}

	@Override
	public void handle(Event arg0) {
		if(arg0.getEventType()==ActionEvent.ANY) {
			this.presenter.placementDone();
		}
		else if(arg0.getEventType()==KeyEvent.KEY_PRESSED) {
			KeyEvent evt = (KeyEvent) arg0;
			if(evt.getCode()==KeyCode.R) {
				this.presenter.rotateShip();
			}
		}
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
