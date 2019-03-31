package fr.ensma.a3.ia.bataille_navale.GUI.ingame.grid.OponentGrid;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GUI.ingame.grid.I_InGameGridObserver;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.PlayGridPresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell.CellPresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.cell.E_ShellEffect;
import fr.ensma.a3.ia.bataille_navale.GUI.playgrid.player.PlayerPlayGridModel;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IShellResult;

public class OponentGridPresenter extends PlayGridPresenter{
	private PlayerPlayGridModel model = null;
	private ArrayList<I_InGameGridObserver> observers = new ArrayList<>();
	

	public OponentGridPresenter() {
		super();
		this.model = null;
	}
	
	public void addObserver(I_InGameGridObserver obs) {
		this.observers.add(obs);
	}
	
	public void remObserver(I_InGameGridObserver obs) {
		this.observers.remove(obs);
	}
	
	public void displayResults(ArrayList<IShellResult> result) {
		for(IShellResult shelres : result) {
			if(shelres.getFireResult() != null) {
				switch(shelres.getFireResult()) {
				case Hit:
					this.getCellPresenter(shelres.getCoordinates()).displayShell(E_ShellEffect.Damaged);
					break;
				case Missed:
					this.getCellPresenter(shelres.getCoordinates()).displayShell(E_ShellEffect.Splash);
					break;
				case ShipSunk:
					this.getCellPresenter(shelres.getCoordinates()).displayShell(E_ShellEffect.Destroyed);
					break;
				case TileDestroyed:
					this.getCellPresenter(shelres.getCoordinates()).displayShell(E_ShellEffect.Destroyed);
					break;
				default:
					break;
				
				}
			}
			else if(shelres.getFlareresult() != null) {
				switch(shelres.getFlareresult()) {
				case ShipTile:
					this.getCellPresenter(shelres.getCoordinates()).displayShipTile(null, 0, null);;
					break;
				case Water:
					this.getCellPresenter(shelres.getCoordinates()).displayEmpty();
					break;
				default:
					break;
				}
				
			}
	
		}
	}

	@Override
	public void cellClicked(CellPresenter cell) {
		for (I_InGameGridObserver obs : this.observers) {
			obs.notifyOponentCellClicked(this.findTile(cell));
		}
	}

	@Override
	public void cellEntered(CellPresenter cell) {
		// TODO Auto-generated method stub
		
	}


}
