package fr.ensma.a3.ia.bataille_navale.movements;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.game_elements.ITile;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipOutOfMapException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipsOverlappingException;
import fr.ensma.a3.ia.bataille_navale.map.IMap;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class Rotation implements IMovement{
	
	@Override
	public void move(IUnit ship, Coordinates start, Coordinates end, IMap map) throws ShipOutOfMapException, ShipsOverlappingException {
		ArrayList<ITile> tiles = ship.getTiles();
		Coordinates tmp = null;
		// Determine rotation parameters
		int sin = 0;
		switch(ship.getDirection()) {
		case Horizontal:
			if(end.getY()-start.getY()>0) {
				sin = 1;
			}
			else {
				sin = -1;
			}
			break;
		case Vertical:
			if(end.getX()-start.getX()>0) {
				sin = 1;
			}
			else {
				sin = -1;
			}
			break;
		}
		// Test if move is possible
		int x_o = start.getX();
		int y_o = start.getY();
		for(ITile tile : tiles) {
			int dx = tile.getCoordinates().getX() - x_o;
			int dy = tile.getCoordinates().getY() - y_o;
			if(sin > 0) {
				tmp = new Coordinates(x_o + dy, y_o - dx);
			}
			else {
				tmp = new Coordinates(x_o - dy, y_o + dx);
			}
			if(!map.isOnMap(tmp))
				throw new ShipOutOfMapException();
			if(!map.noCollision(tmp, ship))
				throw new ShipsOverlappingException();
		}
		// Apply move to ship
		for(ITile tile : tiles) {
			int dx = tile.getCoordinates().getX() - x_o;
			int dy = tile.getCoordinates().getY() - y_o;
			if(sin > 0) {
				tile.getCoordinates().setX(x_o + dy);
				tile.getCoordinates().setY(y_o - dx);
			}
			else {
				tile.getCoordinates().setX(x_o - dy);
				tile.getCoordinates().setY(y_o + dx);
			}
		}
		// Update map
		map.updateMap();
	}

}
