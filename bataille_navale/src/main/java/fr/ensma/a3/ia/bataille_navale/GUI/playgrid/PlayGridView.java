package fr.ensma.a3.ia.bataille_navale.GUI.playgrid;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell.CellView;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell.I_CellView;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class PlayGridView extends GridPane implements I_PlayGridView {
	
	private PlayGridPresenter presenter = null;
	
	public PlayGridView(I_GUIPres pres) {
		this.presenter = (PlayGridPresenter) pres;
		this.setAlignment(Pos.CENTER);
		Coordinates coord = new Coordinates(0,0);
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				coord.setX(j);
				coord.setY(i);
				this.add(new CellView(this.presenter.getCellPresenter(coord)), j, i);
			}
		}
	}

	@Override
	public I_CellView getCellView(int index) {
		return (I_CellView) this.getChildren().get(index);
	}
}
