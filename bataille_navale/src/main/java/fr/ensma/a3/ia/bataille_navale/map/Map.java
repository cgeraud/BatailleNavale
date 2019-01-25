package fr.ensma.a3.ia.bataille_navale.map;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.EFlareResult;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IShellResult;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.UnderWaterMine;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class Map implements IMap{
	
	private static final int Height = 10;
	private static final int Width = 10;
	private ArrayList<IUnit> ships = new ArrayList<IUnit>();
	private UnderWaterMine mine = null;
	private IUnit[][] grid;
	
	public Map() {
		grid = new IUnit[Height][Width];
	}
	
	public int getHeight() {
		return Height;
	}
	
	public int getWidth() {
		return Width;
	}
	
	public void setUWMine(UnderWaterMine mine) {
		this.mine = mine;
	}

	@Override
	public IShellResult fireAt(Coordinates target, float damage) {
		IUnit targetCell = grid[target.getY()][target.getX()];
		IShellResult res = new AttackResult();
		
		if(targetCell != null) {
			res = targetCell.takeDamage(damage, target);
		}
		
		if(mine.isMineAlive()) {
			if(mine.getMineTile().getCoordinates().equals(target)) {
				mine.MineTakeDamage(damage);
				if(!mine.isMineAlive()) {
					for(int i=-2 ; i <= 2 ; i++) {
						for(int j=-2 ; j <= 2 ; j++) {
							Coordinates coord = new Coordinates(target.getX() + i, target.getY() + j);
							if(isOnMap(coord)) {
								this.fireAt(coord, 1.0f);
							}
						}
					}
					for(int i=-1 ; i <= 1 ; i++) {
						for(int j=-1 ; j <= 1 ; j++) {
							Coordinates coord = new Coordinates(target.getX() + i, target.getY() + j);
							if(isOnMap(coord)) {
								this.fireAt(coord, 1.0f);
							}
						}
					}
					this.fireAt(target, 1.0f);
				}
			}
		}
		
		return res;
	}
	
	@Override
	public EFlareResult revealMap(Coordinates target) {
		IUnit targetCell = grid[target.getY()][target.getX()];
		EFlareResult res = EFlareResult.Water;
		if(targetCell != null)
			res = EFlareResult.ShipTile;
		
		return res;
	}

	@Override
	public void updateMap() {
		for(int i = 0; i < Width; i++) {
			for(int j = 0; j < Height; j++) {
				grid[j][i] = null;
			}
		}
		for(IUnit ship : this.ships) {
			ArrayList<Coordinates> coordList = ship.getUnitCoordinates();
			for(Coordinates coord : coordList) {
				grid[coord.getY()][coord.getX()] = ship;
			}
		}
	}
	
	@Override
	public void addShipToMap(IUnit myship) {
		ArrayList<Coordinates> coordList = myship.getUnitCoordinates();
		for(Coordinates coord : coordList) {
			grid[coord.getY()][coord.getX()] = myship;
		}
		this.ships.add(myship);
	}

	@Override
	public IUnit getShipFromId(String id) throws ShipDoesNotExistException {
		IUnit retUnit = null;
		
		for(IUnit unit : this.ships) {
			if(unit.getId().equals(id)) {
				retUnit = unit;
				break;
			}
		}
		if(retUnit == null) {
			throw new ShipDoesNotExistException(id);
		}
		return retUnit;
	}
	
	@Override
	public boolean isOnMap(Coordinates coos) {
		boolean retVal = false;
		if(coos.getX()>=0 && coos.getX()<Width &&
				coos.getY()>=0 && coos.getY()<Height) {
			retVal = true;
		}
		return retVal;
	}

	@Override
	public boolean noCollision(Coordinates coos) {
		boolean ret = true;
		if(this.isOnMap(coos))
			ret = (this.grid[coos.getY()][coos.getX()] == null);
		return ret;
	}
}
