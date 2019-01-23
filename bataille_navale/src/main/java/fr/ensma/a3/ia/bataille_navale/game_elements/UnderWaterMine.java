package fr.ensma.a3.ia.bataille_navale.game_elements;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
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
	
	public  AttackResult MineTakeDamage(float damage) {
		return minetile.takeDamage(damage);
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
