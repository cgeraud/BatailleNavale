package fr.ensma.a3.ia.bataille_navale.GUI.playgrid;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIView;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell.I_CellView;

public interface I_PlayGridView extends I_GUIView {
	I_CellView getCellView(int index);
}
