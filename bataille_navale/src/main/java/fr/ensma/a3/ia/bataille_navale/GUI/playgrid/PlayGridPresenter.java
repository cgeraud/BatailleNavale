package fr.ensma.a3.ia.bataille_navale.GUI.playgrid;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIView;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell.CellPresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell.I_CellObserver;

public abstract class PlayGridPresenter implements I_GUIPres, I_CellObserver {
	
	private I_PlayGridView view = null;
	private PlayGridModel model = null;
	
	private ArrayList<CellPresenter> cells = new ArrayList<>();
	
	public PlayGridPresenter(PlayGridModel model) {
		this.model = model;
		for(int i=0; i<100; i++) {
			CellPresenter cPres = new CellPresenter();
			this.cells.add(cPres);
			cPres.addObserver(this);
		}
	}
	
	public CellPresenter getCellPresenter(int index) {
		return this.cells.get(index);
	}

	@Override
	public void setView(I_GUIView view) {
		this.view = (I_PlayGridView) view;
		for(int i=0; i<100; i++) {
			this.cells.get(i).setView(this.view.getCellView(i));
		}
	}

	@Override
	public I_GUIView getView() {
		return view;
	}

}
