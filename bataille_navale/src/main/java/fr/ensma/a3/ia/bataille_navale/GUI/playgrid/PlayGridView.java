package fr.ensma.a3.ia.bataille_navale.GUI.playgrid;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell.CellView;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell.I_CellView;
import javafx.scene.layout.GridPane;

public class PlayGridView extends GridPane implements I_PlayGridView {
	
	private PlayGridPresenter presenter = null;
	
	public PlayGridView(I_GUIPres pres) {
		this.presenter = (PlayGridPresenter) pres;
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				this.add(new CellView(this.presenter.getCellPresenter(i*10+j)), j, i);
			}
		}
	}

	@Override
	public I_CellView getCellView(int index) {
		return (I_CellView) this.getChildren().get(index);
	}
}
