package fr.ensma.a3.ia.bataille_navale.game_elements;

public class Tile {
	
	private float resistance;
	
	public Tile(float resist) {
		resistance = resist;
	}

	public void takeDamage(float damage) {
		resistance -= damage;
		if (resistance < 0.0f) {
			resistance = 0.0f;
		}
	}

	public float getResistance() {
		return resistance;
	}

}
