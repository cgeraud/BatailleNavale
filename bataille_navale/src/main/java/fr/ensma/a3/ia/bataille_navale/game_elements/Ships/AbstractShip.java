package fr.ensma.a3.ia.bataille_navale.game_elements.Ships;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Logger;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.EAttackEffect;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.EFlareResult;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IShellResult;
import fr.ensma.a3.ia.bataille_navale.game_elements.BoostedTile;
import fr.ensma.a3.ia.bataille_navale.game_elements.ITile;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Tile;
import fr.ensma.a3.ia.bataille_navale.map.IMapOpponent;
import fr.ensma.a3.ia.bataille_navale.map.IMapPlayer;
import fr.ensma.a3.ia.bataille_navale.movements.IMovement;
import fr.ensma.a3.ia.bataille_navale.movements.ZeroMovementException;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;
import fr.ensma.a3.ia.bataille_navale.utils.Shape;



public abstract class AbstractShip implements IUnit{
	
	private final static Logger LOGGER = Logger.getLogger(AbstractShip.class.getName());
	
	private final String id;
	private final ArrayList<ITile> tiles = new ArrayList<ITile>();
	private ShipType type = null;
	private Direction dir = Direction.Horizontal;

	public AbstractShip(String id, IMapPlayer map, Shape shipShape, Direction dir, Coordinates ref, ShipType type) throws ShipAlreadyExistsException, ShipOutOfMapException, ShipsOverlappingException {
		Objects.requireNonNull(ref, "null reference point");
		Objects.requireNonNull(id, "null ship id");
		
		for(IUnit ship : map.getShips()) {
			if(ship.getId().equals(id)) {
				throw new ShipAlreadyExistsException();
			}
		}
		
		this.id = id;
		this.type = type;

		this.dir = dir;
		for (Coordinates coos : shipShape.getRelativeTiles()) {
			Coordinates newCoos = null;
			switch(dir){
			case Horizontal:
				newCoos = new Coordinates(ref.getX() + coos.getX(), ref.getY() + coos.getY());
				break;
			case Vertical:
				newCoos = new Coordinates(ref.getX() + coos.getY(), ref.getY() - coos.getX());
				break;
			}
			if (!map.isOnMap(newCoos))
				throw new ShipOutOfMapException();
			if (!map.noCollision(newCoos, this))
				throw new ShipsOverlappingException();

			tiles.add(new Tile((float)shipShape.getRelativeTiles().size(), newCoos));
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
	public ShipType getType() {
		return this.type;
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
            if(!tile.isDamaged()) {
            	damage += 1.0;
            }
        }
		return damage;
	}

	@Override
	public IShellResult attack(IMapOpponent map, Coordinates target) throws ShipIsDisabledException, ShipCannotAttackException {
		throw new ShipCannotAttackException();
	}

	@Override
	public IShellResult takeDamage(float damage, Coordinates tilecoord) {
		int id = 0;
		for(ITile tile : tiles) {
			if(tilecoord.equals(tile.getCoordinates())) {
				break;
			}
			else {
				id += 1;
			}
		}
		
		AttackResult res = tiles.get(id).takeDamage(damage);
		LOGGER.info(this.getId() + " was hit on tile " + id + ".");
		if(!tiles.get(id).isAlive())
			LOGGER.info(this.getId() + " has tile " + id + " destroyed.");
		if(!this.isAlive()) {
			res.setFireResult(EAttackEffect.ShipSunk);
			res.setSunkShipId(this.id);
			LOGGER.info(this.getId() + " was sunk.");
		}
		
		return res;
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
		LOGGER.info("Ship: " + this.getId()+ " has been upgraded==========================================================" );
		for (int i = 0; i < tiles.size() ; i += 1) {
			tiles.set(i, new BoostedTile(tiles.get(i), dmgreduce));
		}
	}
	
	@Override
	public AttackResult flare(IMapOpponent target, Coordinates coos) throws ShipCannotFlareException, ShipIsDisabledException {
		throw new ShipCannotFlareException();
	}
	
	@Override
	public ArrayList<ITile> getTiles(){
		return tiles;
	}

	@Override
	public void move(IMovement movement, Coordinates start, Coordinates end, IMapPlayer map) throws ShipOutOfMapException, ShipsOverlappingException {
		movement.move(this, start, end, map);
	}
	
	@Override
	public boolean canMove(IMovement movement, Coordinates start, Coordinates end, IMapPlayer map) {
		return movement.canMove(this, start, end, map);
	}
	
	@Override
	public Direction getDirection() {
		return this.dir;
	}
	
	@Override
	public void setDirection(Direction dir) {
		this.dir = dir;
	}
	
	@Override
	public boolean isPlayable() {
		return false;
	}
	
	@Override
	public boolean canFlare() {
		return false;
	}
	
	@Override
	public boolean isMovable() {
		boolean ret = true;
		for(ITile tile : tiles) {
			if(!tile.isAlive())
				ret = false;
		}
		return ret;
	}
}
