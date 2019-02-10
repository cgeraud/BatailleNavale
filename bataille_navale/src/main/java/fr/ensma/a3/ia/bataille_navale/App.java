package fr.ensma.a3.ia.bataille_navale;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Player;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.kernel.GameKernel;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class App 
{
    public static void main( String[] args )
    {
    	/* For testing purposes only */
    	
    	GameKernel kernel = GameKernel.getGameKernel();
    	
    	try {
			kernel.setPlayer1(new Player());
			kernel.setPlayer2(new Player());

			kernel.getPlayer1().addNewShip("Lexington", ShipType.AircraftCarrier, Direction.Horizontal, new Coordinates(0,0));
			kernel.getPlayer1().addNewShip("Nimitz", ShipType.Cruiser, Direction.Horizontal, new Coordinates(0,2));
			kernel.getPlayer1().addNewShip("Fletcher", ShipType.Destroyer, Direction.Horizontal, new Coordinates(0,3));
			kernel.getPlayer1().addNewShip("Nautilus", ShipType.Submarine, Direction.Horizontal, new Coordinates(0,4));
			kernel.getPlayer1().addNewShip("Plouffy", ShipType.TorpedoBoat, Direction.Horizontal, new Coordinates(0,5));
		
			kernel.getPlayer2().addNewShip("Hiryu", ShipType.AircraftCarrier, Direction.Horizontal, new Coordinates(0,0));
			kernel.getPlayer2().addNewShip("Yamato", ShipType.Cruiser, Direction.Horizontal, new Coordinates(0,2));
			kernel.getPlayer2().addNewShip("Senzu", ShipType.Destroyer, Direction.Horizontal, new Coordinates(0,3));
			kernel.getPlayer2().addNewShip("Musashi", ShipType.Submarine, Direction.Horizontal, new Coordinates(0,4));
			kernel.getPlayer2().addNewShip("Purufu", ShipType.TorpedoBoat, Direction.Horizontal, new Coordinates(0,5));
		
			kernel.startGame();
		
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(0,0), "Lexington");
			
			kernel.changePlayer();
			
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(9,9), "Senzu");
			
			kernel.changePlayer();
			
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(1,0), "Lexington");
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(2,0), "Lexington");
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(0,1), "Lexington");
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(1,1), "Lexington");
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(2,1), "Lexington");
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(3,1), "Lexington");
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(4,1), "Lexington");
			
			kernel.changePlayer();
			
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(0,2), "Senzu");
			
			kernel.changePlayer();
			
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(0,2), "Lexington");
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(1,2), "Lexington");
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(2,2), "Lexington");
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(3,2), "Lexington");
			
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(0,3), "Lexington");
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(1,3), "Lexington");
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(2,3), "Lexington");
			
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(0,4), "Lexington");
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(1,4), "Lexington");
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(2,4), "Plouffy");
			
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(0,5), "Lexington");
			kernel.getCurrentPlayer().attack(kernel.getCurrentOpponent(), 
					new Coordinates(1,5), "Lexington");
			
			kernel.changePlayer();
			
			kernel.quit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
