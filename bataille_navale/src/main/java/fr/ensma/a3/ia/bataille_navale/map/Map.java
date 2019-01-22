package fr.ensma.a3.ia.bataille_navale.map;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.UnderWaterMine;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class Map implements IMap{
	
	private static final int Height = 10;
	private static final int Width = 10;
	private ArrayList<IUnit> ships = new ArrayList<IUnit>();
	// TODO: make mine useful
	@SuppressWarnings("unused")
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
	public AttackResult fireAt(Coordinates target, float damage) {
		IUnit targetCell = grid[target.getY()][target.getX()];
		AttackResult res = AttackResult.Missed;
		
		if(targetCell != null) {
			res = targetCell.takeDamage(damage, target);
		}
		
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
}
