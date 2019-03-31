package fr.ensma.a3.ia.bataille_navale.GUI.ingame.actionBar;

public enum EPossibleActions {
	SimpleAttack(0, 70), CrossAttack(1, 85), Flare(2, 88), Upgrade(3, 90), Translation(4, 95), Rotation(5, 100), PassTurn(6, true);
	
	private int value = 0;
	private boolean alwaysOn = false;
	private int probaLimit = 0;
	
	private EPossibleActions(int value) {
		this.value = value;
	}
	
	private EPossibleActions(int value, boolean isOn) {
		this.value = value;
		this.alwaysOn = isOn;
	}
	
	private EPossibleActions(int value, int proba) {
		this.value = value;
		this.probaLimit = proba;
	}
	
	public int getValue() {
		return this.value;
	}

	public boolean isAlwaysOn() {
		return alwaysOn;
	}

	public void setAlwaysOn(boolean alwaysOn) {
		this.alwaysOn = alwaysOn;
	}

	public int getProbaLimit() {
		return probaLimit;
	}
}
