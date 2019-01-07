package fr.ensma.a3.ia.bataille_navale.game_elements;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
public interface ITile {
	public AttackResult takeDamage(float damage);
	public boolean isAlive();
	public float getResistance();
}
