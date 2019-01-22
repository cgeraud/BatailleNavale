package fr.ensma.a3.ia.bataille_navale.game_elements;
import fr.ensma.a3.ia.bataille_navale.GameMaster.Attacks.AttackResult;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;

public class UnderWaterMine{
	private ITile minetile = null;
	private static float mineresistance = 3.0f;

	private UnderWaterMine() {}
	
	private static class holder{
		private static final UnderWaterMine mine1 = new UnderWaterMine();
		private static final UnderWaterMine mine2 = new UnderWaterMine();
	}
	
	public static UnderWaterMine getMine1(){
		return holder.mine1;
	}
	
	public static void initMine1(Coordinates coos) {
		holder.mine1.minetile = new Tile(mineresistance, coos);
	}
	
	public static UnderWaterMine getMine2() {
		return holder.mine2;
	}
	
	public static void initMine2(Coordinates coos) {
		holder.mine2.minetile = new Tile(mineresistance, coos);
	}
	
	public static boolean isMine1Alive() {
		return holder.mine1.minetile.isAlive();
	}
	
	public static boolean isMine2Alive() {
		return holder.mine2.minetile.isAlive();
	}
	
	public static AttackResult Mine1TakeDamage(float damage) {
		return holder.mine1.minetile.takeDamage(damage);
	}
	
	public static AttackResult Mine2TakeDamage(float damage) {
		return holder.mine2.minetile.takeDamage(damage);
	}
	
	public static ITile getMine1Tile() {
		return holder.mine1.minetile;
	}
	
	public static ITile getMine2Tile() {
		return holder.mine2.minetile;
	}
	
}
