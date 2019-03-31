package fr.ensma.a3.ia.bataille_navale.GUI.pregame.shipbar;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ShipBarView extends VBox implements I_ShipBarView, EventHandler<ActionEvent>  {
	
	private ShipBarPresenter presenter = null;
	
	private ArrayList<Button> shipButtons = new ArrayList<Button>();
	
	public ShipBarView(I_GUIPres pres) {
		this.presenter = (ShipBarPresenter) pres;
		
		shipButtons.add(new Button("Torpedo boat"));
		shipButtons.add(new Button("Destroyer"));
		shipButtons.add(new Button("Submarine"));
		shipButtons.add(new Button("Cruiser"));
		shipButtons.add(new Button("Carrier vehicle"));
		

		
		this.getChildren().addAll(shipButtons);
		this.setAlignment(Pos.CENTER);
		
		for(Node but : this.getChildren()) {
			but.addEventHandler(ActionEvent.ANY, this);
		}
	}

	@Override
	public void handle(ActionEvent arg0) {
		this.presenter.buttonClicked(shipButtons.indexOf(arg0.getSource()));
		
	}

	@Override
	public void setButtonDisabled(int index, boolean bool) {
		this.shipButtons.get(index).setDisable(bool);	
		if(!bool) {
			this.shipButtons.get(index).removeEventHandler(ActionEvent.ANY, this);
		}
		else {
			this.shipButtons.get(index).addEventHandler(ActionEvent.ANY, this);
		}
	}
}
