package fr.ensma.a3.ia.bataille_navale.GUI.ingame.actionBar;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class ActionBarView extends VBox implements IActionBarView, EventHandler<ActionEvent> {
	
	private ActionBarPresenter presenter = null;
	private ArrayList<Button> buttons = new ArrayList<>();

	public ActionBarView(ActionBarPresenter pres) {
		this.presenter = pres;
		this.setAlignment(Pos.CENTER);
		
		for(EPossibleActions act : EPossibleActions.values()) {
			Button button = new Button(act.toString());
			buttons.add(button);
			if(!act.isAlwaysOn())
				setButtonAccess(act, false);
			else {
				setButtonAccess(act, true);
				button.addEventHandler(ActionEvent.ANY, this);
			}
		}
		
		this.getChildren().addAll(buttons);
	}
	
	@Override
	public void setButtonAccess(EPossibleActions act, boolean value) {
		Button but = this.buttons.get(act.getValue());
		if(!act.isAlwaysOn()) {
			but.setDisable(!value);
			if(value) {
				but.addEventHandler(ActionEvent.ANY, this);
			} else {
				but.removeEventHandler(ActionEvent.ANY, this);
			}
		}
	}

	@Override
	public void handle(ActionEvent arg0) {
		this.presenter.actionSelected(EPossibleActions.values()[buttons.indexOf(arg0.getSource())]);
	}
	
}
