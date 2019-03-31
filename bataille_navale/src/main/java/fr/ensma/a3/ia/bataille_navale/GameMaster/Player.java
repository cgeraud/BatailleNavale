package fr.ensma.a3.ia.bataille_navale.GameMaster;

import java.util.ArrayList;
import java.util.logging.Logger;

import fr.ensma.a3.ia.bataille_navale.GUI.ingame.actionBar.EPossibleActions;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackOutOfMapException;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.BaseAttack;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.CrossAttack;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.FlareLauncher;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IAttack;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IShellResult;
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
import fr.ensma.a3.ia.bataille_navale.movements.IMovement;
import fr.ensma.a3.ia.bataille_navale.movements.Rotation;
import fr.ensma.a3.ia.bataille_navale.movements.Translation;
import fr.ensma.a3.ia.bataille_navale.movements.ZeroMovementException;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class Player implements IPlayer{
	
	Logger logger = Logger.getLogger(Player.class.getName());
	
	private EPossibleActions currentlyselectedaction;
	private IUnit currentlyselectedShip = null;
	private int turnCoolDown = 0;
	private IMapPlayer playerMap = null;
	private IAttack attackmode = new BaseAttack();
	private IMovement currMovement = new Translation();
	
	private int hitMissed = 0;
	
	public Player() {}
	
	@Override
	public void setMap(IMapPlayer map) {
		this.playerMap = map;
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
		if(this.turnCoolDown < 0) {
			this.turnCoolDown = 0;
		}
	}
	
	@Override
	public ArrayList<IShellResult> attack(IPlayer target, Coordinates coos, String idbateau)throws ShipIsDisabledException, ShipDoesNotExistException, AttackOutOfMapException, ShipCannotAttackException, ShipCannotFlareException {
		ArrayList<IShellResult> res = this.attackmode.attack(this.playerMap.getShipFromId(idbateau), target.getMap(), coos);
		System.out.println(res.get(0).getCoolDownPenalty());
		this.setTurnCoolDown(res.get(0).getCoolDownPenalty());
		return res;
	}
	
	@Override
	public void addNewShip(String id, ShipType type, Direction dir, Coordinates ref) throws ShipAlreadyExistsException, ShipOutOfMapException, ShipsOverlappingException, ShipDoesNotExistException {
		ShipFactory.CreateShip(id, type, this.playerMap, dir, ref);
	}
	
	@Override
	public void upgradeShipsResistance(String id, float dmgreduction) throws ShipDoesNotExistException {
		this.playerMap.getShipFromId(id).upgradeShip(dmgreduction);
		this.setTurnCoolDown(1);
	}

	@Override
	public boolean isReady() {
		return (this.playerMap.getShips().size()==6);
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

	@Override
	public boolean canAddNewShip(String id, ShipType type, Direction dir, Coordinates ref) {
		boolean ret = false;
		try {
			IUnit stubShip = ShipFactory.CreateShip(id, type, this.playerMap, dir, ref);
			ret = true;
			this.playerMap.removeShipFromMap(stubShip);
		} catch (ShipAlreadyExistsException | ShipOutOfMapException | ShipsOverlappingException
				| ShipDoesNotExistException e) {
			ret = false;
		} 
		return ret;
	}

	@Override
	public boolean canMoveShip(String id, IMovement movement, Coordinates start, Coordinates end) throws ShipDoesNotExistException {
		return this.playerMap.getShipFromId(id).canMove(movement, start, end, playerMap);
	}

	@Override
	public void moveShip(String id, Coordinates start, Coordinates end) throws ShipOutOfMapException, ShipsOverlappingException, ShipDoesNotExistException {
		this.playerMap.getShipFromId(id).move(this.currMovement, start, end, playerMap);
		this.setTurnCoolDown(1);
	}

	@Override
	public ArrayList<IUnit> getShips() {
		return this.playerMap.getShips();
	}

	@Override
	public IUnit getCurrentlyselectedShip() {
		return currentlyselectedShip;
	}

	@Override
	public void setCurrentlyselectedShip(IUnit currentlyselectedShip) {
		this.currentlyselectedShip = currentlyselectedShip;
		if(currentlyselectedShip != null) {
			logger.info("Player has now selected " + currentlyselectedShip.getId());
		}
		else {
			logger.info("Player selected ship has been reset");
		}
	}

	
	public EPossibleActions getCurrentlySelectedAction() {
		return this.currentlyselectedaction;
	}
	
	@Override
	public void setcurrentlyselectedaction(EPossibleActions action) {
		this.currentlyselectedaction = action;
		if(action != null) {
			logger.info("Player selected: " + action.toString());
			switch(action) {
			case CrossAttack:
				this.setModeAttaque(new CrossAttack());
				break;
			case Flare:
				this.setModeAttaque(new FlareLauncher());
				break;
			case Rotation:
				this.currMovement = new Rotation();
				break;
			case SimpleAttack:
				this.setModeAttaque(new BaseAttack());
				break;
			case Translation:
				this.currMovement = new Translation();
				break;
			case Upgrade:
				break;
			default:
				break;
			
			}
		}
		else {
			logger.info("Player action has been reset");
		}
	}

	@Override
	public int getHitMissed() {
		return hitMissed;
	}

	@Override
	public void setHitMissed(int hitMissed) {
		this.hitMissed = hitMissed;
	}
}
