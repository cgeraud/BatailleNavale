package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

public interface IShellResult {
	int getCoolDownPenalty();
	EAttackEffect getFireResult();
	String getSunkShipId();
}
