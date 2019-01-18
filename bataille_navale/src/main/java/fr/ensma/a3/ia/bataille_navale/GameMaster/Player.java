package fr.ensma.a3.ia.bataille_navale.GameMaster;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackOutOfMapException;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.BaseAttack;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IAttack;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotAttackException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotFlareException;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.map.ShipDoesNotExistException;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class Player{
	
	private int turnCoolDown = 0;
	private Map playerMap;
	private IAttack attackmode = new BaseAttack();
	
	public Player(Map playerMap) {
		this.playerMap = playerMap;
	}
	
	public void setModeAttaque(IAttack mode) {
		this.attackmode = mode;
	}
	
	public Map getMap() {
		return this.playerMap;
	}
	
	public boolean playerIsalive() {
		//TODO
		return false;
	}
	
	public int getTurnCoolDown() {
		return this.turnCoolDown;
	}
	
	public void setTurnCoolDown(int val) {
		this.turnCoolDown = val;
	}
	
	public void attack(Player target, Coordinates coos, String idbateau)throws ShipIsDisabledException, ShipDoesNotExistException, AttackOutOfMapException, ShipCannotAttackException, ShipCannotFlareException {
		this.turnCoolDown = this.attackmode.attack(this.playerMap.getShipFromId(idbateau), target.getMap(), coos);
	}
}
