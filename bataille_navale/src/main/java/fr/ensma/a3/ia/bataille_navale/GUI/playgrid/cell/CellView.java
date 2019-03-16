package fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class CellView extends BorderPane implements I_CellView, EventHandler<Event>{
	
	private CellPresenter presenter = null;
	
	private Button button = new Button("");
	
	public CellView(I_GUIPres presenter) {
		this.presenter = (CellPresenter) presenter;
		this.setCenter(button);

		this.button.setMinWidth(32.0f);
		this.button.setMaxWidth(32.0f);
		this.button.setMinHeight(32.0f);
		this.button.setMaxHeight(32.0f);
		this.button.addEventHandler(Event.ANY, this);
	}

	@Override
	public void handle(Event arg0) {
		if(arg0.getEventType()==ActionEvent.ANY) {
			this.presenter.onClick();
		} else if (arg0.getEventType()==MouseEvent.MOUSE_ENTERED_TARGET) {
			System.out.println("p");
			this.presenter.onMouseEnter();
		} else if (arg0.getEventType()==MouseEvent.MOUSE_EXITED_TARGET) {
			// For future expansion
		}
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
