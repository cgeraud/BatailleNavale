package fr.ensma.a3.ia.bataille_navale.map;

import java.util.Random;

import fr.ensma.a3.ia.bataille_navale.game_elements.UnderWaterMine;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.SailBoat;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipAlreadyExistsException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipOutOfMapException;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipsOverlappingException;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

public class MapBuilderPlayer1 extends AbsMapBuilder {

	@Override
	public void setMineOnMap() {
		Random rand = new Random();
		int x = rand.nextInt(map.getWidth());
		int y = rand.nextInt(map.getHeight());
		
		UnderWaterMine.initMine1(new Coordinates(x,y));
		UnderWaterMine mine = UnderWaterMine.getMine1();
		
		map.setUWMine(mine);
	}

	@Override
	public void setSailShipOnMap()  {
		Random rand = new Random();
		int x = rand.nextInt(map.getWidth());
		int y = rand.nextInt(map.getHeight());
		
		try {
			new SailBoat("SailBoat1", map, 
					Direction.Horizontal, new Coordinates(x,y), ShipType.SailBoat);
		} catch (ShipAlreadyExistsException e) {
			e.printStackTrace();
		} catch (ShipOutOfMapException e) {
			e.printStackTrace();
		} catch (ShipsOverlappingException e) {
			e.printStackTrace();
		}
	}

}
