package fr.ensma.a3.ia.bataille_navale.GUI.ingame.grid.PlayerGrid;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GUI.drawables.DrawableShip;
import fr.ensma.a3.ia.bataille_navale.GUI.ingame.I_InGameGUIObserver;
import fr.ensma.a3.ia.bataille_navale.GUI.ingame.grid.I_InGameGridObserver;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.PlayGridPresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell.CellPresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell.E_CellContent;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell.E_ShellEffect;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.player.PlayerPlayGridModel;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IShellResult;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.kernel.GameKernel;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class PlayerGridPresenter extends PlayGridPresenter {
	
	private PlayerPlayGridModel model = null;
	private ArrayList<I_InGameGridObserver> observers = new ArrayList<>();

	
	public PlayerGridPresenter() {
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
				if(ship.getCellStates().get(i) == E_CellContent.DamagedShip) {
					this.getCellPresenter(coord).displayTileDamaged();
				} else if (ship.getCellStates().get(i) == E_CellContent.DestroyedShip) {
					this.getCellPresenter(coord).displayTileDestroyed();
				}
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
	
	
	public void addObserver(I_InGameGridObserver obs) {
		this.observers.add(obs);
	}
	
	public void remObserver(I_InGameGridObserver obs) {
		this.observers.remove(obs);
	}

	@Override
	public void cellClicked(CellPresenter cell) {
		DrawableShip selectShip = null;
		for(DrawableShip ship : this.model.getShips()) {
			if(ship.coordinateBelongsToShip(this.findTile(cell))) {
				selectShip = ship;
				this.model.setCurrentlySelectedShip(ship);
			}
		}
		for (I_InGameGridObserver obs : this.observers) {
			obs.notifyPlayerCellClicked(this.findTile(cell), selectShip);
		}
	}

	@Override
	public void cellEntered(CellPresenter cell) {
		// TODO Auto-generated method stub
		
	}

	public void displayResults(ArrayList<IShellResult> res) {
		for(IShellResult shelres : res) {
			if(shelres.getFireResult() != null) {
				switch(shelres.getFireResult()) {
				case Hit:
					this.getCellPresenter(shelres.getCoordinates()).displayTileDamaged();
					break;
				case Missed:
					this.getCellPresenter(shelres.getCoordinates()).displayEmpty();
					break;
				case ShipSunk:
					this.getCellPresenter(shelres.getCoordinates()).displayTileDestroyed();
					break;
				case TileDestroyed:
					this.getCellPresenter(shelres.getCoordinates()).displayTileDestroyed();
					break;
				default:
					break;
				
				}
			}
		}
		this.model.updateModel();
	}

	public DrawableShip getCurrentySelectedShip() {
		return this.model.getCurrentlySelectedShip();
	}
}
