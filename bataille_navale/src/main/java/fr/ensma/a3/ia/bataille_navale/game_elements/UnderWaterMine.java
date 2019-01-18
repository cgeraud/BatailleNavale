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
	
	public UnderWaterMine getMine1(){
		return holder.mine1;
	}
	
	public void initMine1(Coordinates coos) {
		holder.mine1.minetile = new Tile(mineresistance, coos);
	}
	
	public UnderWaterMine getMine2() {
		return holder.mine2;
	}
	
	public void initMine2(Coordinates coos) {
		holder.mine2.minetile = new Tile(mineresistance, coos);
	}
	
	public boolean isMine1Alive() {
		return holder.mine1.minetile.isAlive();
	}
	
	public boolean isMine2Alive() {
		return holder.mine2.minetile.isAlive();
	}
	
	public AttackResult Mine1TakeDamage(float damage) {
		return holder.mine1.minetile.takeDamage(damage);
	}
	
	public AttackResult Mine2TakeDamage(float damage) {
		return holder.mine2.minetile.takeDamage(damage);
	}
	
	public ITile getMine1Tile() {
		return holder.mine1.minetile;
	}
	
	public ITile getMine2Tile() {
		return holder.mine2.minetile;
	}
	
}
