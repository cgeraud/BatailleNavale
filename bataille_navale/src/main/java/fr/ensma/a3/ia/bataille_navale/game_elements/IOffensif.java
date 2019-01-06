package fr.ensma.a3.ia.bataille_navale.game_elements;

import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public interface IOffensif{
	public void attack(Map target, Coordinates coos) throws ShipIsDisabledException;
	public float power();
}
