package fr.ensma.a3.ia.bataille_navale.GUI.pregame.grid;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.PlayGridPresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell.CellPresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.player.PlayerPlayGridModel;
import fr.ensma.a3.ia.bataille_navale.GUI.pregame.EShipTypes;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;
import fr.ensma.a3.ia.bataille_navale.utils.Shape;

public class PreGamePlayGridPresenter extends PlayGridPresenter {
	
	private PlayerPlayGridModel model = null;
	
	private ArrayList<I_PreGameGridObserver> observers = new ArrayList<>();

	public PreGamePlayGridPresenter() {
		super();
		this.model = PlayerPlayGridModel.getPlayerPlayGridModel();
	}
	
	public void updateGrid() {
		
	}
	
	public void addShip(String name, EShipTypes type, Shape shipShape,
			Direction dir, int x, int y) {
		this.model.setShipName(type, name);
		
		int tileId = 0;
		for(Coordinates coord : shipShape.getRelativeTiles()) {
			int index = y*10+x;
			if(dir == Direction.Horizontal) {
				index += coord.getY()*10 + coord.getX();
			} else {
				index += coord.getY() - coord.getX()*10;
			}
			this.getCellPresenter(index).displayShipTile(type, tileId, dir);
			tileId += 1;
		}
	}

	public void placeMockedShip(EShipTypes type, Shape shipShape,
			Direction dir, int x, int y) {

		int tileId = 0;
		for(Coordinates coord : shipShape.getRelativeTiles()) {
			int index = y*10+x;
			if(dir == Direction.Horizontal) {
				index += coord.getY()*10 + coord.getX();
			} else {
				index += coord.getY() - coord.getX()*10;
			}
			this.getCellPresenter(index).mockShipTile(type, tileId, dir, false);//TODO
			tileId += 1;
		}
	}
	
	private int findTile(CellPresenter cell) {
		int k = 0;
		for(k=0;k<100;k++) {
			if(this.getCellPresenter(k)==cell) {
				break;
			}
		}
		return k;
	}
	
	@Override
	public void cellClicked(CellPresenter cell) {
		int index = findTile(cell);
		for(I_PreGameGridObserver obs : observers) {
			obs.notifyTileSelected(index%10, index/10);
		}
	}
	
	@Override
	public void cellEntered(CellPresenter cell) {
		int index = findTile(cell);
		for(I_PreGameGridObserver obs : observers) {
			obs.notifyTileHovered(index%10, index/10);
		}
	}
	
	public void addObserver(I_PreGameGridObserver obs) {
		observers.add(obs);
	}
}
