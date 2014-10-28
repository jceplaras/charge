package ph.cmsc137.charge;

public class Mage extends Unit {

	public Mage(int x, int y, int level) {
		super(x, y);
		range = 50;
		speed =13;
		block=5;
		
		
		if(level == 0) {
			health = 30;
			minattack = 7;
			maxattack = 10;
		}
		if(level == 1) {
			health = 50;
			minattack = 10;
			maxattack = 15;	
		}
		if(level == 2) {
			health = 80;
			minattack = 15;
			maxattack = 20;
		}
		
		speedCounter = speed;
	}

}
