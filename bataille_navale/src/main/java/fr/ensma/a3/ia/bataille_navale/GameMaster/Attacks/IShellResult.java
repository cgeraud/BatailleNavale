package fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks;

import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public interface IShellResult {
	int getCoolDownPenalty();
	void setCoolDownPenality(int cooldown);
	EAttackEffect getFireResult();
	String getSunkShipId();
	EFlareResult getFlareresult();
	void setFlareresult(EFlareResult flareresult);
	void setFireResult(EAttackEffect result);
	void setCoordinates(Coordinates coord);
	Coordinates getCoordinates();
}
