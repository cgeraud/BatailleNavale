package fr.ensma.a3.ia.bataille_navale.game_elements;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public abstract class AbstractBoosted implements ITile{
	
	protected final ITile base;
	
	public AbstractBoosted(ITile baseTile) {
		base = baseTile;
	}
	
	@Override
	public AttackResult takeDamage(float damage) {
		return base.takeDamage(damage);
	}
	
	@Override
	public boolean isAlive() {
		return base.isAlive();
	}
	
	@Override
	public Coordinates getCoordinates() {
		return base.getCoordinates();
	}
	
	@Override
	public float getResistance() {
		return base.getResistance();
	}
	
	@Override
	public boolean isDamaged() {
		return base.isDamaged();
	}
}
