package fr.ensma.a3.ia.bataille_navale.map;

import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public interface IMapPlayer extends IMapOpponent{
	public void updateMap();
	public void addShipToMap(IUnit myship);
	public IUnit getShipFromId(String id) throws ShipDoesNotExistException;
	public boolean noCollision(Coordinates coos, IUnit ship);
}
