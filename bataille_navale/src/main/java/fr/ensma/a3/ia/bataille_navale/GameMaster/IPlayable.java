package fr.ensma.a3.ia.bataille_navale.GameMaster;

import fr.ensma.a3.ia.bataille_navale.game_elements.IUnit;
import fr.ensma.a3.ia.bataille_navale.game_elements.ShipIsDisabledException;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public interface IPlayable {
	public void attack(Player target, Coordinates coos, IUnit bateau)throws ShipIsDisabledException;
}
