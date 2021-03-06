package fr.ensma.a3.ia.bataille_navale.GameMaster;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GUI.ingame.actionBar.EPossibleActions;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackOutOfMapException;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IAttack;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IShellResult;
import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipAlreadyExistsException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotAttackException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipCannotFlareException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipOutOfMapException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipsOverlappingException;
import fr.ensma.a3.ia.bataille_navale.map.IMapOpponent;
import fr.ensma.a3.ia.bataille_navale.map.IMapPlayer;
import fr.ensma.a3.ia.bataille_navale.map.ShipDoesNotExistException;
import fr.ensma.a3.ia.bataille_navale.movements.IMovement;
import fr.ensma.a3.ia.bataille_navale.movements.ZeroMovementException;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public interface IPlayer {
	// Player structural methods
	void setMap(IMapPlayer map);
	IMapOpponent getMap();
	ArrayList<IUnit> getShips();
	int getHitMissed();
	void setHitMissed(int hitMissed);
	void setCurrentlyselectedShip(IUnit ship);
	IUnit getCurrentlyselectedShip();
	void setcurrentlyselectedaction(EPossibleActions action);
	EPossibleActions getCurrentlySelectedAction();
	
	// Player state methods
	boolean playerIsalive();
	int getTurnCoolDown();
	void setTurnCoolDown(int val);
	boolean isReady();
	boolean isDisabled();
	
	// Player interaction methods
	void setModeAttaque(IAttack mode);
	ArrayList<IShellResult> attack(IPlayer target, Coordinates coos, String idbateau) throws ShipIsDisabledException, ShipDoesNotExistException, AttackOutOfMapException, ShipCannotAttackException, ShipCannotFlareException;
	void addNewShip(String id, ShipType type, Direction dir, Coordinates ref) throws ShipAlreadyExistsException, ShipOutOfMapException, ShipsOverlappingException, ShipDoesNotExistException;
	boolean canAddNewShip(String id, ShipType type, Direction dir, Coordinates ref);
	void moveShip(String id, Coordinates start, Coordinates end) throws ShipOutOfMapException, ShipsOverlappingException, ShipDoesNotExistException;
	boolean canMoveShip(String id, IMovement movement, Coordinates start, Coordinates end) throws ShipDoesNotExistException;
	void upgradeShipsResistance(String id, float dmgreduction) throws ShipDoesNotExistException;
}
