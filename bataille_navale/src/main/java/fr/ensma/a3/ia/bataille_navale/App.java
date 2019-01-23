package fr.ensma.a3.ia.bataille_navale;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Player;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.CrossAttack;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipAlreadyExistsException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipFactory;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipOutOfMapException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.map.MapBuilderPlayer1;
import fr.ensma.a3.ia.bataille_navale.map.MapBuilderPlayer2;
import fr.ensma.a3.ia.bataille_navale.map.MapDirector;
import fr.ensma.a3.ia.bataille_navale.map.ShipDoesNotExistException;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class App 
{
    public static void main( String[] args )
    {
    	/* For testing puposes only */
    	MapDirector md = new MapDirector();
    	
    	md.setBuilder(new MapBuilderPlayer1());
    	md.buildMap();
    	Player player1 = new Player(md.getMap());
        
        md.setBuilder(new MapBuilderPlayer2());
    	md.buildMap();
    	Player player2 = new Player(md.getMap());
        
        IUnit nimitz = null;
		try {
			nimitz = ShipFactory.CreateShip("Nimitz", ShipType.Cruiser, player1.getMap(), Direction.Horizontal, new Coordinates(0,0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        IUnit yamato = null;
		try {
			try {
				yamato = ShipFactory.CreateShip("Yamato", ShipType.Cruiser, player2.getMap(), Direction.Horizontal, new Coordinates(0,0));
			} catch (ShipOutOfMapException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ShipAlreadyExistsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		IUnit GFord = null;
		try {
			GFord = ShipFactory.CreateShip("GFord", ShipType.AircraftCarrier, player1.getMap(), Direction.Vertical, new Coordinates(0,5));
		} catch (ShipAlreadyExistsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ShipOutOfMapException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        yamato.upgradeShip(0.5f);
        try {
			player1.attack(player2, new Coordinates(0,0), "Nimitz");
			player1.attack(player2, new Coordinates(1,0), "Nimitz");
			player1.attack(player2, new Coordinates(2,0), "Nimitz");
			player1.attack(player2, new Coordinates(2,0), "Nimitz");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        player2.setModeAttaque(new CrossAttack());
        try {
			player2.attack(player1, new Coordinates(1,4), "Yamato");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /*
        try {
			System.out.println(player1.getMap().getShipFromId("Nimitz").power());
			System.out.println(player2.getMap().getShipFromId("Yamato").power());
        } catch (ShipDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        */
    }
}
