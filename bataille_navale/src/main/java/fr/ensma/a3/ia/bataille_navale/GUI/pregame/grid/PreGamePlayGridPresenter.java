package fr.ensma.a3.ia.bataille_navale.GUI.pregame.grid;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GUI.drawables.DrawableShip;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.PlayGridPresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell.CellPresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.player.PlayerPlayGridModel;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class PreGamePlayGridPresenter extends PlayGridPresenter {
	
	private PlayerPlayGridModel model = null;
	private ArrayList<I_PreGameGridObserver> observers = new ArrayList<>();

	public PreGamePlayGridPresenter() {
		super();
		this.model = PlayerPlayGridModel.getPlayerPlayGridModel();
	}
	
	public void updateGrid() {
		// Clear
		this.clearGrid();
		// Display all ships
		for(DrawableShip ship : this.model.getShips()) {
			int i = 0;
			for(Coordinates coord : ship.getCoordinates().getRelativeTiles()) {
				this.getCellPresenter(coord).displayShipTile(ship.getType(), 
						i, ship.getDirection());
				i += 1;
			}
		}
		// Display mocked ship, if any.
		DrawableShip ship = this.model.getMockedShip();
		if(ship!=null) {
			int i = 0;
			for(Coordinates coord : ship.getCoordinates().getRelativeTiles()) {
				if(coord.getX()<10 && coord.getY()<10 && coord.getX() >= 0 && coord.getY() >= 0) {
					this.getCellPresenter(coord).mockShipTile(ship.getType(), 
							i, ship.getDirection(), this.model.isMockedShipValid());
				}
				i += 1;
			}
		}
	}
	
	public void addShip(String name, ShipType type,
			Direction dir, Coordinates origin) {
		DrawableShip newShip = new DrawableShip();
		newShip.setType(type);
		newShip.setOrigin(origin);
		newShip.setDirection(dir);
		newShip.setName(name);
		this.model.addShip(newShip);
	}

	public void placeMockedShip(DrawableShip mockedShip, boolean valid) {
		this.model.setMockedShip(mockedShip);
		this.model.setMockedShipValid(valid);
	}
	
	public void removeMockedShip() {
		this.model.setMockedShip(null);
	}
	
	private Coordinates findTile(CellPresenter cell) {
		Coordinates coord = new Coordinates(0, 0);
		for(int y=0;y<10;y++) {
			for(int x=0;x<10;x++) {
				coord.setX(x);
				coord.setY(y);
				if(this.getCellPresenter(coord)==cell) {
					return coord;
				}
			}
		}
		return null;
	}
	
	@Override
	public void cellClicked(CellPresenter cell) {
		Coordinates coord = findTile(cell);
		for(I_PreGameGridObserver obs : observers) {
			obs.notifyTileSelected(coord);
		}
	}
	
	@Override
	public void cellEntered(CellPresenter cell) {
		Coordinates coord = findTile(cell);
		for(I_PreGameGridObserver obs : observers) {
			obs.notifyTileHovered(coord);
		}
	}
	
	public void addObserver(I_PreGameGridObserver obs) {
		observers.add(obs);
	}
}
