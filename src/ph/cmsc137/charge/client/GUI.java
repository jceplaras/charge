package ph.cmsc137.charge.client;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ph.cmsc137.charge.Constants;
import ph.cmsc137.charge.server.GameServer;

public class GUI extends JFrame implements Runnable, ActionListener, Constants{
   public GUI() throws Exception{
      socket.setSoTimeout(100);
      
      c = this.getContentPane();
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(new Dimension(820,640));
      this.setLocation(100, 100);
	  setTitle("Charge");
      
      readImages();
      
      userLogin = new UserLogin(this);
      //createUserLogin();
      c.add(userLogin);
      
      this.setResizable(false);
      this.setVisible(true);
      offscreen = createImage(600,600);
   }

   public Image loadImage(String loc) {
	  loc = loc.replaceAll("\\\\", "/");
	  return new ImageIcon(getClass().getResource(loc)).getImage();
	  //return null;
   }

   public void send(String msg){
		try{
			byte[] buf = msg.getBytes();
        	InetAddress address = InetAddress.getByName(serverName);
        	DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4447);
        	socket.send(packet);
        }catch(Exception e){}
		
	}

   public void readImages(){
      map = loadImage("images\\map.png");
      /*
      bow_n_arrow = loadImage("images\\icon\\bow_n_arrow.png");
      crosshair = loadImage("images\\icon\\crosshair.png");
      fist = loadImage("images\\icon\\fist.png");
      flask = loadImage("images\\icon\\flask.png");
      hammer = loadImage("images\\icon\\hammer.png");
      shield = loadImage("images\\icon\\shield.png");
      shoes = loadImage("images\\icon\\shoes.png");
      spear = loadImage("images\\icon\\spear.png");
      sword = loadImage("images\\icon\\sword.png");
      wand = loadImage("images\\icon\\wand.png");
      */

	   /*
	    * LOAD button image icons
	    * 0 - archer
	    * 1 - mage
	    * 2 - spearman
	    * 3 - swordsman
	    * 4 - upgrade archer
	    * 5 - upgrade mage
	    * 6 - upgrade spearman
	    * 7 - upgrade swordsman
	    * 8 - repair base
	    */
	   
	  for(int i=0; i<13; i++){
		  buttonIcon[i] = loadImage("images\\buttons\\"+i+".png");
		  buttonHover[i] = loadImage("images\\buttons\\"+i+"_hover.png");
		  buttonPressed[i] = loadImage("images\\buttons\\"+i+"_pressed.png");
	  }
	  
	  for(int i=0; i<3; i++){
		  openLane[i] = loadImage("images\\laneSelector\\open_"+i+".png");
		  openHoverLane[i] = loadImage("images\\laneSelector\\hover_open_"+i+".png");
		  closeLane[i] = loadImage("images\\laneSelector\\close_"+i+".png");
		  closeHoverLane[i] = loadImage("images\\laneSelector\\hover_close_"+i+".png");
	  }
	  
      for(int i=0; i<4; i++){
         team[i] = new TeamImageContainer(i);
      }
      
      intro = loadImage("images\\intro.gif");
      laneWord = loadImage("images\\icons\\lane.png");
      unitsWord = loadImage("images\\icons\\units.png");
      upgradesWord = loadImage("images\\icons\\upgrades.png");
      
      cNameLabel = loadImage("images\\icons\\nameLabel.png");
      sNameLabel = loadImage("images\\icons\\serverLabel.png");;
      textFieldImage = loadImage("images\\icons\\textfield1.png");;
   }

   @Override
