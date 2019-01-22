package fr.ensma.a3.ia.bataille_navale.map;

abstract public class AbsMapBuilder {
	protected Map map;
	
	public IMap getMap() {
		return map;
	}
	
	public void createMap() {
		map = new Map();
	}
	
	public abstract void setSailShipOnMap();
	public abstract void setMineOnMap();
}
