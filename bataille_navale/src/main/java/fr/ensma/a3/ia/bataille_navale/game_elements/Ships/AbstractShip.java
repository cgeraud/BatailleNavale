package fr.ensma.a3.ia.bataille_navale.game_elements.Ships;

import java.util.ArrayList;
import java.util.Objects;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.game_elements.BoostedTile;
import fr.ensma.a3.ia.bataille_navale.game_elements.ITile;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Tile;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.movements.IMovement;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public abstract class AbstractShip implements IUnit{
	
	private static ArrayList<String> id_list = new ArrayList<String>();
	private final String id;
	private final int length;
	private final ArrayList<ITile> tiles = new ArrayList<ITile>();

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
		
		length = len;
		for (int i = 0; i < length; i++) {
			switch(dir){
			case Horizontal:
				tiles.add(new Tile((float)length, new Coordinates(ref.getX()+i,ref.getY())));
				break;
			case Vertical:
				tiles.add(new Tile((float)length, new Coordinates(ref.getX(),ref.getY()+i)));
				break;
			}
		}
		map.addShipToMap(this);
	}
	
	public ArrayList<Coordinates> getUnitCoordinates() {
		ArrayList<Coordinates> retArray = new ArrayList<Coordinates>();
		for(ITile tile : tiles) {
			retArray.add(tile.getCoordinates());
		}
		return retArray;
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
	public AttackResult attack(Map map, Coordinates target) throws ShipIsDisabledException, ShipCannotAttackException {
		throw new ShipCannotAttackException();
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
		return tiles.get(id).takeDamage(damage);
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
		for (int i = 0; i < tiles.size() ; i += 1) {
			tiles.set(i, new BoostedTile(tiles.get(i), dmgreduce));
		}
	}
	
	@Override
	public AttackResult flare(Map target, Coordinates coos) throws ShipCannotFlareException, ShipIsDisabledException {
		throw new ShipCannotFlareException();
	}
	
	@Override
	public ArrayList<ITile> getTiles(){
		return tiles;
	}

	@Override
	public void move(IMovement movement, int value, Map map) {
		movement.move(this, value, map);
	}
}