public void run(){
	   /*
	   JLabel connecting = new JLabel("Connected. Waiting for Other Players...");
	   connecting.setForeground(Color.yellow);
	   connecting.setBackground(Color.black);
	   layout.putConstraint(SpringLayout.WEST, connecting, 370, SpringLayout.WEST, userLogin);
	   layout.putConstraint(SpringLayout.NORTH, connecting, 20, SpringLayout.SOUTH, userLogin.getPlay());
	   connecting.setVisible(true);
	   
	   JLabel waitingForOtherPlayers = new JLabel("Loading Images. Please Wait.");
	   waitingForOtherPlayers.setForeground(Color.yellow);
	   waitingForOtherPlayers.setBackground(Color.black);
	   layout.putConstraint(SpringLayout.WEST, waitingForOtherPlayers, 370, SpringLayout.WEST, userLogin);
	   layout.putConstraint(SpringLayout.NORTH, waitingForOtherPlayers, 20, SpringLayout.SOUTH, userLogin.getPlay());
	   waitingForOtherPlayers.setVisible(true);*/
	   
	   for(int i =0;i<4;i++)
	   playersHealth.add(0);
	   
	   while(true){
			
			try{
				Thread.sleep(1);
			}catch(Exception ioe){}
			

			byte[] buf = new byte[256];
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
			
			try{
					socket.receive(packet);
			}catch(Exception ioe){/*lazy exception handling :)*/}
		
			serverData=new String (buf);
			serverData=serverData.trim();
		
         if(!serverData.isEmpty())
         System.out.println(serverData);		   

			if(!connected && serverData.startsWith("PLAYERNUMBER")){
				connected=true;
				String temp[]=serverData.split(" ");
				myPlayerNumber = Integer.parseInt(temp[1]);
				//System.out.println("Connected.");

   //load 
            //readImages();
            	//userLogin.setVisible(false);
            	//userLogin.remove(connecting);
            	//userLogin.add(waitingForOtherPlayers);
            	//userLogin.setVisible(true);

				send("READY "+clientName); //kinoment ko muna kasi walang gann sa GameServer.java
			}
			else if (!connected && !connectSent){
				//System.out.println("Connecting...");
				//userLogin.removeAll();
				//c.remove(userLogin);
				//userLogin.setVisible(false);
				//userLogin.add(connecting);
				//c.add(userLogin);
				//userLogin.setVisible(true);
				
				send("CONNECT "+clientName);
				connectSent=true;
			}
			else if (connected && serverData.startsWith("START")){
				gameStart=true;
			}
			else if (connected && gameStart){
				if(!UIinit) {
					//dito yung laro
					splash.dispose();
					
					containerPanel = new JPanel();
					containerPanel.setSize(800, 600);
					containerPanel.setBackground(Color.decode("#001383"));
					
					userLogin.setVisible(false);
					userLogin.removeAll();
					c.remove(userLogin);
					
					gamePanel = new GamePanel(this);
					ctrlPanel = new ControlPanel(teamColors[myPlayerNumber],this);
					
					containerPanel.add(gamePanel);
					containerPanel.add(ctrlPanel);
					c.add(containerPanel);
					
					//c.add(new JButton("button"));
					this.setVisible(true);
					UIinit=true;
				}
				else {
					if(upgradeCount == 2 && !upgradeDisabled) {
						ctrlPanel.disableUpgradeButton();
						upgradeDisabled = true;
					}
					if(myHealth < 0 && !isDead) {
				    	ctrlPanel.updateMessage("You are dead.");
						ctrlPanel.disableAll();
						isDead = true;
					}
					
					for(int i =0;i<playersHealth.size();i++) {
						if(playersHealth.get(i) < 0 && !isDeadArray[i]) {
							ctrlPanel.disableLane(laneMatch[myPlayerNumber][i]);
							gamePanel.destroyBase(i);
							isDeadArray[i] = true;
							
						}
					}
					
					
					//playerList.indexOf(arg0)
					String temp[]=serverData.split(" ");
					if(temp[0].equals("UPDATE")) {
						gamePanel.addUnit(temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8]);
					}
					else if(temp[0].equals("UPDATE_GOLD")) {
						if(Integer.parseInt(temp[1]) == myPlayerNumber) {
							myGold = Integer.parseInt(temp[2]);
						}
					}
					else if(temp[0].equals("UPDATE_LIFE")) {
						playersHealth.set(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
						
						if(Integer.parseInt(temp[1]) == myPlayerNumber) {
							myHealth = Integer.parseInt(temp[2]);
						}
					}
					else if(temp[0].equals("REPAINT")) {
						if(myHealth > 0) {
						ctrlPanel.updateGold(myGold);
						ctrlPanel.updateHealth(myHealth);
						}
						gamePanel.repaint();
						this.setVisible(true);
					}
					else if(temp[0].equals("WINNER")) {
						int winner=Integer.parseInt(temp[1]);
						if(myPlayerNumber == winner) {
							ctrlPanel.updateMessage("YOURE THE WINNER");
							ctrlPanel.disableAll();
						}
						else
							ctrlPanel.updateMessage("Player "+winner+" Win!");
						
						break;
					}
				}
			}
		}
	}

   /*public void createUserLogin(){
		layout = new SpringLayout();
		userLogin.setSize(800, 600);
		//userLogin.setBackground(Color.black);
		userLogin.setBackground(Color.decode("#001383"));
		userLogin.setLayout(layout);
		
		ipaddr = new JTextField("localhost", 20);
		ipaddr.setEditable(false);
      name = new JTextField("clientname", 20);
      login = new JButton("Join!");
      viewServers = new JButton("Servers");
      createGame = new JButton("CreateGame");
      

      layout.putConstraint(SpringLayout.WEST, ipaddr, 300, SpringLayout.WEST, userLogin);
      layout.putConstraint(SpringLayout.NORTH, ipaddr, 200, SpringLayout.NORTH, userLogin);
      layout.putConstraint(SpringLayout.WEST, name, 300, SpringLayout.WEST, userLogin);
      layout.putConstraint(SpringLayout.NORTH, name, 10, SpringLayout.SOUTH, ipaddr);
      layout.putConstraint(SpringLayout.WEST, login, 370, SpringLayout.WEST, userLogin);
      layout.putConstraint(SpringLayout.NORTH, login, 10, SpringLayout.SOUTH, name);
      //ipaddr.addActionListener(this);
      //name.addActionListener(this);
      login.addActionListener(this);
      viewServers.addActionListener(this);
      createGame.addActionListener(this);
      
      userLogin.add(ipaddr);
      userLogin.add(name);
      userLogin.add(login);
      userLogin.add(viewServers);
      userLogin.add(createGame);
		userLogin.setVisible(true);
   }*/

   public UserLogin getUserLogin() {
	return userLogin;
}

