package fr.ensma.a3.ia.bataille_navale.GUI.pregame.grid;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GUI.drawables.DrawableShip;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.PlayGridPresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell.CellPresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.player.PlayerPlayGridModel;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.kernel.GameKernel;
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
		this.model.updateModel();
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
			this.model.setMockedShipValid(GameKernel.getGameKernel().getPlayer1().canAddNewShip(
					this.model.getMockedShip().getName(), 
					this.model.getMockedShip().getType(), 
					this.model.getMockedShip().getDirection(), 
					this.model.getMockedShip().getOrigin()));
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
	
	public void placeShip() {
		this.model.updateModel();
		this.model.setMockedShip(null);
		
		updateGrid();
	}

	public void placeMockedShip(ShipType type) {
		DrawableShip mockedShip = new DrawableShip();
		mockedShip.setOrigin(new Coordinates(10,10));
		mockedShip.setDirection(Direction.Horizontal);
		mockedShip.setType(type);
		mockedShip.setName("MockedShip");
		this.model.setMockedShip(mockedShip);
		this.model.setMockedShipValid(false);
		
		updateGrid();
	}
	
	public void rotateMockedShip() {
		if(this.model.getMockedShip().getDirection()==Direction.Horizontal) {
			this.model.getMockedShip().setDirection(Direction.Vertical);
		} else {
			this.model.getMockedShip().setDirection(Direction.Horizontal);
		}
		
		updateGrid();
	}
	
	public void removeMockedShip() {
		this.model.setMockedShip(null);
		
		updateGrid();
	}
	
	
	@Override
	public void cellClicked(CellPresenter cell) {
		if(this.model.getMockedShip()!=null) {
			for(I_PreGameGridObserver obs : observers) {
				obs.notifyPlaceShip(this.model.getMockedShip().getType(),
						this.model.getMockedShip().getOrigin(),
						this.model.getMockedShip().getDirection());
			}
		}
	}
	
	@Override
	public void cellEntered(CellPresenter cell) {
		if(this.model.getMockedShip()!=null)
			this.model.getMockedShip().setOrigin(findTile(cell));
		
		updateGrid();
	}
	
	public void addObserver(I_PreGameGridObserver obs) {
		observers.add(obs);
	}
}
