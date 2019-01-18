package fr.ensma.a3.ia.bataille_navale.map;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class Map {
	
	private static final int Height = 10;
	private static final int Width = 10;
	private ArrayList<IUnit> ships = new ArrayList<IUnit>();
	private IUnit[][] grid;
	
	public Map() {
		grid = new IUnit[Height][Width];
	}

	public AttackResult fireAt(Coordinates target, float damage) {
		IUnit targetCell = grid[target.getY()][target.getX()];
		AttackResult res = AttackResult.Missed;
		
		if(targetCell != null) {
			res = targetCell.takeDamage(damage, target);
		}
		
		return res;
	}
	
	// TODO exceptions
	public void addShipToMap(IUnit myship, int length, Coordinates ref, Direction dir) {
		if (ref.getX() >= 0 && ref.getX() < Width && ref.getY() >= 0 && ref.getY() < Height) {
			switch(dir) {
			case Horizontal:
				if(ref.getX()+length < Width) {
					for(int i = ref.getX(); i < ref.getX()+length; i++) {
						grid[ref.getY()][i] = myship;
					}
				}
				break;
			case Vertical:
				if(ref.getY()+length < Height) {
					for(int i = ref.getY(); i < ref.getY()+length; i++) {
						grid[i][ref.getX()] = myship;
					}
				}
				break;
			}
		}
		this.ships.add(myship);
	}
	
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
	
	public boolean isOnMap(Coordinates coos) {
		boolean retVal = false;
		if(coos.getX()>=0 && coos.getX()<Width &&
				coos.getY()>=0 && coos.getY()<Height) {
			retVal = true;
		}
		return retVal;
	}
}
