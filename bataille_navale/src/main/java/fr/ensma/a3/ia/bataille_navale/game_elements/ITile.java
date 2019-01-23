package fr.ensma.a3.ia.bataille_navale.game_elements;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;

import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
public interface ITile {
	public AttackResult takeDamage(float damage);
	public boolean isAlive();
	public float getResistance();
	public Coordinates getCoordinates();
}
