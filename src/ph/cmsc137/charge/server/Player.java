package ph.cmsc137.charge.server;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;

import ph.cmsc137.charge.Base;
import ph.cmsc137.charge.Unit;

public class Player {
	
	private InetAddress address;
	private int port;
	private String name;
	
	private int playerNumber;
	
	private int gold = 500;
	
	private int swordsmanLevel = 0;
	private int spearmanLevel = 0;
	private int archerLevel = 0;
	private int mageLevel = 0;
	
	private HashMap<String, ArrayList<Unit>> units = new HashMap<String, ArrayList<Unit>>();

	private Base base;

	public Player(String name,int playerNumber,InetAddress address, int port){
		this.address = address;
		this.port = port;
		this.name = name;
		this.playerNumber = playerNumber;
		
		units.put("MIDDLE", new ArrayList<Unit>());
		units.put("LEFT", new ArrayList<Unit>());
		units.put("RIGHT", new ArrayList<Unit>());
		
		base = new Base(playerNumber);
	}
	
	
	
	public HashMap<String, ArrayList<Unit>> getUnits() {
		return units;
	}



	public void decreaseGold(int decrease) {
		this.gold-=decrease;
	}
	
	public InetAddress getAddress(){
		return address;
	}

	public int getPort(){
		return port;
	}

	public String getName(){
		return name;
	}


	public int getGold() {
		return gold;
	}

	

	public int getSwordsmanLevel() {
		return swordsmanLevel;
	}



	public int getSpearmanLevel() {
		return spearmanLevel;
	}



	public int getArcherLevel() {
		return archerLevel;
	}



	public int getMageLevel() {
		return mageLevel;
	}



	public Base getBase() {
		return base;
	}

	

	public int getPlayerNumber() {
		return playerNumber;
	}



	public int getUnitLevel(String unitType) {
		
		
		if(unitType.equalsIgnoreCase("SWORDSMAN"))
			return swordsmanLevel;
		else if(unitType.equalsIgnoreCase("SPEARMAN"))
			return spearmanLevel;
		else if(unitType.equalsIgnoreCase("ARCHER"))
			return archerLevel;
		else if(unitType.equalsIgnoreCase("MAGE"))
			return mageLevel;
		return 0;
	}
	
	public void upgrade(String unitType) {
		if(unitType.equalsIgnoreCase("SWORDSMAN"))
			swordsmanLevel++;
		else if(unitType.equalsIgnoreCase("SPEARMAN"))
			spearmanLevel++;
		else if(unitType.equalsIgnoreCase("ARCHER"))
			archerLevel++;
		else if(unitType.equalsIgnoreCase("MAGE"))
			mageLevel++;
	}



	public void increaseGold(int i) {
		// TODO Auto-generated method stub
		gold+=i;
	}
	
}
