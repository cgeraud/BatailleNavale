package fr.ensma.a3.ia.bataille_navale.GUI.ingame;

import java.util.ArrayList;
import java.util.logging.Logger;

import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIPres;
import fr.ensma.a3.ia.bataille_navale.GUI.I_GUIView;
import fr.ensma.a3.ia.bataille_navale.GUI.drawables.DrawableShip;
import fr.ensma.a3.ia.bataille_navale.GUI.ingame.actionBar.ActionBarPresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.ingame.actionBar.EPossibleActions;
import fr.ensma.a3.ia.bataille_navale.GUI.ingame.actionBar.IActionBarObserver;
import fr.ensma.a3.ia.bataille_navale.GUI.ingame.grid.I_InGameGridObserver;
import fr.ensma.a3.ia.bataille_navale.GUI.ingame.grid.OponentGrid.OponentGridPresenter;
import fr.ensma.a3.ia.bataille_navale.GUI.ingame.grid.PlayerGrid.PlayerGridPresenter;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.IShellResult;
import fr.ensma.a3.ia.bataille_navale.kernel.GameKernel;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class InGamePresenter implements I_GUIPres, I_InGameGridObserver, IActionBarObserver {
	
	
	private Logger logger = Logger.getLogger(InGamePresenter.class.getName());
	private I_InGameView view = null;
	private I_InGameGUIObserver observer = null;
	
	private PlayerGridPresenter playergrid = null;
	private OponentGridPresenter oponentgrid = null;
	private ActionBarPresenter actionbar = null;
	
	public InGamePresenter() {
		this.playergrid = new PlayerGridPresenter();
		this.playergrid.addObserver(this);
		this.oponentgrid = new OponentGridPresenter();
		this.oponentgrid.addObserver(this);
		this.actionbar = new ActionBarPresenter();
		this.actionbar.setObserver(this);
	}
	
	public void addObserver(I_InGameGUIObserver obs) {
		this.observer = obs;
	}
	
	public void remObserver(I_InGameGUIObserver obs) {
		this.observer = null;
	}

	@Override
	public void setView(I_GUIView view) {
		this.view = (I_InGameView) view;
		this.playergrid.setView(this.view.getPlayerView());
		this.playergrid.updateGrid();
		this.oponentgrid.setView(this.view.getOponentView());
		this.actionbar.setView(this.view.getActionBarView());
		this.view.setCoolDownText(this.generateCoolDownText(0));
		this.view.setPlayerMissedText(this.generatePlayerMissedText(0));
		this.view.setOpponentMissedText(this.generateOpponentMissedText(0));
	}

	@Override
	public I_GUIView getView() {
		return this.view;
	}

	public PlayerGridPresenter getPlayerGridPresenter() {
		return this.playergrid;
	}
	
	public OponentGridPresenter getOponentGridPresenter() {
		return this.oponentgrid;
	}

	public ActionBarPresenter getActionBarPresenter() {
		return this.actionbar ;
	}

	@Override
	public void notifyPlayerCellClicked(Coordinates coord, DrawableShip ship) {
		if(GameKernel.getGameKernel().getCurrentPlayer().getCurrentlySelectedAction()==EPossibleActions.Translation ||
				GameKernel.getGameKernel().getCurrentPlayer().getCurrentlySelectedAction()==EPossibleActions.Rotation){
			observer.notifyPlayerClickedPlayersCell(this.playergrid.getCurrentySelectedShip().getOrigin(), coord);
			this.playergrid.updateGrid();
		} else {
			if(ship != null) {
				ArrayList<EPossibleActions> act = observer.notifyPlayerClickedShip(ship.getName());
				this.actionbar.AllowActionaccess(act, true);
			} else {
				this.actionbar.AllowActionaccess(null, false);
				observer.notifyPlayerSelectedAction(null);
				observer.notifyPlayerClickedShip(null);
			}
		}
	}
	
	public void updatePlayerInfo() {
		this.view.setCoolDownText(this.generateCoolDownText(GameKernel.getGameKernel().getCurrentPlayer().getTurnCoolDown()));
		this.view.setPlayerMissedText(this.generatePlayerMissedText(GameKernel.getGameKernel().getCurrentPlayer().getHitMissed()));
		this.view.setOpponentMissedText(this.generateOpponentMissedText(GameKernel.getGameKernel().getCurrentOpponent().getHitMissed()));
	}

	private String generateCoolDownText(int turns) {
		String ret = null;
		if(turns > 0) {
			ret = "Tours de pénalité restants : " + turns;
		} else {
			ret = "C'est à vous !";
		}
		return ret;
	}
	
	private String generateOpponentMissedText(int i) {
		return "Coups consécutifs de l'adversaire dans l'eau : " + i;
	}

	private String generatePlayerMissedText(int i) {
		return "Coups dans l'eau consécutifs : " + i;
	}

	@Override
	public void notifyActionClicked(EPossibleActions action) {
		if(action == EPossibleActions.PassTurn)
			this.actionbar.AllowActionaccess(null, false);
		observer.notifyPlayerSelectedAction(action);
	}

	@Override
	public void notifyOponentCellClicked(Coordinates tilecoos) {
		observer.notifyPlayerClickedOponentCell(tilecoos);
		
	}

	public void actionFinished() {
		this.actionbar.AllowActionaccess(null, false);
		observer.notifyPlayerSelectedAction(null);
		observer.notifyPlayerClickedShip(null);
	}
	
	public void displayPlayersFireResults(ArrayList<IShellResult> res) {
		if(!res.isEmpty()) {
			this.getOponentGridPresenter().displayResults(res);
			this.actionFinished();
		}
	}
	
	public void displayOpponentsFireResults(ArrayList<IShellResult> res) {
		if(!res.isEmpty()) {
			this.getPlayerGridPresenter().displayResults(res);
		}
	}

	public void abandon() {
		this.observer.notifyAbandon();
		
	}
}