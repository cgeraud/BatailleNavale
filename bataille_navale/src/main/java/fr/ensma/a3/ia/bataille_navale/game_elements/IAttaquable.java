package fr.ensma.a3.ia.bataille_navale.game_elements;

import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public interface IAttaquable {
	public void takeDamage(float damage, Coordinates tilecoord);
}
