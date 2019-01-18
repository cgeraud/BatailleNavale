package fr.ensma.a3.ia.bataille_navale;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Player;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.CrossAttack;
import fr.ensma.a3.ia.bataille_navale.game_elements.Cruiser;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipAlreadyExistsException;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class App 
{
    public static void main( String[] args )
    {
    	
        Map mapPlayer1 = new Map();
        Map mapPlayer2 = new Map();
        Player player1 = new Player(mapPlayer1);
        Player player2 = new Player(mapPlayer2);
        IUnit nimitz = null;
		try {
			nimitz = new Cruiser("Nimitz", mapPlayer1, Direction.Horizontal, new Coordinates(0,0));
		} catch (ShipAlreadyExistsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        IUnit yamato = null;
		try {
			yamato = new Cruiser("Yamato", mapPlayer2, Direction.Horizontal, new Coordinates(0,0));
		} catch (ShipAlreadyExistsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        yamato.upgradeShip(0.5f);
        try {
			player1.attack(player2, new Coordinates(0,0), nimitz);
			player1.attack(player2, new Coordinates(1,0), nimitz);
			player1.attack(player2, new Coordinates(2,0), nimitz);
			player1.attack(player2, new Coordinates(2,0), nimitz);
		} catch (ShipIsDisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        player2.setModeAttaque(new CrossAttack());
        try {
			player2.attack(player1, new Coordinates(1,1), yamato);
		} catch (ShipIsDisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
