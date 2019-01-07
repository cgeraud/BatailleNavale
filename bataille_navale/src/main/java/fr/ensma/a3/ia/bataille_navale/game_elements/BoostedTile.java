package fr.ensma.a3.ia.bataille_navale.game_elements;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;

public class BoostedTile extends AbstractBoosted {
	
	private final float damagereduction;

	public BoostedTile(ITile baseTile, float damagereduction) {
		super(baseTile);
		this.damagereduction = damagereduction;
	}
	
	@Override
	public AttackResult takeDamage(float damage) {
		return base.takeDamage((1.0f-damagereduction)*damage);
	}
}
