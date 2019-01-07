package fr.ensma.a3.ia.bataille_navale;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Player;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.CrossAttack;
import fr.ensma.a3.ia.bataille_navale.game_elements.BoostedShip;
import fr.ensma.a3.ia.bataille_navale.game_elements.Cruiser;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
        Map mapPlayer1 = new Map();
        Map mapPlayer2 = new Map();
        Player player1 = new Player(mapPlayer1);
        Player player2 = new Player(mapPlayer2);
        IUnit nimitz = new Cruiser(mapPlayer1, Direction.Horizontal, new Coordinates(0,0));
        IUnit yamato = new Cruiser(mapPlayer2, Direction.Horizontal, new Coordinates(0,0));
        yamato = new BoostedShip(yamato, 0.5f);
        player1.addShip(nimitz);
        try {
			player1.attack(player2, new Coordinates(0,0), nimitz);
		} catch (ShipIsDisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			player2.attack(player1, new Coordinates(0,0), yamato);
		} catch (ShipIsDisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
