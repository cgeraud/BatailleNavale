package fr.ensma.a3.ia.bataille_navale.GUI.pregame.shipbar;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ShipBarView extends VBox implements I_ShipBarView, EventHandler<ActionEvent>  {
	
	private ShipBarPresenter presenter = null;
	
	private Button torpButton = null;
	private Button destButton = null;
	private Button subButton = null;
	private Button cruiButton = null;
	private Button CVNButton = null;
	
	public ShipBarView(I_GUIPres pres) {
		this.presenter = (ShipBarPresenter) pres;
		
		torpButton = new Button("Torpedo boat");
		destButton = new Button("Destroyer");
		subButton = new Button("Submarine");
		cruiButton = new Button("Cruiser");
		CVNButton = new Button("Carrier vehicle");
		
		this.getChildren().addAll(torpButton, destButton, subButton,
				cruiButton, CVNButton);
		this.setAlignment(Pos.CENTER);
		
		for(Node but : this.getChildren()) {
			but.addEventHandler(ActionEvent.ANY, this);
		}
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
