package fr.ensma.a3.ia.bataille_navale.GUI.ingame;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIView;
import fr.ensma.a3.ia.bataille_navale.GUI.ingame.actionBar.IActionBarView;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.I_PlayGridView;

public interface I_InGameView extends I_GUIView {
	I_PlayGridView getPlayerView();
	I_PlayGridView getOponentView();
	IActionBarView getActionBarView();
	void setCoolDownText(String text);
	void setPlayerMissedText(String text);
	void setOpponentMissedText(String text);
}
