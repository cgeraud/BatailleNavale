package fr.ensma.a3.ia.bataille_navale.game_elements;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.EFlareResult;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IShellResult;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotAttackException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotFlareException;
import fr.ensma.a3.ia.bataille_navale.map.IMapOpponent;
import fr.ensma.a3.ia.bataille_navale.map.IMapPlayer;
import fr.ensma.a3.ia.bataille_navale.movements.IMovement;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public interface IUnit {
	public IShellResult takeDamage(float damage, Coordinates tilecoord);
	public String getId();
	public boolean isAlive();
	public IShellResult attack(IMapOpponent target, Coordinates coos) throws ShipIsDisabledException, ShipCannotAttackException;
	public float power();
	public void upgradeShip(float dmgReduce);
	public EFlareResult flare(IMapOpponent target, Coordinates coos) throws ShipCannotFlareException, ShipIsDisabledException;
	public ArrayList<Coordinates> getUnitCoordinates();
	public ArrayList<ITile> getTiles();
	public void move(IMovement movement, Coordinates start, Coordinates end, IMapPlayer map);
	public Direction getDirection();
	public void setDirection(Direction dir);
}
