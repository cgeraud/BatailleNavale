package fr.ensma.a3.ia.bataille_navale.GUI.endGame;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.initgame.InitGamePresenter;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class EndGameView extends VBox implements EventHandler<ActionEvent>, I_EndGameView {
	
	private EndGamePresenter presenter;
	
	private Button button;
	private Text winner;
	
	public EndGameView(I_GUIPres pres) {
		
		this.presenter = (EndGamePresenter) pres;
		
		
		this.button = new Button("Replay");
		this.button.addEventHandler(ActionEvent.ANY, this);
		
		this.winner = new Text("");
		
		this.getChildren().addAll(this.winner, this.button);
		this.setAlignment(Pos.CENTER);
	}
	

	@Override
	public void handle(ActionEvent arg0) {
		presenter.replay();
	}


	@Override
	public void setWinner(String winner) {
		this.winner.setText(winner);
	}
}

