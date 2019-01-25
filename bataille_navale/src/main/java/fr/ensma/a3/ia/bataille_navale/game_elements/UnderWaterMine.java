package fr.ensma.a3.ia.bataille_navale.game_elements;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.map.IMap;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class UnderWaterMine{

	private static final UnderWaterMine mine1 = new UnderWaterMine();
	private static final UnderWaterMine mine2 = new UnderWaterMine();
	
	private ITile minetile = null;
	private static float mineresistance = 3.0f;
	
	private UnderWaterMine() {}
	
	private void initMine(Coordinates coos) {
		minetile = new Tile(mineresistance, coos);
	}
	
	public boolean isMineAlive() {
		return minetile.isAlive();
	}
	
	public  AttackResult MineTakeDamage(float damage, IMap map) {
		AttackResult res = minetile.takeDamage(damage);
		if(!this.isMineAlive())
			this.explode(map);
		return res;
	}
	
	private void explode(IMap map) {
		Coordinates target = new Coordinates(0,0);
		float damage = 0.0f;
		for(int i=-2 ; i <= 2 ; i++) {
			for(int j=-2 ; j <= 2 ; j++) {
				Coordinates coord = new Coordinates(target.getX() + i, target.getY() + j);
				if(i==0 && j==0)
					damage = 3.0f;
				else if(Math.abs(i)<=1 && Math.abs(j)<=1)
					damage = 2.0f;
				else
					damage = 1.0f;

				if(map.isOnMap(coord))
					map.fireAt(coord, damage);
			}
		}
	}

	public ITile getMineTile() {
		return minetile;
	}
	
	public static UnderWaterMine getMine1(){
		return mine1;
	}
	
	public static void initMine1(Coordinates coos) {
		mine1.initMine(coos);
	}
	
	public static UnderWaterMine getMine2() {
		return mine2;
	}
	
	public static void initMine2(Coordinates coos) {
		mine2.initMine(coos);
	}
	
	
	
}
