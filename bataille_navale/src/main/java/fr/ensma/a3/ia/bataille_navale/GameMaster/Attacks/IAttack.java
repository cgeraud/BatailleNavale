package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotAttackException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotFlareException;
import fr.ensma.a3.ia.bataille_navale.map.IMapOpponent;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public interface IAttack {
	public int attack(IUnit bateau, IMapOpponent targetMap, Coordinates coos) throws ShipIsDisabledException, AttackOutOfMapException, ShipCannotAttackException, ShipCannotFlareException;
}
