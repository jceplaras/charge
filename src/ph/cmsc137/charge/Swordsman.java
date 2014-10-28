package ph.cmsc137.charge;


public class Swordsman extends Unit{

	public Swordsman(int x, int y, int level) {
		super(x, y);
		range = 10;
		// TODO Auto-generated constructor stub
		speed = 17;
		minattack = 3;
		maxattack = 20;
		
		if(level == 0) {
			health = 100;
			block = 70;
		}
		if(level == 1) {
			health = 150;
			block = 75;			
				}
		if(level == 2) {
			health = 200;
			block = 80;
		}
		
		speedCounter = speed;
	}

	
	
}
