package fr.ensma.a3.ia.bataille_navale.movements;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.game_elements.ITile;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipOutOfMapException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipsOverlappingException;
import fr.ensma.a3.ia.bataille_navale.map.IMap;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class Translation implements IMovement{
	
	@Override
	public void move(IUnit ship, int value, IMap map) throws ShipOutOfMapException, ShipsOverlappingException {
		ArrayList<ITile> tiles = ship.getTiles();
		Coordinates tmp = null;
		// Test if move is possible
		for(ITile tile : tiles) {
			switch(ship.getDirection()) {
			case Horizontal:
				tmp = new Coordinates(tile.getCoordinates().getX() + value, 
						tile.getCoordinates().getY());
				break;
			case Vertical:
				tmp = new Coordinates(tile.getCoordinates().getX(), 
						tile.getCoordinates().getY() + value);
				break;
			}
			if(!map.isOnMap(tmp))
				throw new ShipOutOfMapException();
			if(!map.noCollision(tmp))
				throw new ShipsOverlappingException();
		}
		// Apply move to ship
		for(ITile tile : tiles) {
			switch(ship.getDirection()) {
			case Horizontal:
				tile.getCoordinates().setX(tile.getCoordinates().getX() + value);
				break;
			case Vertical:
				tile.getCoordinates().setY(tile.getCoordinates().getY() + value);
				break;
			}
		}
		// Update map
		map.updateMap();
	}

}
