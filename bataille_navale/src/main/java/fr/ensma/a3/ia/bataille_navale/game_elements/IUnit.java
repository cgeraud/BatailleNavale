package fr.ensma.a3.ia.bataille_navale.game_elements;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotAttackException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotFlareException;
import fr.ensma.a3.ia.bataille_navale.map.IMap;
import fr.ensma.a3.ia.bataille_navale.movements.IMovement;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public interface IUnit {
	public AttackResult takeDamage(float damage, Coordinates tilecoord);
	public String getId();
	public boolean isAlive();
	public AttackResult attack(IMap target, Coordinates coos) throws ShipIsDisabledException, ShipCannotAttackException;
	public float power();
	public void upgradeShip(float dmgReduce);
	public AttackResult flare(IMap target, Coordinates coos) throws ShipCannotFlareException, ShipIsDisabledException;
	public ArrayList<Coordinates> getUnitCoordinates();
	public ArrayList<ITile> getTiles();
	public void move(IMovement movement, int value, IMap map);
	public Direction getDirection();
	public void setDirection(Direction dir);
}
