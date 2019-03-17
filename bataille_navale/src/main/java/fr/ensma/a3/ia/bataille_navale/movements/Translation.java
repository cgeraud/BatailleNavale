package fr.ensma.a3.ia.bataille_navale.movements;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.game_elements.ITile;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipOutOfMapException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipsOverlappingException;
import fr.ensma.a3.ia.bataille_navale.map.IMapPlayer;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class Translation implements IMovement{
	
	@Override
	public void move(IUnit ship, Coordinates start, Coordinates end, IMapPlayer map) throws ShipOutOfMapException, ShipsOverlappingException, ZeroMovementException {
		ArrayList<ITile> tiles = ship.getTiles();
		Coordinates tmp = null;
		// Determine length of travel
		int value = 0;
		switch(ship.getDirection()) {
		case Horizontal:
			value = end.getX()-start.getX();
			break;
		case Vertical:
			value = end.getY()-start.getY();
			break;
		}
		// If no move, exception
		if(value==0)
			throw new ZeroMovementException();
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
			if(!map.noCollision(tmp, ship))
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

	@Override
	public boolean canMove(IUnit ship, Coordinates start, Coordinates end, IMapPlayer map) {
		ArrayList<ITile> tiles = ship.getTiles();
		Coordinates tmp = null;
		// Determine length of travel
		int value = 0;
		switch(ship.getDirection()) {
		case Horizontal:
			value = end.getX()-start.getX();
			break;
		case Vertical:
			value = end.getY()-start.getY();
			break;
		}
		// If no move, exception
		if(value==0)
			return true;
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
				return false;
			if(!map.noCollision(tmp, ship))
				return false;
		}
		return true;
	}

}
