package fr.ensma.a3.ia.bataille_navale.map;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public interface IMap {
	public AttackResult fireAt(Coordinates target, float damage);
	public void updateMap() ;
	public void addShipToMap(IUnit myship);
	public IUnit getShipFromId(String id) throws ShipDoesNotExistException;
	public boolean isOnMap(Coordinates coos);
}
