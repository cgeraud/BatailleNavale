package fr.ensma.a3.ia.bataille_navale.map;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.EFlareResult;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IShellResult;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public interface IMap {
	public IShellResult fireAt(Coordinates target, float damage);
	public EFlareResult revealMap(Coordinates target);
	public void updateMap() ;
	public void addShipToMap(IUnit myship);
	public IUnit getShipFromId(String id) throws ShipDoesNotExistException;
	public boolean isOnMap(Coordinates coos);
}
