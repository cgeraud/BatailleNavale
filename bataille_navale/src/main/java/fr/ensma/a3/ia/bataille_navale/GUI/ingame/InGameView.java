package fr.ensma.a3.ia.bataille_navale.GUI.ingame;
import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.ingame.actionBar.ActionBarView;
import fr.ensma.a3.ia.bataille_navale.GUI.ingame.actionBar.IActionBarView;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.I_PlayGridView;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.PlayGridView;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.shipbar.I_ShipBarView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

public class InGameView extends VBox implements I_InGameView, EventHandler<KeyEvent> {
	
	private HBox mainLayout = new HBox();

	private InGamePresenter presenter = null;
	
	private PlayGridView playergrid = null;
	private PlayGridView oponentgrid = null;
	
	private ActionBarView actionbar = null;
	private Text coolDownText = null;
	private Text playerMissedText = null;
	private Text opponentMissedText = null;
	
	public InGameView(I_GUIPres pres) {
		this.presenter = (InGamePresenter) pres;
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		
		this.addEventHandler(KeyEvent.KEY_PRESSED, this);
		
		this.mainLayout.setAlignment(Pos.CENTER);
		
		this.playergrid = new PlayGridView(this.presenter.getPlayerGridPresenter());
		this.playergrid.setPadding(new Insets(20,20,20,20));
		
		this.oponentgrid = new PlayGridView(this.presenter.getOponentGridPresenter());
		this.oponentgrid.setPadding(new Insets(20,20,20,20));
		
		this.actionbar = new ActionBarView(this.presenter.getActionBarPresenter());
		
		this.coolDownText = new Text("Hello there !");
		this.playerMissedText = new Text("Hello there !");
		this.opponentMissedText = new Text("Hello there !");
		
		this.mainLayout.getChildren().addAll(this.playergrid, this.actionbar, this.oponentgrid);
		this.getChildren().addAll(this.coolDownText, this.playerMissedText, this.opponentMissedText, this.mainLayout);
	}
	
	@Override
	public I_PlayGridView getPlayerView() {
		return this.playergrid;
	}

	@Override
	public I_PlayGridView getOponentView() {
		return this.oponentgrid;
	}

	@Override
	public IActionBarView getActionBarView() {
		return this.actionbar;
	}

	@Override
	public void setCoolDownText(String text) {
		this.coolDownText.setText(text);
	}
	
	@Override
	public void setPlayerMissedText(String text) {
		this.playerMissedText.setText(text);
	}
	
	@Override
	public void setOpponentMissedText(String text) {
		this.opponentMissedText.setText(text);
	}

	@Override
	public void handle(KeyEvent arg0) {
		if(arg0.getCode().equals(KeyCode.A)) {
			this.presenter.abandon();
			}
	}
}