@Override
public void actionPerformed(ActionEvent e){
      //if(e.getSource() == login){
	  if(e.getSource() == userLogin.getPlay()){
         //String s = "IP Address: " + ipaddr.getText() + "\n" + "Client Name: " + name.getText();
         //JOptionPane.showMessageDialog(null, s, "hello", JOptionPane.ERROR_MESSAGE); 
         if(userLogin.getIpaddr().getText().isEmpty()) {
        	 JOptionPane.showMessageDialog(null, "Select Server From Server List.", "hello", JOptionPane.ERROR_MESSAGE); 
         }
         else if(userLogin.getcName().getText().isEmpty()) {
        	 JOptionPane.showMessageDialog(null, "User Name Required", "hello", JOptionPane.ERROR_MESSAGE); 
         }else {
		  serverName = userLogin.getIpaddr().getText();
         clientName = userLogin.getcName().getText();
         userLogin.getPlay().setEnabled(false);
         userLogin.getCreate().setEnabled(false);
         userLogin.getTutorials().setEnabled(false);
         userLogin.getJoin().setEnabled(false);
         splash = new SplashScreen(1,this);
         t.start();}         
      }
      //else if(e.getSource() == createGame){ 
	  else if(e.getSource() == userLogin.getCreate()){
         if(!userLogin.getcName().getText().isEmpty()){ 
			  //String s = "IP Address: " + userLogin.getIpaddr().getText() + "\n" + "Client Name: " + userLogin.getcName().getText();
	          //JOptionPane.showMessageDialog(null, s, "hello", JOptionPane.ERROR_MESSAGE); 
	          serverName = "localhost";
	          clientName = userLogin.getcName().getText();
	          userLogin.getPlay().setEnabled(false);
	          userLogin.getCreate().setEnabled(false);
	          userLogin.getTutorials().setEnabled(false);
	          userLogin.getJoin().setEnabled(false);
	          new GameServer(4, clientName);
	          splash = new SplashScreen(2,this);
	          t.start();  
         }else{
	          JOptionPane.showMessageDialog(null, "User Name Required", "ERROR!", JOptionPane.ERROR_MESSAGE); 
         }
       }
      //else if(e.getSource() == viewServers){
	  else if(e.getSource() == userLogin.getJoin()){
         try {
			slist = new ServerListBox(this);
			userLogin.getJoin().setEnabled(false);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       }
	  else if(e.getSource() == userLogin.getTutorials()){
		  new TutorialBox(this);
		  userLogin.getTutorials().setEnabled(false);
	  }
      else if(e.getSource() == ctrlPanel.getLeft()){
			//JOptionPane.showMessageDialog(null, "Left Gate Open!", "hello", JOptionPane.ERROR_MESSAGE);
			selectedLane = "LEFT";
			
  			ctrlPanel.getLeft().setIcon(new ImageIcon(this.openLane[0]));
  			ctrlPanel.getLeft().setRolloverIcon(new ImageIcon(this.openHoverLane[0]));
			
			ctrlPanel.getMiddle().setIcon(new ImageIcon(this.closeLane[1]));
			ctrlPanel.getMiddle().setRolloverIcon(new ImageIcon(this.closeHoverLane[1]));
			
			ctrlPanel.getRight().setIcon(new ImageIcon(this.closeLane[2]));
			ctrlPanel.getRight().setRolloverIcon(new ImageIcon(this.closeHoverLane[2]));
      }
      else if(e.getSource() == ctrlPanel.getMiddle()){
			//JOptionPane.showMessageDialog(null, "Middle Gate Open!", "hello", JOptionPane.ERROR_MESSAGE); 
			selectedLane = "MIDDLE";

			ctrlPanel.getMiddle().setIcon(new ImageIcon(this.openLane[1]));
			ctrlPanel.getMiddle().setRolloverIcon(new ImageIcon(this.openHoverLane[1]));

  			ctrlPanel.getLeft().setIcon(new ImageIcon(this.closeLane[0]));
  			ctrlPanel.getLeft().setRolloverIcon(new ImageIcon(this.closeHoverLane[0]));
			
			ctrlPanel.getRight().setIcon(new ImageIcon(this.closeLane[2]));
			ctrlPanel.getRight().setRolloverIcon(new ImageIcon(this.closeHoverLane[2]));
      }
      else if(e.getSource() == ctrlPanel.getRight()){
			//JOptionPane.showMessageDialog(null, "Right Gate Open!", "hello", JOptionPane.ERROR_MESSAGE); 
			selectedLane = "RIGHT";
			
			ctrlPanel.getRight().setIcon(new ImageIcon(this.openLane[2]));
			ctrlPanel.getRight().setRolloverIcon(new ImageIcon(this.openHoverLane[2]));

  			ctrlPanel.getLeft().setIcon(new ImageIcon(this.closeLane[0]));
  			ctrlPanel.getLeft().setRolloverIcon(new ImageIcon(this.closeHoverLane[0]));
			
			ctrlPanel.getMiddle().setIcon(new ImageIcon(this.closeLane[1]));
			ctrlPanel.getMiddle().setRolloverIcon(new ImageIcon(this.closeHoverLane[1]));
      }
      else if(e.getSource() == ctrlPanel.getSwordsmanIcon()){
			//JOptionPane.showMessageDialog(null, "Swordsman!", "hello", JOptionPane.ERROR_MESSAGE); 
    	  	if(myGold>UNITCOST){
    	  		send("SUMMON "+clientName+" SWORDSMAN "+ selectedLane);
    	  		ctrlPanel.updateMessage("Summoned a Swordsman");
    	  	}
    	  	else ctrlPanel.updateMessage("Not Enough Gold");
      }
      else if(e.getSource() == ctrlPanel.getSpearmanIcon()){
			//JOptionPane.showMessageDialog(null, "Spearman!", "hello", JOptionPane.ERROR_MESSAGE); 
    	  if(myGold>UNITCOST){
    		  send("SUMMON "+clientName+" SPEARMAN "+ selectedLane);
    		  ctrlPanel.updateMessage("Summoned a Spearman");
    	  }
    	  else ctrlPanel.updateMessage("Not Enough Gold");
      }
      else if(e.getSource() == ctrlPanel.getArcherIcon()){
			//JOptionPane.showMessageDialog(null, "Archer!", "hello", JOptionPane.ERROR_MESSAGE); 
    	  	if(myGold>UNITCOST) {
    	  		send("SUMMON "+clientName+" ARCHER "+ selectedLane);
    	  		ctrlPanel.updateMessage("Summoned a Archer");
    	  	}
      	  else ctrlPanel.updateMessage("Not Enough Gold");
      }
      else if(e.getSource() == ctrlPanel.getMageIcon()){
			//JOptionPane.showMessageDialog(null, "Mage!", "hello", JOptionPane.ERROR_MESSAGE); 
    	  	if(myGold>UNITCOST) {
    	  		send("SUMMON "+clientName+" MAGE "+ selectedLane);
    	  		ctrlPanel.updateMessage("Summoned a Mage");
    	  	}
      	  else ctrlPanel.updateMessage("Not Enough Gold");
      }
      else if(e.getSource() == ctrlPanel.getUpgradeArchers()){
			//JOptionPane.showMessageDialog(null, "Archers Upgraded!", "hello", JOptionPane.ERROR_MESSAGE); 
    	  	if(myGold>UPGRADECOST) {
    	  		send("UPGRADE "+clientName+" ARCHER");
    	  		ctrlPanel.updateMessage("Archer Upgraded");
    	  		upgradeCount++;
    	  	}
      	  else ctrlPanel.updateMessage("Not Enough Gold");
      }
      else if(e.getSource() == ctrlPanel.getUpgradeSpearmen()){
			//JOptionPane.showMessageDialog(null, "Spearmen Upgraded!", "hello", JOptionPane.ERROR_MESSAGE); 
    	  if(myGold>UPGRADECOST){
    		  send("UPGRADE "+clientName+" SPEARMAN");
    		  ctrlPanel.updateMessage("Spearmen upgraded");
    		  upgradeCount++;
    	  }
    	  else ctrlPanel.updateMessage("Not Enough Gold");
      }
      else if(e.getSource() == ctrlPanel.getUpgradeSwordsmen()){
			//JOptionPane.showMessageDialog(null, "Swordsmen Upgraded!", "hello", JOptionPane.ERROR_MESSAGE); 
    	  if(myGold>UPGRADECOST)  {
    		  send("UPGRADE "+clientName+" SWORDSMAN");
    		  ctrlPanel.updateMessage("Swordsmen Upgraded");
    		  upgradeCount++;
    	  }
    	  else ctrlPanel.updateMessage("Not Enough Gold");
      }
      else if(e.getSource() == ctrlPanel.getUpgradeMage()){
			//JOptionPane.showMessageDialog(null, "Mage Upgraded!", "hello", JOptionPane.ERROR_MESSAGE); 
    	  if(myGold>UPGRADECOST) {
    		  send("UPGRADE "+clientName+" MAGE");
    		  ctrlPanel.updateMessage("Mages Upgraded");
    		  upgradeCount++;
    	  }
    	  else ctrlPanel.updateMessage("Not Enough Gold");
      }
      else if(e.getSource() == ctrlPanel.getRepairBase()){
			//JOptionPane.showMessageDialog(null, "Repair Complete!", "hello", JOptionPane.ERROR_MESSAGE); 
    	  if(myGold>UPGRADECOST)  {
    		  send("REPAIR "+clientName);
    		  ctrlPanel.updateMessage("Base Repaired.");
    	  }
    	  else ctrlPanel.updateMessage("Not Enough Gold");
      }
   }

   
	public static void main(String args[]) throws Exception{
		new GUI();
	}

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private static String clientName;	
	private static String serverName;
	
	private boolean gameStart=false;
	private boolean connected=false;
	private boolean connectSent=false;
	private boolean UIinit=false;
	private boolean upgradeDisabled=false;
	private boolean isDead = false;
	private boolean isDeadArray[] = {false, false,false,false };
	
	private static String serverData;
	private DatagramSocket socket = new DatagramSocket();
	private ArrayList<Integer> playersHealth = new ArrayList<Integer>();
	private Thread t=new Thread(this);
		
	
   private Container c;

   private UserLogin userLogin;
   private ControlPanel ctrlPanel;
   private GamePanel gamePanel;
   private JPanel containerPanel;
   
   private SplashScreen splash;
   
   /* 
   public JTextField ipaddr;
   private JTextField name;
   private JButton login; // play
   public JButton viewServers; // join
   public JButton createGame; //create
   */
   public Image map;
   public Image intro;
   public Image laneWord;
   public Image unitsWord;
   public Image upgradesWord;
   
   public Image cNameLabel;
   public Image sNameLabel;
   public Image textFieldImage;
   
   /*
   public Image bow_n_arrow;
   public Image crosshair;
   public Image fist;
   public Image flask;
   public Image hammer;
   public Image shield;
   public Image shoes;
   public Image spear;
   public Image sword;
   public Image wand;
    */
   
   /*
    * 0 - archer
    * 1 - mage
    * 2 - spearman
    * 3 - swordsman
    * 4 - upgrade archer
    * 5 - upgrade mage
    * 6 - upgrade spearman
    * 7 - upgrade swordsman
    * 8 - repair base
    */
   public Image[] buttonIcon = new Image[13];
   public Image[] buttonHover = new Image[13];
   public Image[] buttonPressed = new Image[13];

   public Image[] openLane = new Image[3];
   public Image[] openHoverLane = new Image[3];
   public Image[] closeLane = new Image[3];
   public Image[] closeHoverLane = new Image[3];
   
   public Image offscreen;
   
   public TeamImageContainer[] team = new TeamImageContainer[4];
   
   private String selectedLane = "MIDDLE";
   
   //private SpringLayout layout;
   //private JLabel connecting;
   
   private int myGold=1;
   private int myHealth=1;
   private int myPlayerNumber;
   
   private int upgradeCount=0;
   
   public ServerListBox slist = null;
}
