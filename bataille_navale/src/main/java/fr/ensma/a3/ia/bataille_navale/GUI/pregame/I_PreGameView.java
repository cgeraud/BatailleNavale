package fr.ensma.a3.ia.bataille_navale.GUI.pregame;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIView;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.I_PlayGridView;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.shipbar.I_ShipBarView;

public interface I_PreGameView extends I_GUIView{
	I_PlayGridView getGridView();
	I_ShipBarView getBarView();
	void setOkButtonAccess(boolean access);
}
