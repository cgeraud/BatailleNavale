package fr.ensma.a3.ia.bataille_navale.game_elements;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotAttackException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotFlareException;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public interface IUnit {
	public AttackResult takeDamage(float damage, Coordinates tilecoord);
	public String getId();
	public boolean isAlive();
	public AttackResult attack(Map target, Coordinates coos) throws ShipIsDisabledException, ShipCannotAttackException;
	public float power();
	public void upgradeShip(float dmgReduce);
	public AttackResult flare(Map target, Coordinates coos) throws ShipCannotFlareException, ShipIsDisabledException;
}
