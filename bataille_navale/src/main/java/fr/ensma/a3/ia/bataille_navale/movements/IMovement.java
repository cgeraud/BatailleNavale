package fr.ensma.a3.ia.bataille_navale.movements;

import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipOutOfMapException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipsOverlappingException;
import fr.ensma.a3.ia.bataille_navale.map.IMapPlayer;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public interface IMovement {
	public void move(IUnit ship, Coordinates start, Coordinates end, IMapPlayer map) throws ShipOutOfMapException, ShipsOverlappingException;
	public boolean canMove(IUnit ship, Coordinates start, Coordinates end, IMapPlayer map);
}
