package ph.cmsc137.charge.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import ph.cmsc137.charge.Constants;
import ph.cmsc137.charge.Unit;



public class GameServer implements Runnable, Constants{
	/**
	 * Placeholder for the data received from the player
	 */	 
	String playerData;
	String serverName;
	
	int playerCount=0;
	int readyCount=0;
	
	boolean endGame = false;
	
    DatagramSocket serverSocket = null;
	GameState game;

	int gameStage=WAITING_FOR_PLAYERS;
	int numPlayers;
	
	Thread t = new Thread(this);
	
	
	
	public GameServer(int numPlayers, String serverName){
		this.serverName = serverName;
		this.numPlayers = numPlayers;
		try {
            serverSocket = new DatagramSocket(PORT);
			serverSocket.setSoTimeout(100);
		} catch (IOException e) {
			    System.err.println("Could not listen on port: "+PORT);
			    System.exit(-1);
		}catch(Exception e){}
		
		game = new GameState();
		
		System.out.println("Game created...");
		
		t.start();
	}
	
	/**
	 * Helper method for broadcasting data to all players
	 * @param msg
	 */
	public void broadcast(String msg){
		for(Player player: game.getPlayers().values()){			
			send(player,msg);	
		}
	}
	/**
	 * Send a message to a player
	 * @param player
	 * @param msg
	 */
	public void send(Player player, String msg){
		DatagramPacket packet;	
		byte buf[] = msg.getBytes();		
		packet = new DatagramPacket(buf, buf.length, player.getAddress(),player.getPort());
		try{
			serverSocket.send(packet);
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	
	public void broadcastUpdates() {
		
		
		for(Player p: game.getPlayers().values()) {
			for(Unit u : p.getUnits().get("LEFT")) {
				broadcast("UPDATE "+p.getPlayerNumber()+" "+u.getType()+" LEFT "+u.getX()+" "+u.getY()+" "+u.getHealth()+" "+p.getUnitLevel(u.getType())+" "+u.isAttacking());
			}
			for(Unit u : p.getUnits().get("MIDDLE")) {
				broadcast("UPDATE "+p.getPlayerNumber()+" "+u.getType()+" MIDDLE "+u.getX()+" "+u.getY()+" "+u.getHealth()+" "+p.getUnitLevel(u.getType())+" "+u.isAttacking());
			}
			for(Unit u : p.getUnits().get("RIGHT")) {
				broadcast("UPDATE "+p.getPlayerNumber()+" "+u.getType()+" RIGHT "+u.getX()+" "+u.getY()+" "+u.getHealth()+" "+p.getUnitLevel(u.getType())+" "+u.isAttacking());
			}
			broadcast("UPDATE_GOLD "+p.getPlayerNumber()+" "+p.getGold());
			broadcast("UPDATE_LIFE "+p.getPlayerNumber()+" "+p.getBase().getHealth());
			
		}
		broadcast("REPAINT");
	}
	
	@Override
	public void run(){
		while(!endGame){
						
			// Get the data from players
			byte[] buf = new byte[256];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			try{
     			serverSocket.receive(packet);
			}catch(Exception ioe){}
			
			/**
			 * Convert the array of bytes to string
			 */
			playerData=new String(buf);
			
			//remove excess bytes
			playerData = playerData.trim();
			//if (!playerData.equals("")){
			//	System.out.println("Player Data:"+playerData);
			//}
		
			// process
			switch(gameStage){
				  case WAITING_FOR_PLAYERS:
						//System.out.println("Game State: Waiting for players...");
					  	
					//System.out.println("Game State: Waiting for players...");
						byte[] multiBuf = new byte[256];
						String dString="CHARGESERVEREXISTS "+ serverName;
						multiBuf= dString.getBytes();
						
						 // send it
						InetAddress group;
						try{
						group = InetAddress.getByName("230.0.0.1");		
		                DatagramPacket multiPacket = new DatagramPacket(multiBuf, multiBuf.length, group, 4446);
		                serverSocket.send(multiPacket);
						}
						catch(Exception e){}
					  
						if (playerCount==numPlayers){
								String msg = "PLAYERNUMBER ";
								int i=0;
								for(Player player: game.getPlayerList()){			
										send(player, msg+i);
										i++;
								}
								gameStage=WAITING_FOR_READY;
							}
						else if (playerData.startsWith("CONNECT")){
							String tokens[] = playerData.split(" ");
							Player player=new Player(tokens[1],playerCount,packet.getAddress(),packet.getPort());
							System.out.println("Player connected: "+tokens[1]);
							game.update(tokens[1].trim(),player);
							playerCount++;
						}
					  break;
					  
			          case WAITING_FOR_READY:
			          		if (readyCount==numPlayers){
			          			gameStage=GAME_START;
			          		}
			          		else if (playerData.startsWith("READY")){
								String tokens[] = playerData.split(" ");
								
								System.out.println("Player ready: "+tokens[1]);
							
								readyCount++;
			          		}
			          	  break;	
				  
			          case GAME_START:
						  System.out.println("Game State: START");
						  broadcast("START");
						  gameStage=IN_PROGRESS;
					  break;
				  
			          case IN_PROGRESS:
			        	  if(game.getAlive() == 1) {
			        		  endGame = true;
			        		  break;
			        	  }
						  if(!playerData.isEmpty()) {
						  	if (playerData.startsWith("SUMMON")) {
						  		String tokens[] = playerData.split(" ");
						  		Player p = game.getPlayers().get(tokens[1]);
						  		p.getUnits().get(tokens[3]).add(Unit.createUnit(tokens[2], p.getBase().getInitialX(tokens[3]),p.getBase().getInitialY(tokens[3]), p.getUnitLevel(tokens[2])));
						  		p.decreaseGold(UNITCOST);
						  	}
						  	else if (playerData.startsWith("UPGRADE")) {
						  		String tokens[] = playerData.split(" ");
						  		Player p = game.getPlayers().get(tokens[1]);
						  		p.upgrade(tokens[2]);
						  		p.decreaseGold(UPGRADECOST);
						  	}
						  	else if (playerData.startsWith("REPAIR")) {
						  		String tokens[] = playerData.split(" ");
						  		Player p = game.getPlayers().get(tokens[1]);
						  		p.getBase().increaseHealth(1000);
						  		p.decreaseGold(REPAIRCOST);
						  	}
						  	
						  }
						 //UPDATE FUNCTION!
				
					game.updateGame();
						  broadcastUpdates();
					  break;
			}				  
		}
		broadcast("WINNER "+game.getWinner());
	}	
	
	//
	
	public static void main(String [] args){
		
		new GameServer(Integer.parseInt(args[1]),args[2]);
	}
}

