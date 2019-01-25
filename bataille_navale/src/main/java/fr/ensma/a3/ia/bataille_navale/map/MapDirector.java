package fr.ensma.a3.ia.bataille_navale.map;

public class MapDirector {
	private AbsMapBuilder builder = null;
	
	public void setBuilder(AbsMapBuilder builder) {
		this.builder = builder;
	}
	
	public Map getMap() {
		return builder.getMap();
	}
	
	public void buildMap() {
		builder.createMap();
		builder.setSailShipOnMap();
		builder.setMineOnMap();
	}
}
