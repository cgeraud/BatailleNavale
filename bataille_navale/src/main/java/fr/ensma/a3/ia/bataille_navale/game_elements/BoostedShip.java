package fr.ensma.a3.ia.bataille_navale.game_elements;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class BoostedShip extends AbstractBoosted {
	
	private final float damagereduction;

	public BoostedShip(IAttaquable baseShip, float damagereduction) {
		super(baseShip);
		this.damagereduction = damagereduction;
	}
	
	@Override
	public AttackResult takeDamage(float damage, Coordinates tilecoord) {
		return base.takeDamage((1.0f-damagereduction)*damage, tilecoord);
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

}
