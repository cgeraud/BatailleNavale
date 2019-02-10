package fr.ensma.a3.ia.bataille_navale.GameMaster;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackOutOfMapException;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.BaseAttack;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IAttack;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipAlreadyExistsException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotAttackException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotFlareException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipFactory;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipOutOfMapException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipsOverlappingException;
import fr.ensma.a3.ia.bataille_navale.map.IMapOpponent;
import fr.ensma.a3.ia.bataille_navale.map.IMapPlayer;
import fr.ensma.a3.ia.bataille_navale.map.ShipDoesNotExistException;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class Player implements IPlayer{
	
	private int turnCoolDown = 0;
	private IMapPlayer playerMap;
	private IAttack attackmode = new BaseAttack();
	
	public Player(IMapPlayer playerMap) {
		this.playerMap = playerMap;
	}
	
	@Override
	public void setModeAttaque(IAttack mode) {
		this.attackmode = mode;
	}
	
	@Override
	public IMapOpponent getMap() {
		return this.playerMap;
	}
	
	@Override
	public boolean playerIsalive() {
		boolean res = false;
		for(IUnit ship : playerMap.getShips()) {
			if(ship.isAlive()) {
				res = true;
				break;
			}
		}
		return res;
	}
	
	@Override
	public int getTurnCoolDown() {
		return this.turnCoolDown;
	}
	
	@Override
	public void setTurnCoolDown(int val) {
		this.turnCoolDown = val;
	}
	
	@Override
	public void attack(IPlayer target, Coordinates coos, String idbateau)throws ShipIsDisabledException, ShipDoesNotExistException, AttackOutOfMapException, ShipCannotAttackException, ShipCannotFlareException {
		this.setTurnCoolDown(this.attackmode.attack(this.playerMap.getShipFromId(idbateau), target.getMap(), coos));
	}
	
	@Override
	public void addNewShip(String id, ShipType type, Direction dir, Coordinates ref) throws ShipAlreadyExistsException, ShipOutOfMapException, ShipsOverlappingException, ShipDoesNotExistException {
		ShipFactory.CreateShip(id, type, this.playerMap, dir, ref);
	}
	
	@Override
	public void upgradeShipsResistance(String id, float dmgreduction) throws ShipDoesNotExistException {
		this.playerMap.getShipFromId(id).upgradeShip(dmgreduction);
	}

	@Override
	public boolean isReady() {
		return (this.playerMap.getShips().size()==5);
	}

	@Override
	public boolean isDisabled() {
		boolean ret = true;
		for(IUnit ship : playerMap.getShips()) {
			if(ship.power()>0.1f)
				ret = false;
		}
		return ret;
	}
}
