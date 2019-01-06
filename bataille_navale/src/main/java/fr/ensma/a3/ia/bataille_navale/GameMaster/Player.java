package fr.ensma.a3.ia.bataille_navale.GameMaster;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.BaseAttack;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IAttack;
import fr.ensma.a3.ia.bataille_navale.game_elements.AbstractShip;
import fr.ensma.a3.ia.bataille_navale.game_elements.IOffensif;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class Player implements IPlayable {
	
	private int turnCoolDown = 0;
	private Map playerMap;
	private ArrayList<IOffensif> shipList = new ArrayList<IOffensif>();
	private IAttack attackmode = new BaseAttack();
	
	public Player(Map playerMap) {
		this.playerMap = playerMap;
	}
	
	public void addShip(AbstractShip ship, int len, Coordinates coos, Direction dir) {
		this.shipList.add(ship);
		this.playerMap.addShipToMap(ship, len, coos, dir);
	}
	
	public void setModeAttaque(IAttack mode) {
		this.attackmode = mode;
	}
	
	public Map getMap() {
		return this.playerMap;
	}
	
	public boolean playerIsDead() {
		//TODO
		return false;
	}
	
	public int getTurnCoolDown() {
		return this.turnCoolDown;
	}
	
	public void setTurnCoolDown(int val) {
		this.turnCoolDown = val;
	}
	
	@Override
	public void attack(Player target, Coordinates coos, IOffensif bateau)throws ShipIsDisabledException {
		this.turnCoolDown = attackmode.attack(bateau, target.getMap(), coos);
		
	}
}
