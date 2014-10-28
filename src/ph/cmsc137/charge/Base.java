package ph.cmsc137.charge;

public class Base {

	private int health = 10000;
	
	private int locationX;
	private int locationY;
	
	private int[] openingX;
	private int[] openingY;
	
	public Base(int playerNumber) {
		switch(playerNumber) {
			case 0:
				locationX = 0;
				locationY = 0;
				openingX = new int[]{270, 300, 330};
				openingY = new int[]{565, 550, 565};
				//[(285,570), (300,570), (315,570)]
			break;
			case 1:
				locationX = 0;
				locationY = 0;
				openingX = new int[]{565, 550, 565};
				openingY = new int[]{330, 300, 270};
				//[(570,315), (570,300), (570,285)]
			break;
			case 2:
				locationX = 0;
				locationY = 0;
				openingX = new int[]{330, 300, 270};
				openingY = new int[]{35, 50, 35};
			break;
			case 3:
				locationX = 0;
				locationY = 0;
				openingX = new int[]{35, 50, 35};
				openingY = new int[]{270, 300, 330};
				//[(30,285), (30,300), (30,315)]
			break;
		}
		
	}

	public int getHealth() {
		return health;
	}

	public int getLocationX() {
		return locationX;
	}

	public int getLocationY() {
		return locationY;
	}
	
	public void decreaseHealth(int decrease) {
		health-=decrease;
	}
	
	public void increaseHealth(int increase) {
		health=Math.min(10000,health+increase);
	}
	
	public int getInitialX(String lane) {
		
		if(lane.equalsIgnoreCase("LEFT"))
			return openingX[0];
		else if(lane.equalsIgnoreCase("MIDDLE"))
			return openingX[1];
		else if(lane.equalsIgnoreCase("RIGHT"))
			return openingX[2];
		
		return 0;
		
	}
	
	public int getInitialY(String lane) {
		
		if(lane.equalsIgnoreCase("LEFT"))
			return openingY[0];
		else if(lane.equalsIgnoreCase("MIDDLE"))
			return openingY[1];
		else if(lane.equalsIgnoreCase("RIGHT"))
			return openingY[2];
		
		return 0;
		
	}
	
}
