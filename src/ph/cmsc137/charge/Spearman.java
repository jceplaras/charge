package ph.cmsc137.charge;

public class Spearman extends Unit {

	public Spearman(int x, int y, int level) {
		super(x, y);
		range = 10;
		health = 50;
		minattack = 5;
		maxattack = 10;
		
		if(level == 0) {
			speed =10;
			block=80;
		}
		if(level == 1) {
			speed =5;
			block=85;		
		}
		if(level == 2) {
			speed =0;
			block=90;
		}
		
		speedCounter = speed;
	}

}
