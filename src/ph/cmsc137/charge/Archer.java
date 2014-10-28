package ph.cmsc137.charge;

public class Archer extends Unit {

	public Archer(int x, int y, int level) {
		super(x, y);
		range = 50;
		health = 50;
		block=5;
		
		
		if(level == 0) {
			speed =13;
			minattack = 5;
			maxattack = 10;
		}
		if(level == 1) {
			speed =11;
			minattack = 7;
			maxattack = 12;	
		}
		if(level == 2) {
			speed =8;
			minattack = 9;
			maxattack = 15;
		}
		
		speedCounter = speed;
	}

}
