package fr.ensma.a3.ia.bataille_navale.kernel;

import fr.ensma.a3.ia.bataille_navale.GameMaster.IPlayer;
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

public class GameKernel implements IKernelAutomaton, IKernelState {
	private static GameKernel kernel = null;
	
	private MapDirector mapDirector = new MapDirector();
	IPlayer player1 = null;
	IPlayer player2 = null;
	IPlayer currentPlayer = null;
	IPlayer currentOpponent = null;
	
	IKernelState currentState = new GameInitState(this);
	IKernelState preGameState = new PreGameState(this);
	IKernelState player1TurnState = new Player1TurnState(this);
	IKernelState player2TurnState = new Player2TurnState(this);
	IKernelState player1WonState = new Player1WonState(this);
	IKernelState player2WonState = new Player2WonState(this);
	
	private GameKernel(){}
	
	public static GameKernel getGameKernel() {
		if(GameKernel.kernel == null) {
			GameKernel.kernel = new GameKernel();
		}
		return GameKernel.kernel;
	}
	
	public void setPlayer1(IPlayer player) throws IllegalKernelTransitionException {
		this.player1 = player;
		this.mapDirector.setBuilder(new MapBuilderPlayer1());
    	this.mapDirector.buildMap();
		this.player1.setMap(this.mapDirector.getMap());
		this.gameInitialized();
	}
	
	public void setPlayer2(IPlayer player) throws IllegalKernelTransitionException {
		this.player2 = player;
		this.mapDirector.setBuilder(new MapBuilderPlayer2());
    	this.mapDirector.buildMap();
    	this.player2.setMap(this.mapDirector.getMap());
		this.gameInitialized();
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
		if(!this.getCurrentPlayer().playerIsalive() 
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
}
