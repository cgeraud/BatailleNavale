package fr.ensma.a3.ia.bataille_navale.game_elements;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public interface IAttaquable {
	public AttackResult takeDamage(float damage, Coordinates tilecoord);
}
