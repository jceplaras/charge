package ph.cmsc137.charge;

import java.util.Random;



public abstract class Unit {
	int speed, health, minattack, maxattack, block;
	int x,y;
	int range;
	
	int speedCounter;
	boolean isAttacking = false;
	
	public Unit(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMinattack() {
		return minattack;
	}

	public void setMinattack(int minattack) {
		this.minattack = minattack;
	}

	public int getMaxattack() {
		return maxattack;
	}

	public void setMaxattack(int maxattack) {
		this.maxattack = maxattack;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void decreaseHealth(int decrease) {
		health-=decrease;
	}
	
	public int getDamage() {
		Random gen = new Random(System.nanoTime());
		
		return gen.nextInt(maxattack - minattack + 1) + minattack;
	}
	
	public void increaseX() {
		x+=10;
	}
	public void decreaseX() {
		x-=10;
	}
	public void increaseY() {
		y+=10;
	}
	public void decreaseY() {
		y-=10;
	}
	
	public boolean canAttack(Unit opponent) {
		//distance formula
		int x2 = (x-opponent.getX());
		x2*=x2;
		int y2 = y-opponent.getY();
		y2*=y2;
		isAttacking = true;
		return (Math.sqrt(x2+y2)<range);
	}
	
	public boolean canAttack(Base b, String lane) {
		//distance formula
		int x2 = (x-b.getInitialX(lane));
		x2*=x2;
		int y2 = y-b.getInitialY(lane);
		y2*=y2;
		isAttacking = true;
		return (Math.sqrt(x2+y2)<range);
	}
	
	public boolean blocked() {
		Random gen = new Random(System.currentTimeMillis());
		
		return (gen.nextInt(100) < block);
	}
	
	public boolean isAttacking() {
		return isAttacking;
	}
	
	
	public void move(int playerNumber,String lane) {
		isAttacking = false;
		if(speedCounter == 0) {
			if(playerNumber == 0) {
				if(lane.equalsIgnoreCase("LEFT"))
					decreaseX();	
				else if(lane.equalsIgnoreCase("RIGHT"))
					increaseX();
				decreaseY();
			}
			else if(playerNumber == 1) {
				if(lane.equalsIgnoreCase("LEFT"))
					increaseY();	
				else if(lane.equalsIgnoreCase("RIGHT"))
					decreaseY();
				decreaseX();
			}
			else if(playerNumber == 2) {
				if(lane.equalsIgnoreCase("LEFT"))
					increaseX();	
				else if(lane.equalsIgnoreCase("RIGHT"))
					decreaseX();
				increaseY();		
			}
			else if(playerNumber == 3) {
				if(lane.equalsIgnoreCase("LEFT"))
					decreaseY();	
				else if(lane.equalsIgnoreCase("RIGHT"))
					increaseY();
				increaseX();
			}
			speedCounter = speed;
		}
		else speedCounter--;
	}
	
	public static Unit createUnit(String unitType, int x, int y, int level) {
		
		if(unitType.equalsIgnoreCase("SWORDSMAN"))
			return new Swordsman(x, y, level);
		else if(unitType.equalsIgnoreCase("SPEARMAN"))
			return new Spearman(x, y, level);
		else if(unitType.equalsIgnoreCase("ARCHER"))
			return new Archer(x, y, level);
		else if(unitType.equalsIgnoreCase("MAGE"))
			return new Mage(x, y, level);
		return null;
		
		
	}
	
	
	
	public String getType() {
		return getClass().getSimpleName().toUpperCase();
	}
}
