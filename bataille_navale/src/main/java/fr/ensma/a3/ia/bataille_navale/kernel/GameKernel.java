package fr.ensma.a3.ia.bataille_navale.kernel;

import java.util.ArrayList;
import java.util.logging.Logger;

import fr.ensma.a3.ia.bataille_navale.GUI.drawables.DrawableShip;
import fr.ensma.a3.ia.bataille_navale.GUI.ingame.actionBar.EPossibleActions;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell.E_CellContent;
import fr.ensma.a3.ia.bataille_navale.GameMaster.IPlayer;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Player;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackOutOfMapException;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.EAttackEffect;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IShellResult;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotAttackException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotFlareException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipOutOfMapException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipsOverlappingException;
import fr.ensma.a3.ia.bataille_navale.kernel.AiLogic.AiLogic;
import fr.ensma.a3.ia.bataille_navale.kernel.kernel_states.GameInitState;
import fr.ensma.a3.ia.bataille_navale.kernel.kernel_states.IKernelAutomaton;
import fr.ensma.a3.ia.bataille_navale.kernel.kernel_states.IKernelState;
import fr.ensma.a3.ia.bataille_navale.kernel.kernel_states.IllegalKernelTransitionException;
import fr.ensma.a3.ia.bataille_navale.kernel.kernel_states.Player1TurnState;
import fr.ensma.a3.ia.bataille_navale.kernel.kernel_states.Player1WonState;
import fr.ensma.a3.ia.bataille_navale.kernel.kernel_states.Player2TurnState;
import fr.ensma.a3.ia.bataille_navale.kernel.kernel_states.Player2WonState;
import fr.ensma.a3.ia.bataille_navale.kernel.kernel_states.PreGameState;
import fr.ensma.a3.ia.bataille_navale.map.MapBuilderPlayer1;
import fr.ensma.a3.ia.bataille_navale.map.MapBuilderPlayer2;
import fr.ensma.a3.ia.bataille_navale.map.MapDirector;
import fr.ensma.a3.ia.bataille_navale.map.ShipDoesNotExistException;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class GameKernel implements IKernelAutomaton, IKernelState, IGameKernelObserver {
	
	private Logger logger = Logger.getLogger(GameKernel.class.getName());
	
	private static GameKernel kernel = null;
	
	private AiLogic AI = null;
	
	private MapDirector mapDirector = new MapDirector();
	private IPlayer player1 = new Player();
	private IPlayer player2 = new Player();
	private IPlayer currentPlayer = null;
	private IPlayer currentOpponent = null;
	
	private IKernelState initGameState = new GameInitState(this);
	private IKernelState preGameState = new PreGameState(this);
	private IKernelState player1TurnState = new Player1TurnState(this);
	private IKernelState player2TurnState = new Player2TurnState(this);
	private IKernelState player1WonState = new Player1WonState(this);
	private IKernelState player2WonState = new Player2WonState(this);
	private IKernelState currentState = initGameState;
	
	private ArrayList<IGameKernelObserver> observers = new ArrayList<IGameKernelObserver>();
	
	private GameKernel() {
		resetPlayers();
	}
	
	public static GameKernel getGameKernel() {
		if(GameKernel.kernel == null) {
			GameKernel.kernel = new GameKernel();
		}
		return GameKernel.kernel;
	}
	
	@Override
	public void resetPlayers() {
		this.player1 = new Player();
		this.player2 = new Player();
		
		this.mapDirector.setBuilder(new MapBuilderPlayer1());
    	this.mapDirector.buildMap();
		this.player1.setMap(this.mapDirector.getMap());
		
		this.mapDirector.setBuilder(new MapBuilderPlayer2());
    	this.mapDirector.buildMap();
    	this.player2.setMap(this.mapDirector.getMap());
	}
	
	public void setPlayer1(IPlayer player) throws IllegalKernelTransitionException {
		this.player1 = player;
	}
	
	public void setPlayer2(IPlayer player) throws IllegalKernelTransitionException {
		this.player2 = player;
	}
	
	public void setPlayer2asAI() {
		if(this.AI != null) {
			observers.remove(AI);
		}
		this.AI = new AiLogic();
		observers.add(AI);
		logger.info("Ai has been loaded");
	}
	
	@Override
	public IPlayer getPlayer1() {
		return this.player1;
	}
	
	@Override
	public IPlayer getPlayer2() {
		return this.player2;
	}
	
	@Override
	public void setCurrentPlayer(IPlayer player) {
		this.currentPlayer = player;
	}
	
	@Override
	public void setCurrentOpponent(IPlayer player) {
		this.currentOpponent = player;
	}
	
	public IPlayer getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public IPlayer getCurrentOpponent() {
		return this.currentOpponent;
	}
	
	public ArrayList<EPossibleActions> getPossibleActions(String shipName) {
		Boolean shipAlive = false;
		IUnit selectedship = null;
		for (IUnit ship : this.getCurrentPlayer().getShips()) {
			if(ship.getId() == shipName && ship.isAlive()) {
				shipAlive = true;
				selectedship = ship;
	
			}
		}
		if(this.getCurrentPlayer().getTurnCoolDown() > 0 || shipAlive == false) {
			this.getCurrentPlayer().setCurrentlyselectedShip(null);
			return null;
		}
		else {
			this.getCurrentPlayer().setCurrentlyselectedShip(selectedship);
			ArrayList<EPossibleActions> act = new ArrayList<EPossibleActions>();
			if(this.getCurrentPlayer().getCurrentlyselectedShip().isPlayable()) {
				act.add(EPossibleActions.SimpleAttack);
				if(this.getCurrentPlayer().getCurrentlyselectedShip().isMovable()) {
					act.add(EPossibleActions.Translation);
					act.add(EPossibleActions.Rotation);
				}
				if(this.getCurrentPlayer().getCurrentlyselectedShip().canFlare() && this.getCurrentPlayer().getHitMissed() >= 5) {
					act.add(EPossibleActions.Flare);
				}
				if(this.getCurrentOpponent().getHitMissed() >= 5) {
					act.add(EPossibleActions.Upgrade);
				}
				if(this.getCurrentPlayer().getHitMissed() >= 3) {
					act.add(EPossibleActions.CrossAttack);
				}
			}
			return act; 
		}
	}
	
	public void setCurrentAction(EPossibleActions action) {
		if(action == EPossibleActions.PassTurn) {
			try {
				this.getCurrentPlayer().setTurnCoolDown(this.getCurrentPlayer().getTurnCoolDown() - 1);
				this.changePlayer();
			} catch (IllegalKernelTransitionException e) {
				e.printStackTrace();
			}
		}
		else if(action == EPossibleActions.Upgrade) {
			try {
				this.getCurrentPlayer().upgradeShipsResistance(this.getCurrentPlayer().getCurrentlyselectedShip().getId(), 0.5f);
				this.simpleActionSelected();
			} catch (ShipDoesNotExistException e) {
				e.printStackTrace();
			}
		}
		else {
			this.getCurrentPlayer().setcurrentlyselectedaction(action);
		}
	}
	
	public void PlayerMovedShip(Coordinates start, Coordinates end) {
		IUnit curShip = this.getCurrentPlayer().getCurrentlyselectedShip();
		EPossibleActions curAction = this.getCurrentPlayer().getCurrentlySelectedAction();
		if(curShip != null && curAction != null && this.getCurrentPlayer().getTurnCoolDown() == 0) {
			switch(curAction){
			case Rotation:
				try {
					this.currentPlayer.moveShip(curShip.getId(), start, end);
				} catch (ShipOutOfMapException | ShipsOverlappingException | ShipDoesNotExistException e) {
					logger.info("Deplacement impossible");
				}
				break;
			case Translation:
				try {
					this.currentPlayer.moveShip(curShip.getId(), start, end);
				} catch (ShipOutOfMapException | ShipsOverlappingException | ShipDoesNotExistException e) {
					logger.info("Deplacement impossible");
				}
				break;
			default:
				// Do nothing
				break;
			}
		}
		this.simpleActionSelected();
	}
	
	public void PlayerAttackedCoordinates(Coordinates tilecoos) {
		ArrayList<IShellResult> result = new ArrayList<IShellResult>();
		IUnit curShip = this.getCurrentPlayer().getCurrentlyselectedShip();
		EPossibleActions curAction = this.getCurrentPlayer().getCurrentlySelectedAction();
		if(curShip != null && curAction != null && this.getCurrentPlayer().getTurnCoolDown() == 0) {
			switch(curAction){
			case SimpleAttack: 
				try {
					result = this.getCurrentPlayer().attack(this.getCurrentOpponent(), tilecoos, curShip.getId());
					if(result.get(0).getFireResult() == EAttackEffect.Missed) {
						this.getCurrentPlayer().setHitMissed(this.getCurrentPlayer().getHitMissed() + 1);
					} else {
						this.getCurrentPlayer().setHitMissed(0);
					}
				} catch (ShipIsDisabledException | ShipDoesNotExistException | AttackOutOfMapException
						| ShipCannotAttackException | ShipCannotFlareException e) {
					e.printStackTrace();
				}
				break;
			case CrossAttack:
				try {
					result = this.getCurrentPlayer().attack(this.getCurrentOpponent(), tilecoos, curShip.getId());
				} catch (ShipIsDisabledException | ShipDoesNotExistException | AttackOutOfMapException
						| ShipCannotAttackException | ShipCannotFlareException e) {
					e.printStackTrace();
				}
				break;
			case Flare:
				try {
					result = this.getCurrentPlayer().attack(this.getCurrentOpponent(), tilecoos, curShip.getId());
				} catch (ShipIsDisabledException | ShipDoesNotExistException | AttackOutOfMapException
						| ShipCannotAttackException | ShipCannotFlareException e) {
					e.printStackTrace();
				}
				break;
			default:
				// do nothing
				break;
			}
		}
		notifyFireResults(result);
	}
	
	public ArrayList<DrawableShip> getShipList(IPlayer player) {
		ArrayList<DrawableShip> list = new ArrayList<>();
		for(IUnit unit : player.getShips()) {
			DrawableShip newShip = new DrawableShip();
			// initialize ship
			newShip.setName(unit.getId());
			newShip.setOrigin(unit.getUnitCoordinates().get(0));
			newShip.setDirection(unit.getDirection());
			newShip.setType(unit.getType());
			// apply damage to ship
			for(int i = 0; i < unit.getTiles().size(); i++) {
				if(!unit.getTiles().get(i).isAlive()) {
					newShip.getCellStates().set(i, E_CellContent.DestroyedShip);
				} else if(unit.getTiles().get(i).isDamaged()) {
					newShip.getCellStates().set(i, E_CellContent.DamagedShip);
				}
			}
			// add ship to return list
			list.add(newShip);
		}
		return list;
	}
	
	/*
	 * State Management part
	 */
	
	@Override
	public void gameInitialized() throws IllegalKernelTransitionException {
		if(this.player1 != null && this.player2 != null) {
			this.getCurrentState().gameInitialized();
		}
	}

	@Override
	public void startGame() throws IllegalKernelTransitionException {
		if(player1.isReady() && player2.isReady())
			this.getCurrentState().startGame();
	}

	@Override
	public void changePlayer() throws IllegalKernelTransitionException {
		this.getCurrentState().changePlayer();
		
		// Once player has changed, test if new player is still alive
		// AND battle-able
		if(!(this.getCurrentPlayer().playerIsalive()) 
				|| this.getCurrentPlayer().isDisabled()) {
			this.getCurrentState().playerLost();
		}
	}

	@Override
	public void playerLost() throws IllegalKernelTransitionException {
		// In case player abandons
		this.getCurrentState().playerLost();
	}

	@Override
	public void replay() throws IllegalKernelTransitionException {
		// On replay, reset each player's map
		this.mapDirector.setBuilder(new MapBuilderPlayer1());
    	this.mapDirector.buildMap();
		this.player1.setMap(this.mapDirector.getMap());
		this.mapDirector.setBuilder(new MapBuilderPlayer2());
    	this.mapDirector.buildMap();
		this.player2.setMap(this.mapDirector.getMap());
		
		// Then change state
		this.getCurrentState().replay();
	}

	@Override
	public void quit() throws IllegalKernelTransitionException {
		this.getCurrentState().quit();
	}
	
	/*
	 * Automaton part
	 */

	@Override
	public void setCurrentState(IKernelState state) {
		this.currentState = state;
	}

	@Override
	public IKernelState getCurrentState() {
		return this.currentState;
	}
	
	@Override
	public IKernelState getInitGameState() {
		return this.initGameState;
	}

	@Override
	public IKernelState getPreGameState() {
		return this.preGameState;
	}

	@Override
	public IKernelState getPlayer1TurnState() {
		return this.player1TurnState;
	}

	@Override
	public IKernelState getPlayer2TurnState() {
		return this.player2TurnState;
	}

	@Override
	public IKernelState getPlayer1WonState() {
		return this.player1WonState;
	}

	@Override
	public IKernelState getPlayer2WonState() {
		return this.player2WonState;
	}
	
	/*
	 * Observer management part
	 */
	
	public void addObserver(IGameKernelObserver obs) {
		this.observers.add(obs);
	}
	
	public void remObserver(IGameKernelObserver obs) {
		this.observers.remove(obs);
	}

	@Override
	public void notifyPreGame() {
		for(IGameKernelObserver obs : observers)
			obs.notifyPreGame();
	}

	@Override
	public void notifyPlayer1Turn() {
		for(IGameKernelObserver obs : observers)
			obs.notifyPlayer1Turn();
	}

	@Override
	public void notifyPlayer2Turn() {
		for(IGameKernelObserver obs : observers)
			obs.notifyPlayer2Turn();
	}

	@Override
	public void notifyPlayer1Won() {
		for(IGameKernelObserver obs : observers)
			obs.notifyPlayer1Won();
	}

	@Override
	public void notifyPlayer2Won() {
		for(IGameKernelObserver obs : observers)
			obs.notifyPlayer2Won();
	}

	@Override
	public void notifyQuit() {
		for(IGameKernelObserver obs : observers)
			obs.notifyQuit();
	}

	@Override
	public void notifyGameStarted() {
		for(IGameKernelObserver obs : observers)
			obs.notifyGameStarted();
	}

	@Override
	public void notifyFireResults(ArrayList<IShellResult> res) {
		for(IGameKernelObserver obs : observers)
			obs.notifyFireResults(res);
	}

	@Override
	public void simpleActionSelected() {
		for(IGameKernelObserver obs : observers)
			obs.simpleActionSelected();
		
	}
}
