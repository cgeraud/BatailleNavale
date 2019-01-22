package fr.ensma.a3.ia.bataille_navale.map;

import java.util.Random;

import fr.ensma.a3.ia.bataille_navale.game_elements.UnderWaterMine;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class MapBuilderPlayer2 extends AbsMapBuilder {

	@Override
	public void setMineOnMap() {
		Random rand = new Random();
		int x = rand.nextInt(map.getWidth());
		int y = rand.nextInt(map.getHeight());
		
		UnderWaterMine.initMine2(new Coordinates(x,y));
		UnderWaterMine mine = UnderWaterMine.getMine2();
		
		map.setUWMine(mine);
	}

}
