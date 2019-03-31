package fr.ensma.a3.ia.bataille_navale.game_elements;

import java.util.logging.Logger;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.EAttackEffect;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class Tile implements ITile {
	Logger logger = Logger.getLogger(Tile.class.getName());
	
	private final float initialResistance;
	private float resistance;
	private Coordinates coords;
	
	public Tile(float resist, Coordinates coord) {
		this.initialResistance = resist;
		this.resistance = resist;
		this.coords = coord;
	}

	@Override
	public AttackResult takeDamage(float damage) {
		resistance -= damage;
		AttackResult res = new AttackResult();
		res.setFireResult(EAttackEffect.Hit);
		if (resistance < 1e-3) {
			resistance = 0.0f;
			res.setFireResult(EAttackEffect.TileDestroyed);
		}
		logger.info("Tile HP:" + resistance);
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

	@Override
	public Coordinates getCoordinates() {
		return coords;
	}

	@Override
	public boolean isDamaged() {
		return (resistance-initialResistance)<(-1e-3);
	}
	
}
