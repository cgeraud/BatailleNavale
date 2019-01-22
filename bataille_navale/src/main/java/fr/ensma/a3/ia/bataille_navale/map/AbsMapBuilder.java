package fr.ensma.a3.ia.bataille_navale.map;

import java.util.Random;

import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipAlreadyExistsException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipFactory;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipOutOfMapException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

abstract public class AbsMapBuilder {
	protected Map map;
	
	public IMap getMap() {
		return map;
	}
	
	public void createMap() {
		map = new Map();
	}
	
	public void setSailShipOnMap() {
		Random rand = new Random();
		int x = rand.nextInt(map.getWidth());
		int y = rand.nextInt(map.getHeight());
		IUnit sailship = null;
		try {
			sailship = ShipFactory.CreateShip("SailBoat",
					ShipType.SailBoat, map, Direction.Horizontal, 
					new Coordinates(x,y));
		} catch (ShipAlreadyExistsException e) {
			e.printStackTrace();
		} catch (ShipOutOfMapException e) {
			e.printStackTrace();
		}
		
		map.addShipToMap(sailship);
	}
	
	public abstract void setMineOnMap();
}
