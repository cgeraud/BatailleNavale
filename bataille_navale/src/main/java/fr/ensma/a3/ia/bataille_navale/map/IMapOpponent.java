package fr.ensma.a3.ia.bataille_navale.map;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.EFlareResult;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IShellResult;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public interface IMapOpponent {
	public IShellResult fireAt(Coordinates target, float damage);
	public EFlareResult revealMap(Coordinates target);
	public boolean isOnMap(Coordinates coos);
}
