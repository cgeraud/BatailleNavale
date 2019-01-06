package fr.ensma.a3.ia.bataille_navale.map;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.game_elements.IAttaquable;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class Map {
	
	private static final int Height = 10;
	private static final int Width = 10;
	private IAttaquable[][] grid;
	
	public Map() {
		grid = new IAttaquable[Height][Width];
	}

	public AttackResult fireAt(Coordinates target, float damage) {
		IAttaquable targetCell = grid[target.getY()][target.getX()];
		AttackResult res = AttackResult.Missed;
		
		// TODO takeDamage must return an AttackResult
		if(targetCell != null) {
			res = targetCell.takeDamage(damage, target);
		}
		
		return res;
	}
	
	// TODO exceptions
	public void addShipToMap(IAttaquable myship, int length, Coordinates ref, Direction dir) {
		if (ref.getX()>0 && ref.getX() <= Width && ref.getY()>0 && ref.getY() <= Height) {
			switch(dir) {
			case Horizontal:
				if(ref.getX()+length <= Width) {
					for(int i = ref.getX(); i < ref.getX()+length; i++) {
						grid[ref.getY()][i] = myship;
					}
				}
			case Vertical:
				if(ref.getY()+length <= Height) {
					for(int i = ref.getY(); i < ref.getY()+length; i++) {
						grid[i][ref.getX()] = myship;
					}
				}
			}
		}
	}

}
