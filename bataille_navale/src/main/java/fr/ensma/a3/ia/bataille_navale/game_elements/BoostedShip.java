package fr.ensma.a3.ia.bataille_navale.game_elements;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class BoostedShip extends AbstractBoosted {
	
	private final float damagereduction;

	public BoostedShip(IUnit baseShip, float damagereduction) {
		super(baseShip);
		this.damagereduction = damagereduction;
	}
	
	@Override
	public AttackResult takeDamage(float damage, Coordinates tilecoord) {
		System.out.println("yes");
		return base.takeDamage((1.0f-damagereduction)*damage, tilecoord);
	}

	@Override
	public boolean isAlive() {
		return base.isAlive();
	}

	@Override
	public AttackResult attack(Map target, Coordinates coos) throws ShipIsDisabledException {
		return base.attack(target, coos);
	}

	@Override
	public float power() {
		return base.power();
	}

}
