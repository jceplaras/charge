package ph.cmsc137.charge.client;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;


public class GamePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -590894219711617910L;
	private GUI gui;
	private ArrayList<String> data = new ArrayList<String>();
	private boolean isDeadArray[] = { false, false, false, false};
	private Graphics2D painter;
	public GamePanel(GUI g){
		gui=g;
		this.setSize(600, 600);
		this.setPreferredSize(new Dimension(600, 600));
		this.setVisible(true);
		painter = (Graphics2D) gui.offscreen.getGraphics();
		painter.setColor(Color.RED);
	}
	
	public void addUnit(String teamNum, String type, String lane, String x, String y, String health, String level, String isAttacking) {
		String temp = teamNum+" "+type+" "+lane+" "+x+" "+y+" "+health+" "+level+" "+isAttacking;
		data.add(temp);
	}
	
	@Override
	public void paint(Graphics g) {
		painter.clearRect(0, 0, 600, 600);
		painter.drawImage(gui.map, 0,0, gui);
		for(String u: data) {
			String []temp = u.split(" ");
			int teamNum = Integer.parseInt(temp[0]);
			int x = Integer.parseInt(temp[3]);
			int y = Integer.parseInt(temp[4]);
			int level = Integer.parseInt(temp[6]);
			if(temp[7].equalsIgnoreCase("false")) {
				painter.drawImage(gui.team[teamNum].getImageWalk(temp[1], temp[2], level), x-36, y-36,gui);
			}
			else
				painter.drawImage(gui.team[teamNum].getImageAttack(temp[1], temp[2], level), x-36, y-36,gui);
			
			//painter.drawRect(x,y,1,1);
			//painter.drawString(temp[5], x, y+10);
		}
		if(!isDeadArray[0])
		painter.drawImage(gui.team[0].getImageBase(),265,550,gui);
		if(!isDeadArray[1])
		painter.drawImage(gui.team[1].getImageBase(),550,265,gui);
		if(!isDeadArray[2])
		painter.drawImage(gui.team[2].getImageBase(),265,-20,gui);
		if(!isDeadArray[3])
		painter.drawImage(gui.team[3].getImageBase(),-20,265,gui);
		g.drawImage(gui.offscreen, 0, 0, gui);
		data.clear();
	}

	public void destroyBase(int i) {
		// TODO Auto-generated method stub
		isDeadArray[i] = true;
	}
}
