package fr.ensma.a3.ia.bataille_navale.game_elements.Ships;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IShellResult;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.map.IMapOpponent;
import fr.ensma.a3.ia.bataille_navale.map.IMapPlayer;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;
import fr.ensma.a3.ia.bataille_navale.utils.Shape;

public abstract class BattleShip extends AbstractShip{

	public BattleShip(String id, IMapPlayer map, Shape shape, Direction dir, Coordinates ref, ShipType type) throws ShipAlreadyExistsException, ShipOutOfMapException, ShipsOverlappingException {
		super(id, map, shape, dir, ref, type);
	}

	@Override
	public IShellResult attack(IMapOpponent map, Coordinates target) throws ShipIsDisabledException {
		
		float damage = this.power();
		if(Math.abs(damage)<1e-10) {
			throw new ShipIsDisabledException();
		}
		
		return map.fireAt(target, damage);
	}
	
	@Override
	public boolean isPlayable() {
		return true;
	}
}
