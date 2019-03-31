package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class AttackResult implements IShellResult {
	
	private int cooldownpenalty = 0;
	private EAttackEffect fireresult = null;
	private EFlareResult flareresult = null;
	private String sunkshipid = null;
	private Coordinates coos = null;
	
	@Override
	public void setCoordinates(Coordinates coos) {
		this.coos = coos;
	}
	
	@Override
	public Coordinates getCoordinates() {
		return this.coos;
	}
	
	@Override
	public void setCoolDownPenality(int penalty) {
		this.cooldownpenalty = penalty;
	}
	
	@Override
	public void setFireResult(EAttackEffect result) {
		this.fireresult = result;
	}
	
	public void setSunkShipId(String id) {
		this.sunkshipid = id;
		//TODO multiple ship sunk
	}

	@Override
	public int getCoolDownPenalty() {
		return cooldownpenalty;
	}

	@Override
	public EAttackEffect getFireResult() {
		return fireresult;
	}

	@Override
	public String getSunkShipId() {
		return sunkshipid;
	}

	@Override
	public EFlareResult getFlareresult() {
		return flareresult;
	}

	@Override
	public void setFlareresult(EFlareResult flareresult) {
		this.flareresult = flareresult;
	}

}
