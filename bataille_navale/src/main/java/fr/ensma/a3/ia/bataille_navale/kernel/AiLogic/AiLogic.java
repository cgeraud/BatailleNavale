package fr.ensma.a3.ia.bataille_navale.kernel.AiLogic;

import java.util.ArrayList;
import java.util.logging.Logger;

import fr.ensma.a3.ia.bataille_navale.GUI.ingame.actionBar.EPossibleActions;
import fr.ensma.a3.ia.bataille_navale.GameMaster.IPlayer;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackOutOfMapException;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IShellResult;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipAlreadyExistsException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotAttackException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotFlareException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipOutOfMapException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipsOverlappingException;
import fr.ensma.a3.ia.bataille_navale.kernel.GameKernel;
import fr.ensma.a3.ia.bataille_navale.kernel.IGameKernelObserver;
import fr.ensma.a3.ia.bataille_navale.map.ShipDoesNotExistException;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class AiLogic implements IGameKernelObserver{
	
	private IPlayer AiPlayer = null;
	
	private Logger logger = Logger.getLogger(AiLogic.class.getName());
	
	public AiLogic() {
		this.AiPlayer = GameKernel.getGameKernel().getPlayer2();
	}
	
	private Direction helperRandomDirection() {
		return Direction.values()[(int)(Math.random() * Direction.values().length)];
	}
	
	private Coordinates helperRandomCoordinates() {
		return new Coordinates((int)(Math.random() * 10), (int)(Math.random() * 10));
	}
	
	private EPossibleActions helperRandomAction(ArrayList<EPossibleActions> actions) {
		EPossibleActions retact = null;
		int actionproba = (int)(Math.random() * 100);
		for(EPossibleActions act : actions) {
			if(actionproba < act.getProbaLimit()) {
				retact =  act;
				break;
			}
		}
		return retact;
	}
	
	private String helperRandomShipName() {
		int index = 0;
		IUnit ship = null;
		do {
			index = (int)(Math.random() * GameKernel.getGameKernel().getCurrentPlayer().getShips().size());
			ship = GameKernel.getGameKernel().getCurrentPlayer().getShips().get(index);
		} while(!ship.isPlayable() || !ship.isAlive());
		return ship.getId();
	}

	@Override
	public void notifyPreGame() {
		Coordinates placementcoord = null;
		Direction placementdir = null;
		String placementname = null;
		for (ShipType ship : ShipType.values()) {
			if(ship != ShipType.SailBoat) {
				do {
					placementcoord = helperRandomCoordinates();
					placementdir = helperRandomDirection();
					placementname = "AI_" + ship.toString();
				} while(! AiPlayer.canAddNewShip(placementname, ship, placementdir, placementcoord));
				try {
					AiPlayer.addNewShip(placementname, ship, placementdir, placementcoord);
					logger.info("Ai is placing it's " + ship.toString() +  " at " + placementcoord.toString() + " in the direction: " + placementdir.toString());
				} catch (ShipAlreadyExistsException | ShipOutOfMapException | ShipsOverlappingException
						| ShipDoesNotExistException e) {
					e.printStackTrace();
					logger.severe("Ai tried to place a ship where it shouldnt please investigate");
				}
			}
		}
	}
	

	@Override
	public void notifyGameStarted() {
		// do nothing
		
	}

	@Override
	public void notifyPlayer1Turn() {
		// do nothing
		
	}

	@Override
	public void notifyPlayer2Turn() {
		if(GameKernel.getGameKernel().getCurrentPlayer().getTurnCoolDown() == 0 &&
				!GameKernel.getGameKernel().getCurrentPlayer().isDisabled()) {
			String shipId = null;
			EPossibleActions action = null;
			while(action == null) {
				shipId = this.helperRandomShipName();
				action = this.helperRandomAction(GameKernel.getGameKernel().getPossibleActions(shipId));
			}
			GameKernel.getGameKernel().setCurrentAction(action);
			switch(action) {
			case CrossAttack:
				GameKernel.getGameKernel().PlayerAttackedCoordinates(this.helperRandomCoordinates());
				break;
			case Flare:
				GameKernel.getGameKernel().PlayerAttackedCoordinates(this.helperRandomCoordinates());
				break;
			case Rotation:
				GameKernel.getGameKernel().PlayerMovedShip(GameKernel.getGameKernel().getCurrentPlayer().getCurrentlyselectedShip().getUnitCoordinates().get(0), this.helperRandomCoordinates());
				break;
			case SimpleAttack:
					GameKernel.getGameKernel().PlayerAttackedCoordinates(this.helperRandomCoordinates());
				break;
			case Translation:
				GameKernel.getGameKernel().PlayerMovedShip(GameKernel.getGameKernel().getCurrentPlayer().getCurrentlyselectedShip().getUnitCoordinates().get(0), this.helperRandomCoordinates());
				break;
			default:
				break;
			}
		}
		GameKernel.getGameKernel().setCurrentAction(EPossibleActions.PassTurn);
	}

	@Override
	public void notifyPlayer1Won() {
		// TODO loose
		
	}

	@Override
	public void notifyPlayer2Won() {
		// TODO win
		
	}

	@Override
	public void notifyQuit() {
		// do nothing
		
	}

	@Override
	public void notifyFireResults(ArrayList<IShellResult> res) {
		// Do nothing
	}

	@Override
	public void simpleActionSelected() {
		// TODO Auto-generated method stub
		
	}

}
