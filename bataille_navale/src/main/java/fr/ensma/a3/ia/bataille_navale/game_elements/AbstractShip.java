package fr.ensma.a3.ia.bataille_navale.game_elements;

import java.util.Objects;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public abstract class AbstractShip implements IUnit{
	
	private final int length;
	private final Coordinates reference;
	private final Direction direction;
	private final ITile[] tiles;

	public AbstractShip(Map map, int len, Direction dir, Coordinates ref) {
		Objects.requireNonNull(ref, "null reference point");
		tiles = new ITile[len];
		length = len;
		for (int i = 0; i < length; i++) {
			tiles[i] = new Tile((float)length);
		}
		reference = ref;
		direction = dir;
		map.addShipToMap(this, len, ref, dir);
	}
	
	@Override
	public float power() {
		float damage = 0.0f;
		for (ITile tile : tiles) {
			// Ajoute 1.0 de degats pour chaque tile intact
            if(Math.abs((float)length-tile.getResistance())<1e-10) {
            	damage += 1.0;
            }
        }
		return damage;
	}

	// TODO exceptions
	public AttackResult attack(Map map, Coordinates target) throws ShipIsDisabledException {
		
		float damage = power();
		
		if(Math.abs(damage)<1e-10) {
			throw new ShipIsDisabledException();
		}
		
		return map.fireAt(target, damage);
	}

	@Override
	public AttackResult takeDamage(float damage, Coordinates tilecoord) {
		long id = 0;
		switch(direction) {
		case Horizontal:
			id = Math.abs(tilecoord.getX() - reference.getX());
			break;
		case Vertical:
			id = Math.abs(tilecoord.getY() - reference.getY());
			break;
		}
		return tiles[(int)id].takeDamage(damage);
	}
	
	@Override
	public boolean isAlive() {
		for (ITile tile : tiles) {
			// si Une tile est en vie, True
			if (tile.isAlive()) {
				return true;
			}
		}
		//si aucune: false
		return false;
	}
	
	@Override
	public void upgradeShip(float dmgreduce) {
		for (int i = 0; i < tiles.length ; i += 1) {
			tiles[i] = new BoostedTile(tiles[i], dmgreduce);
		}
	}
}
