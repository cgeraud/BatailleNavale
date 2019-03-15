package fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIView;

public interface I_CellView extends I_GUIView {
	void setContent(String content);
	void setDeactivated(boolean disable);
}
