package ph.cmsc137.charge.client;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;


public class ControlPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9016324100563543906L;

	public ControlPanel(Color teamColor, GUI g){
		gui = g;
		this.setSize(200, 600);
		this.setPreferredSize(new Dimension(200, 600));
		this.setBackground(Color.decode("#001383"));
		
		layout = new SpringLayout();
		this.setLayout(layout);
		
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
		
		JLabel lane = new JLabel(new ImageIcon(gui.laneWord));
		//layout.putConstraint(SpringLayout.NORTH, lane, 20, SpringLayout.NORTH, this);
		this.add(lane);
		
		//lane = new ButtonGroup();
		
		//left = new JRadioButton("LEFT");
		left = new JButton();
		left.setBorderPainted(false);
		left.setContentAreaFilled(false);
		left.setFocusPainted(false);
		left.setIcon(new ImageIcon(gui.closeLane[0]));
		left.setRolloverIcon(new ImageIcon(gui.closeHoverLane[0]));
		//left.setPressedIcon(new ImageIcon(gui.buttonPressed[0]));
		left.addActionListener(gui);
		layout.putConstraint(SpringLayout.EAST, left, 87, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, left, 30, SpringLayout.NORTH, this);
		this.add(left);
		
		//middle = new JRadioButton("middle");
		middle = new JButton();
		middle.setBorderPainted(false);
		middle.setContentAreaFilled(false);
		middle.setFocusPainted(false);
		middle.setIcon(new ImageIcon(gui.openLane[1]));
		middle.setRolloverIcon(new ImageIcon(gui.openHoverLane[1]));
		//left.setPressedIcon(new ImageIcon(gui.buttonPressed[0]));
		middle.addActionListener(gui);
		layout.putConstraint(SpringLayout.WEST, middle, 65, SpringLayout.WEST, left);
		layout.putConstraint(SpringLayout.NORTH, middle, 20, SpringLayout.NORTH, this);
		this.add(middle);
		//middle.setSelected(true);
		//middle.doClick();
		
		//right = new JRadioButton("right");
		right = new JButton();
		right.setBorderPainted(false);
		right.setContentAreaFilled(false);
		right.setFocusPainted(false);
		right.setIcon(new ImageIcon(gui.closeLane[2]));
		right.setRolloverIcon(new ImageIcon(gui.closeHoverLane[2]));
		//left.setPressedIcon(new ImageIcon(gui.buttonPressed[0]));
		right.addActionListener(gui);
		layout.putConstraint(SpringLayout.WEST, right, 65, SpringLayout.WEST, middle);
		layout.putConstraint(SpringLayout.NORTH, right, 30, SpringLayout.NORTH, this);
		this.add(right);

		//lane.add(left);
		//lane.add(middle);
		//lane.add(right);
		
		JLabel units = new JLabel(new ImageIcon(gui.unitsWord));
		layout.putConstraint(SpringLayout.NORTH, units, 5, SpringLayout.SOUTH, middle);
		this.add(units);
		
		archerIcon = new JButton();
		archerIcon.setBorderPainted(false);
		archerIcon.setContentAreaFilled(false);
		archerIcon.setFocusPainted(false);
		archerIcon.setIcon(new ImageIcon(gui.buttonIcon[0]));
		archerIcon.setRolloverIcon(new ImageIcon(gui.buttonHover[0]));
		archerIcon.setPressedIcon(new ImageIcon(gui.buttonPressed[0]));
		archerIcon.addActionListener(gui);
		layout.putConstraint(SpringLayout.WEST, archerIcon, 13, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, archerIcon, 77, SpringLayout.SOUTH, units);
		this.add(archerIcon);
		
		mageIcon = new JButton();
		mageIcon.setBorderPainted(false);
		mageIcon.setContentAreaFilled(false);
		mageIcon.setFocusPainted(false);
		mageIcon.setIcon(new ImageIcon(gui.buttonIcon[1]));
		mageIcon.setRolloverIcon(new ImageIcon(gui.buttonHover[1]));
		mageIcon.setPressedIcon(new ImageIcon(gui.buttonPressed[1]));
		mageIcon.addActionListener(gui);
		layout.putConstraint(SpringLayout.WEST, mageIcon, 70, SpringLayout.WEST, archerIcon);
		layout.putConstraint(SpringLayout.SOUTH, mageIcon, 77, SpringLayout.SOUTH, units);
		this.add(mageIcon);
		
		spearmanIcon = new JButton();
		spearmanIcon.setBorderPainted(false);
		spearmanIcon.setContentAreaFilled(false);
		spearmanIcon.setFocusPainted(false);
		spearmanIcon.setIcon(new ImageIcon(gui.buttonIcon[2]));
		spearmanIcon.setRolloverIcon(new ImageIcon(gui.buttonHover[2]));
		spearmanIcon.setPressedIcon(new ImageIcon(gui.buttonPressed[2]));
		spearmanIcon.addActionListener(gui);
		layout.putConstraint(SpringLayout.WEST, spearmanIcon, 13, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, spearmanIcon, 75, SpringLayout.NORTH, archerIcon);
		this.add(spearmanIcon);
		
		swordsmanIcon = new JButton();
		swordsmanIcon.setBorderPainted(false);
		swordsmanIcon.setContentAreaFilled(false);
		swordsmanIcon.setFocusPainted(false);
		swordsmanIcon.setIcon(new ImageIcon(gui.buttonIcon[3]));
		swordsmanIcon.setRolloverIcon(new ImageIcon(gui.buttonHover[3]));
		swordsmanIcon.setPressedIcon(new ImageIcon(gui.buttonPressed[3]));
		swordsmanIcon.addActionListener(gui);
		layout.putConstraint(SpringLayout.WEST, swordsmanIcon, 70, SpringLayout.WEST, spearmanIcon);
		layout.putConstraint(SpringLayout.NORTH, swordsmanIcon, 75, SpringLayout.NORTH, mageIcon);
		this.add(swordsmanIcon);
		
		JLabel upgrades = new JLabel(new ImageIcon(gui.upgradesWord));
		layout.putConstraint(SpringLayout.NORTH, upgrades, 3, SpringLayout.SOUTH, swordsmanIcon);
		upgrades.setVisible(true);
		this.add(upgrades);
		
		upgradeArchers = new JButton();
		upgradeArchers.setBorderPainted(false);
		upgradeArchers.setContentAreaFilled(false);
		upgradeArchers.setFocusPainted(false);
		upgradeArchers.setIcon(new ImageIcon(gui.buttonIcon[4]));
		upgradeArchers.setRolloverIcon(new ImageIcon(gui.buttonHover[4]));
		upgradeArchers.setPressedIcon(new ImageIcon(gui.buttonPressed[4]));
		upgradeArchers.addActionListener(gui);
		layout.putConstraint(SpringLayout.WEST, upgradeArchers, 13, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, upgradeArchers, 77, SpringLayout.SOUTH, upgrades);
		this.add(upgradeArchers);
		
		upgradeMage = new JButton();
		upgradeMage.setBorderPainted(false);
		upgradeMage.setContentAreaFilled(false);
		upgradeMage.setFocusPainted(false);
		upgradeMage.setIcon(new ImageIcon(gui.buttonIcon[5]));
		upgradeMage.setRolloverIcon(new ImageIcon(gui.buttonHover[5]));
		upgradeMage.setPressedIcon(new ImageIcon(gui.buttonPressed[5]));
		upgradeMage.addActionListener(gui);
		layout.putConstraint(SpringLayout.WEST, upgradeMage, 70, SpringLayout.WEST, upgradeArchers);
		layout.putConstraint(SpringLayout.SOUTH, upgradeMage, 77, SpringLayout.SOUTH, upgrades);
		this.add(upgradeMage);
		
		upgradeSpearmen = new JButton();
		upgradeSpearmen.setBorderPainted(false);
		upgradeSpearmen.setContentAreaFilled(false);
		upgradeSpearmen.setFocusPainted(false);
		upgradeSpearmen.setIcon(new ImageIcon(gui.buttonIcon[6]));
		upgradeSpearmen.setRolloverIcon(new ImageIcon(gui.buttonHover[6]));
		upgradeSpearmen.setPressedIcon(new ImageIcon(gui.buttonPressed[6]));
		upgradeSpearmen.addActionListener(gui);
		layout.putConstraint(SpringLayout.WEST, upgradeSpearmen, 13, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, upgradeSpearmen, 75, SpringLayout.NORTH, upgradeArchers);
		this.add(upgradeSpearmen);
		
		upgradeSwordsmen = new JButton();
		upgradeSwordsmen.setBorderPainted(false);
		upgradeSwordsmen.setContentAreaFilled(false);
		upgradeSwordsmen.setFocusPainted(false);
		upgradeSwordsmen.setIcon(new ImageIcon(gui.buttonIcon[7]));
		upgradeSwordsmen.setRolloverIcon(new ImageIcon(gui.buttonHover[7]));
		upgradeSwordsmen.setPressedIcon(new ImageIcon(gui.buttonPressed[7]));
		upgradeSwordsmen.addActionListener(gui);
		layout.putConstraint(SpringLayout.WEST, upgradeSwordsmen, 70, SpringLayout.WEST, upgradeSpearmen);
		layout.putConstraint(SpringLayout.NORTH, upgradeSwordsmen, 75, SpringLayout.NORTH, upgradeMage);
		this.add(upgradeSwordsmen);
		
		repairBase = new JButton();
		repairBase.setBorderPainted(false);
		repairBase.setContentAreaFilled(false);
		repairBase.setFocusPainted(false);
		repairBase.setIcon(new ImageIcon(gui.buttonIcon[8]));
		repairBase.setRolloverIcon(new ImageIcon(gui.buttonHover[8]));
		repairBase.setPressedIcon(new ImageIcon(gui.buttonPressed[8]));
		repairBase.addActionListener(gui);
		layout.putConstraint(SpringLayout.WEST, repairBase, 47, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, repairBase, 75, SpringLayout.NORTH, upgradeSpearmen);
		this.add(repairBase);
		
		String colorName = "";
		if(teamColor.equals(Color.BLUE)) colorName = "BLUE";
		else if(teamColor.equals(Color.GREEN))colorName = "GREEN";
		else if(teamColor.equals(Color.RED))colorName = "RED";
		else if(teamColor.equals(Color.PINK))colorName = "PINK";
			
		
		teamIdentifier = new JLabel("TEAM "+colorName);
		teamIdentifier.setForeground(teamColor);
		layout.putConstraint(SpringLayout.WEST, teamIdentifier, 3, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, teamIdentifier, 5, SpringLayout.SOUTH, repairBase);
		this.add(teamIdentifier);
		
		goldLabel = new JLabel("Gold: ");
		goldLabel.setForeground(Color.yellow);
		layout.putConstraint(SpringLayout.WEST, goldLabel, 3, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, goldLabel, 17, SpringLayout.SOUTH, teamIdentifier);
		this.add(goldLabel);
		
		healthLabel = new JLabel("Health: ");
		healthLabel.setForeground(Color.white);
		layout.putConstraint(SpringLayout.WEST, healthLabel, 3, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, healthLabel, 17, SpringLayout.SOUTH, goldLabel);
		this.add(healthLabel);
		
		message = new JLabel();
		message.setForeground(Color.red);
		layout.putConstraint(SpringLayout.WEST, message, 3, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, message, 17, SpringLayout.SOUTH, healthLabel);
		this.add(message);
		
		
	}
	
	public void updateHealth(int health) {
		healthLabel.setText("Health: "+health);
		this.setVisible(true);
	}

	public void updateGold(int gold) {
		goldLabel.setText("Gold: "+gold);
		this.setVisible(true);
	}

	public void updateMessage(String msg) {
		message.setText(msg);
		
		this.setVisible(true);
	}
	
	/*public ButtonGroup getLane() {
		return lane;
	}*/

	public JButton getLeft() {
		return left;
	}

	public JButton getMiddle() {
		return middle;
	}

	public JButton getRight() {
		return right;
	}

	public JButton getSwordsmanIcon() {
		return swordsmanIcon;
	}

	public JButton getSpearmanIcon() {
		return spearmanIcon;
	}

	public JButton getArcherIcon() {
		return archerIcon;
	}

	public JButton getMageIcon() {
		return mageIcon;
	}

	public JButton getUpgradeArchers() {
		return upgradeArchers;
	}

	public JButton getUpgradeSpearmen() {
		return upgradeSpearmen;
	}

	public JButton getUpgradeSwordsmen() {
		return upgradeSwordsmen;
	}

	public JButton getUpgradeMage() {
		return upgradeMage;
	}

	public JButton getRepairBase() {
		return repairBase;
	}

	public GUI getGui() {
		return gui;
	}

	public void disableUpgradeButton() {
		// TODO Auto-generated method stub
		
		upgradeMage.setEnabled(false);
		upgradeSpearmen.setEnabled(false);
		upgradeSwordsmen.setEnabled(false);
		upgradeArchers.setEnabled(false);
		this.setVisible(true);
	}
	
	public void disableAll() {
		
		
		
		left.setEnabled(false);
		middle.setEnabled(false);
		right.setEnabled(false);
		
		swordsmanIcon.setEnabled(false);
		spearmanIcon.setEnabled(false);
		archerIcon.setEnabled(false);
		mageIcon.setEnabled(false);
		
		upgradeArchers.setEnabled(false);
		upgradeSpearmen.setEnabled(false);
		upgradeSwordsmen.setEnabled(false);
		upgradeMage.setEnabled(false);
		repairBase.setEnabled(false);
		
		healthLabel.setVisible(false);
		goldLabel.setVisible(false);
		this.setVisible(true);
		
		
	}
	public void disableLane(String string) {
		// TODO Auto-generated method stub
		if(string.equalsIgnoreCase("LEFT")) {
			left.setEnabled(false);
		}
		else if(string.equalsIgnoreCase("MIDDLE")) {
			middle.setEnabled(false);
		}
		else if(string.equalsIgnoreCase("RIGHT")) {
			right.setEnabled(false);
		}
		
		if(left.isEnabled()) {
			left.doClick();
		}
		else if(middle.isEnabled()) {
			middle.doClick();
		}
		else {
			right.doClick();
		}
	}


	//private ButtonGroup lane;
	
	private JButton left;
	private JButton middle;
	private JButton right;
	
	private JButton swordsmanIcon;
	private JButton spearmanIcon;
	private JButton archerIcon;
	private JButton mageIcon;
	
	private JButton upgradeArchers;
	private JButton upgradeSpearmen;
	private JButton upgradeSwordsmen;
	private JButton upgradeMage;
	private JButton repairBase;
	
	private JLabel healthLabel;
	private JLabel goldLabel;
	private JLabel message;
	private JLabel teamIdentifier;
	
	//@Override
	/*public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == swordsmanIcon){
			JOptionPane.showMessageDialog(null, "Swordsman!", "hello", JOptionPane.ERROR_MESSAGE); 
		}
	}*/
	
	GUI gui;
	
	private SpringLayout layout;
}
