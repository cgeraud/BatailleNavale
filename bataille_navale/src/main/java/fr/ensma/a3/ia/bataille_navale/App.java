package fr.ensma.a3.ia.bataille_navale;

import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.GameGUIPresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.game_gui.GameGUIView;
import fr.ensma.a3.ia.bataille_navale.kernel.GameKernel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{	
	@Override
	public void start(Stage primaryStage) {
  	
    	GameGUIPresenter gui = new GameGUIPresenter();
    	GameKernel.getGameKernel().addObserver(gui);
    	
		GameGUIView root = new GameGUIView(gui);
		gui.setView(root);
		
		Scene scene = new Scene(root, 1024, 640);
		primaryStage.setTitle("Bataille Navale");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
