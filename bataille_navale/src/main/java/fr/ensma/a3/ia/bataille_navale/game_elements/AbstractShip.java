package fr.ensma.a3.ia.bataille_navale.game_elements;

import java.util.ArrayList;
import java.util.Objects;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public abstract class AbstractShip implements IUnit{
	
	private static ArrayList<String> id_list = new ArrayList<String>();
	private final String id;
	private final int length;
	private final ITile[] tiles;

	public AbstractShip(String id, Map map, int len, Direction dir, Coordinates ref) throws ShipAlreadyExistsException {
		Objects.requireNonNull(ref, "null reference point");
		Objects.requireNonNull(id, "null ship id");
		
		for(String prev_id : AbstractShip.id_list) {
			if(prev_id.equals(id)) {
				// TODO
				throw new ShipAlreadyExistsException();
			}
		}
		
		this.id = id;
		AbstractShip.id_list.add(this.id);
		
		tiles = new ITile[len];
		length = len;
		for (int i = 0; i < length; i++) {
			switch(dir){
			case Horizontal:
				tiles[i] = new Tile((float)length, new Coordinates(ref.getX()+i,ref.getY()));
				break;
			case Vertical:
				tiles[i] = new Tile((float)length, new Coordinates(ref.getX(),ref.getY()+i));
				break;
			}
		}
		map.addShipToMap(this, len, ref, dir);
	}
	
	@Override
	public String getId() {
		return id;
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
	@Override
	public AttackResult attack(Map map, Coordinates target) throws ShipIsDisabledException {
		
		float damage = power();
		
		if(Math.abs(damage)<1e-10) {
			throw new ShipIsDisabledException();
		}
		
		return map.fireAt(target, damage);
	}

	@Override
	public AttackResult takeDamage(float damage, Coordinates tilecoord) {
		int id = 0;
		for(ITile tile : tiles) {
			if(tilecoord.equals(tile.getCoordinates())) {
				break;
			}
			else {
				id += 1;
			}
		}
		return tiles[id].takeDamage(damage);
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
