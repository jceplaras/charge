package ph.cmsc137.charge.server;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ph.cmsc137.charge.Unit;


public class GameState{
	private Map<String, Player> players=new HashMap<String, Player>();
	private ArrayList<Player> playerList = new ArrayList<Player>();
	
	private int updateGoldCounter = 10;
	
	public GameState(){}
	
	public void update(String name, Player player){
		players.put(name,player);
		playerList.add(player);
	}
	
	public Map<String, Player> getPlayers(){
		return players;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}
	
	public void updateGame() {
		ArrayList<Player> temp = getPlayerList();
		ArrayList<BattleReferee> battles = new ArrayList<BattleReferee>();
		if(temp.get(0).getBase().getHealth() > 0 && temp.get(2).getBase().getHealth() > 0)
			battles.add( new BattleReferee(temp.get(0),"MIDDLE",temp.get(2),"MIDDLE"));
		
		if(temp.get(1).getBase().getHealth() > 0 && temp.get(3).getBase().getHealth() > 0)
			battles.add( new BattleReferee(temp.get(1),"MIDDLE",temp.get(3),"MIDDLE"));
		
		if(temp.get(0).getBase().getHealth() > 0 && temp.get(3).getBase().getHealth() > 0)
			battles.add( new BattleReferee(temp.get(0),"LEFT",temp.get(3),"RIGHT"));
		
		if(temp.get(0).getBase().getHealth() > 0 && temp.get(1).getBase().getHealth() > 0)
			battles.add( new BattleReferee(temp.get(0),"RIGHT",temp.get(1),"LEFT"));
		
		if(temp.get(2).getBase().getHealth() > 0 && temp.get(1).getBase().getHealth() > 0)
			battles.add( new BattleReferee(temp.get(2),"LEFT",temp.get(1),"RIGHT"));
		
		if(temp.get(2).getBase().getHealth() > 0 && temp.get(3).getBase().getHealth() > 0)
			battles.add( new BattleReferee(temp.get(2),"RIGHT",temp.get(3),"LEFT"));
		
		Collections.shuffle(battles);
		
		for(BattleReferee br: battles) {
			br.battleLanes();
		}
		
		cleanUnits();
		updateGold();
		cleanLanes();
	}
	
	
	
	private void cleanLanes() {
		// TODO Auto-generated method stub
		ArrayList<Unit> temp;
		if(playerList.get(0).getBase().getHealth() <= 0) {
			temp = playerList.get(1).getUnits().get("LEFT");
			playerList.get(1).increaseGold(temp.size()*100);
			temp.clear();
			
			temp = playerList.get(2).getUnits().get("MIDDLE");
			playerList.get(2).increaseGold(temp.size()*100);
			temp.clear();
			
			temp = playerList.get(3).getUnits().get("RIGHT");
			playerList.get(3).increaseGold(temp.size()*100);
			temp.clear();
		}
		if(playerList.get(1).getBase().getHealth() <= 0) {
			temp = playerList.get(0).getUnits().get("RIGHT");
			playerList.get(0).increaseGold(temp.size()*100);
			temp.clear();
			
			temp = playerList.get(2).getUnits().get("LEFT");
			playerList.get(2).increaseGold(temp.size()*100);
			temp.clear();
			
			temp = playerList.get(3).getUnits().get("MIDDLE");
			playerList.get(3).increaseGold(temp.size()*100);
			temp.clear();
		}
		if(playerList.get(2).getBase().getHealth() <= 0) {
			temp = playerList.get(1).getUnits().get("RIGHT");
			playerList.get(1).increaseGold(temp.size()*100);
			temp.clear();
			
			temp = playerList.get(0).getUnits().get("MIDDLE");
			playerList.get(0).increaseGold(temp.size()*100);
			temp.clear();
			
			temp = playerList.get(3).getUnits().get("LEFT");
			playerList.get(3).increaseGold(temp.size()*100);
			temp.clear();
		}
		if(playerList.get(3).getBase().getHealth() <= 0) {
			temp = playerList.get(1).getUnits().get("MIDDLE");
			playerList.get(1).increaseGold(temp.size()*100);
			temp.clear();
			
			temp = playerList.get(2).getUnits().get("RIGHT");
			playerList.get(2).increaseGold(temp.size()*100);
			temp.clear();
			
			temp = playerList.get(0).getUnits().get("LEFT");
			playerList.get(0).increaseGold(temp.size()*100);
			temp.clear();
		}
	}

	private void updateGold() {
		// TODO Auto-generated method stub
		if(updateGoldCounter == 0) {
		for(Player p: getPlayers().values()) {
			if(p.getGold() < 999999) {
			p.increaseGold(10);
			}
		}
			updateGoldCounter = 10;
		}
		else updateGoldCounter--;
	}

	private void cleanUnits() {
		// TODO Auto-generated method stub
		for(Player p: getPlayers().values()) {
			for(int i =0; i< p.getUnits().get("LEFT").size();i++) {
				if(p.getUnits().get("LEFT").get(i).getHealth() <= 0) 
					p.getUnits().get("LEFT").remove(i);
			}
			for(int i =0; i< p.getUnits().get("MIDDLE").size();i++) {
				if(p.getUnits().get("MIDDLE").get(i).getHealth() <= 0) 
					p.getUnits().get("MIDDLE").remove(i);
			}
			for(int i =0; i< p.getUnits().get("RIGHT").size();i++) {
				if(p.getUnits().get("RIGHT").get(i).getHealth() <= 0) 
					p.getUnits().get("RIGHT").remove(i);
			}
		}
	}

	

	public int getAlive() {
		// TODO Auto-generated method stub
		int sum = 0;
		for(Player p: playerList) {
			if(p.getBase().getHealth() > 0) sum++;
		}
		return sum;
	}
	
	public int getWinner() {
		for(int i = 0;i<playerList.size();i++) {
			if(playerList.get(i).getBase().getHealth() > 0) return i;
		}
		return -1;
	}
}
