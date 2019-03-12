package fr.ensma.a3.ia.bataille_navale.GUI.pregame.shipbar;

public interface IShipBarObserver {
	void notifyTorpClicked();
	void notifyDestClicked();
	void notifySubClicked();
	void notifyCruiClicked();
	void notifyCVNClicked();
}
