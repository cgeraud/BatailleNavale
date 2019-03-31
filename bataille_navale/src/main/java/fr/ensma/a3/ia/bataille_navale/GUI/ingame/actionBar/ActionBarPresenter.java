package fr.ensma.a3.ia.bataille_navale.GUI.ingame.actionBar;

import java.util.ArrayList;
import java.util.logging.Logger;

public class ActionBarPresenter{
	
	Logger LOGGER = Logger.getLogger(this.getClass().getName());
	
	private IActionBarView view = null;
	private ActionBarModel model = null;
	private IActionBarObserver observer = null;
	
	public ActionBarPresenter() {
		// TODO Auto-generated constructor stub
	}

	public void setView(IActionBarView view) {
		this.view = view;
	}

	public IActionBarView getView() {
		return this.view;
	}
	
	public void setObserver(IActionBarObserver observer) {
		this.observer = observer;
	}
	
	public void removeObserver() {
		this.observer = null;
	}
	public void AllowActionaccess(ArrayList<EPossibleActions> action, Boolean bol) {
		if(action != null) {
			for(EPossibleActions act : EPossibleActions.values()) {
				this.view.setButtonAccess(act, false);
			}
			for(EPossibleActions act : action) {
				this.view.setButtonAccess(act, true);
			}
		}
		else {
			for(EPossibleActions act : EPossibleActions.values()) {
				this.view.setButtonAccess(act, false);
			}
		}
	}
	

	public void actionSelected(EPossibleActions action) {
		LOGGER.info("Action selected : " + action);
		this.observer.notifyActionClicked(action);
		
	}
	

}
