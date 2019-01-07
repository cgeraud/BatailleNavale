package fr.ensma.a3.ia.bataille_navale.game_elements;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

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

	@Override
	public boolean isAlive() {
		return base.isAlive();
	}

	@Override
	public float getResistance() {
		return base.getResistance();
	}
}
