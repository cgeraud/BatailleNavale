package fr.ensma.a3.ia.bataille_navale.game_elements;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;

public class Tile implements ITile {
	
	private float resistance;
	
	public Tile(float resist) {
		resistance = resist;
	}

	@Override
	public AttackResult takeDamage(float damage) {
		resistance -= damage;
		AttackResult res = AttackResult.Hit;
		if (resistance < 1e-10) {
			resistance = 0.0f;
			res = AttackResult.Destroyed;
		}
		return res;
	}
	
	@Override
	public float getResistance() {
		return resistance;
	}
	
	@Override
	public boolean isAlive() {
		return (this.resistance > 0.0f);
	}
}
