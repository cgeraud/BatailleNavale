package fr.ensma.a3.ia.bataille_navale.game_elements;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public abstract class AbstractBoosted implements IAttaquable{
	
	protected final IAttaquable base;
	
	public AbstractBoosted(IAttaquable baseShip) {
		base = baseShip;
	}
	
	@Override
	public AttackResult takeDamage(float damage, Coordinates tilecoord) {
		return base.takeDamage(damage, tilecoord);
	}
	
	@Override
	public boolean isAlive() {
		return base.isAlive();
	}
}
