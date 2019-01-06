package fr.ensma.a3.ia.bataille_navale.game_elements;

import java.util.Objects;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public abstract class AbstractShip implements IAttaquable, IOffensif{
	
	private final int length;
	private final Coordinates reference;
	private final Direction direction;
	private final Tile[] tiles;

	public AbstractShip(int len, Direction dir, Coordinates ref) {
		Objects.requireNonNull(ref, "null reference point");
		tiles = new Tile[len];
		length = len;
		for (int i = 0; i < length; i++) {
			tiles[i] = new Tile((float)length);
		}
		reference = ref;
		direction = dir;
	}
	
	@Override
	public float power() {
		float damage = 0.0f;
		for (Tile tile : tiles) {
			// Ajoute 1.0 de degats pour chaque tile intact
            if(Math.abs((float)length-tile.getResistance())<1e-10) {
            	damage += 1.0;
            }
        }
		return damage;
	}

	// TODO exceptions
	public void attack(Map map, Coordinates target) throws ShipIsDisabledException {
		
		float damage = power();
		
		if(Math.abs(damage)<1e-10) {
			throw new ShipIsDisabledException();
		}
		
		map.fireAt(target, damage);
	}

	@Override
	public AttackResult takeDamage(float damage, Coordinates tilecoord) {
		long id = 0;
		switch(direction) {
		case Horizontal:
			id = Math.abs(tilecoord.getX() - reference.getX());
		case Vertical:
			id = Math.abs(tilecoord.getY() - reference.getY());
		}
		return tiles[(int)id].takeDamage(damage);
	}
	
	@Override
	public boolean isAlive() {
		for (Tile tile : tiles) {
			// si Une tile est en vie, True
			if (tile.isAlive()) {
				return true;
			}
		}
		//si aucune: false
		return false;
	}
}
