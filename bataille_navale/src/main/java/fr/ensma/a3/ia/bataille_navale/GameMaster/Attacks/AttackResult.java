package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

public class AttackResult implements IShellResult {
	
	private int cooldownpenalty = 0;
	private EAttackEffect fireresult = EAttackEffect.Missed;
	private String sunkshipid = null;
	
	public void setCoolDownPenalty(int penalty) {
		this.cooldownpenalty = penalty;
	}
	
	public void setFireResult(EAttackEffect result) {
		this.fireresult = result;
	}
	
	public void setSunkShipId(String id) {
		this.sunkshipid = id;
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

}
