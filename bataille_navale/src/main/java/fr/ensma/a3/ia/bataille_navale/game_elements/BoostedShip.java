package fr.ensma.a3.ia.bataille_navale.game_elements;

import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class BoostedShip extends AbstractBoosted {
	
	private final float damagereduction;

	public BoostedShip(IAttaquable baseShip, float damagereduction) {
		super(baseShip);
		this.damagereduction = damagereduction;
	}
	
	@Override
	public void takeDamage(float damage, Coordinates tilecoord) {
		base.takeDamage((1.0f-damagereduction)*damage, tilecoord);
	}

}
