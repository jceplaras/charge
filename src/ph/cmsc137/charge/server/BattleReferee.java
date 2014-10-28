package ph.cmsc137.charge.server;
import ph.cmsc137.charge.Unit;


public class BattleReferee {

	
	
private Player p1;
private String lane1;
private Player p2;
private String lane2;

public BattleReferee(Player p1, String lane1, Player p2, String lane2) {
	super();
	this.p1 = p1;
	this.lane1 = lane1;
	this.p2 = p2;
	this.lane2 = lane2;
}



public void battleLanes() {
		
		for(Unit unit1: p1.getUnits().get(lane1)) {
			boolean hasAttacked = false;
			
				for(Unit unit2: p2.getUnits().get(lane2)) {
					if(unit1.canAttack(unit2)) {
						if(!unit2.blocked()) unit2.decreaseHealth(unit1.getDamage());
						
						if(unit2.getHealth() < 0) 
							p1.increaseGold(50);
						
						hasAttacked = true;
						break;
					}
				}
				
				if(!hasAttacked && unit1.canAttack(p2.getBase(),lane2))
				{
					p2.getBase().decreaseHealth(unit1.getDamage());
					hasAttacked = true; 
					if(p2.getBase().getHealth() < 0) {
						p1.increaseGold(1000);
					}
				}
			
			
				if(!hasAttacked){
					unit1.move(p1.getPlayerNumber(), lane1);
				}
		}
		
		for(Unit unit2: p2.getUnits().get(lane2)) {
			boolean hasAttacked = false;
			
			
			
				for(Unit unit1: p1.getUnits().get(lane1)) {
					if(unit2.canAttack(unit1)) {
						if(!unit1.blocked()) unit1.decreaseHealth(unit2.getDamage());
						
						if(unit1.getHealth() < 0) 
							p2.increaseGold(50);
						
						hasAttacked = true;
						break;
					}
				}
				
				if(!hasAttacked && unit2.canAttack(p1.getBase(),lane1))
				{
					p1.getBase().decreaseHealth(unit2.getDamage());
					if(p1.getBase().getHealth() < 0) {
						p2.increaseGold(1000);
					}
					hasAttacked = true;
				}
			
			if(!hasAttacked){
				unit2.move(p2.getPlayerNumber(), lane2);
			}
		}
	}
}
