package fr.ensma.a3.ia.bataille_navale.GUI.playgrid.player;

import java.util.ArrayList;

import fr.ensma.a3.ia.bataille_navale.GUI.pregame.EShipTypes;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class PlayerPlayGridModel {
	private ArrayList<String> shipsNames = new ArrayList<>();
	private ArrayList<Coordinates> shipsCoords = new ArrayList<>();
	private static PlayerPlayGridModel instance = null;
	
	private PlayerPlayGridModel() {
		for(int i=0; i<5; i++)
			this.shipsNames.add("");
		
		for(int i=0; i<5; i++)
			this.shipsCoords.add(null);
	}
	
	public static PlayerPlayGridModel getPlayerPlayGridModel() {
		if(instance==null) {
			instance = new PlayerPlayGridModel();
		}
		return instance;
	}

	public ArrayList<String> getShipsNames() {
		return instance.shipsNames;
	}
	
	private int getIntFromType(EShipTypes type) {
		int index = -1;
		switch(type) {
		case CVN:
			index = 4;
			break;
		case Crui:
			index = 3;
			break;
		case Dest:
			index = 2;
			break;
		case Sub:
			index = 1;
			break;
		case Torp:
			index = 0;
			break;
		}
		return index;
	}
	
	public String getShipName(EShipTypes type) {
		return instance.shipsNames.get(this.getIntFromType(type));
	}

	public void setShipName(EShipTypes type, String name) {
		instance.shipsNames.set(this.getIntFromType(type), name);
	}

	public ArrayList<Coordinates> getShipsCoords() {
		return shipsCoords;
	}
	
	public Coordinates getShipCoords(EShipTypes type) {
		return instance.shipsCoords.get(this.getIntFromType(type));
	}

	public void setShipCoords(EShipTypes type, Coordinates coords) {
		instance.shipsCoords.set(this.getIntFromType(type), coords);
	}
}
