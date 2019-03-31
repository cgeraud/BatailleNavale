package fr.ensma.a3.ia.bataille_navale.GUI;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IShellResult;

public interface I_GUIPres {
	void setView(I_GUIView view);
	I_GUIView getView();
}
