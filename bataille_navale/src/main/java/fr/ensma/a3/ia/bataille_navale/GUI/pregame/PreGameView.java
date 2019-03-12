package fr.ensma.a3.ia.bataille_navale.GUI.pregame;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.shipbar.ShipBarView;
import javafx.scene.layout.HBox;

public class PreGameView extends HBox implements I_PreGameView {
	
	private PreGamePresenter presenter = null;
	
	private ShipBarView shipBar = null;
	
	public PreGameView(I_GUIPres pres) {
		this.presenter = (PreGamePresenter) pres;
		
		this.shipBar = new ShipBarView(this.presenter.getShipBarPresenter());
		this.presenter.getShipBarPresenter().setView(this.shipBar);
		
		this.getChildren().add(this.shipBar);
	}
}
