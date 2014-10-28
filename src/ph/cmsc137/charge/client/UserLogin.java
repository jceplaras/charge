package ph.cmsc137.charge.client;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


public class UserLogin extends JPanel{
	public UserLogin(GUI g){
		this.gui = g;

		layout = new SpringLayout();
		this.setLayout(layout);
		this.setSize(800, 600);
		//this.setBackground(Color.decode("#041278"));
		this.setBackground(Color.black);
		JLabel jl = new JLabel(/*new ImageIcon(gui.intro)*/);
		layout.putConstraint(SpringLayout.WEST, jl, 7, SpringLayout.WEST, gui);
		layout.putConstraint(SpringLayout.NORTH, jl, 5, SpringLayout.NORTH, gui);
		this.add(jl);
		//gui.setVisible(true);
		//gui.getGraphics().drawImage(new ImageIcon("images\\intro_0.gif").getImage(), 10, 10, this);
		
		ipaddr = new JTextField(18);
		cName = new JTextField(18);

		ipaddr.setPreferredSize(new Dimension(25, 25));
		cName.setPreferredSize(new Dimension(25, 25));
		
		Font newTextFieldFont=new Font("Comic Sans MS", Font.BOLD, 20);
		ipaddr.setFont(newTextFieldFont);
		cName.setFont(newTextFieldFont);

		ipaddr.setOpaque(false);
		ipaddr.setBorder(null);
		//ipaddr.setEditable(false);
		
		cName.setOpaque(false);
		cName.setBorder(null);
		
		layout.putConstraint(SpringLayout.NORTH, ipaddr, 388, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, ipaddr, 445, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, cName, 25, SpringLayout.SOUTH, ipaddr);
		layout.putConstraint(SpringLayout.WEST, cName, 445, SpringLayout.WEST, this);
		
		this.add(ipaddr);
		this.add(cName);
		
		join = new JButton();
		join.setBorderPainted(false);
		join.setContentAreaFilled(false);
		join.setFocusPainted(false);
		join.setIcon(new ImageIcon(gui.buttonIcon[9]));
		join.setRolloverIcon(new ImageIcon(gui.buttonHover[9]));
		join.setPressedIcon(new ImageIcon(gui.buttonPressed[9]));
		join.addActionListener(gui);
		layout.putConstraint(SpringLayout.WEST, join, 400, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, join, 20, SpringLayout.NORTH, cName);
		this.add(join);
		
		create = new JButton();
		create.setBorderPainted(false);
		create.setContentAreaFilled(false);
		create.setFocusPainted(false);
		create.setIcon(new ImageIcon(gui.buttonIcon[10]));
		create.setRolloverIcon(new ImageIcon(gui.buttonHover[10]));
		create.setPressedIcon(new ImageIcon(gui.buttonPressed[10]));
		create.addActionListener(gui);
		layout.putConstraint(SpringLayout.WEST, create, 170, SpringLayout.WEST, join);
		layout.putConstraint(SpringLayout.NORTH, create, 20, SpringLayout.NORTH, cName);
		this.add(create);
		
		play = new JButton();
		play.setBorderPainted(false);
		play.setContentAreaFilled(false);
		play.setFocusPainted(false);
		play.setIcon(new ImageIcon(gui.buttonIcon[11]));
		play.setRolloverIcon(new ImageIcon(gui.buttonHover[11]));
		play.setPressedIcon(new ImageIcon(gui.buttonPressed[11]));
		play.addActionListener(gui);
		layout.putConstraint(SpringLayout.WEST, play, 400, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, play, 50, SpringLayout.NORTH, join);
		this.add(play);
		
		tutorials = new JButton();
		tutorials.setBorderPainted(false);
		tutorials.setContentAreaFilled(false);
		tutorials.setFocusPainted(false);
		tutorials.setIcon(new ImageIcon(gui.buttonIcon[12]));
		tutorials.setRolloverIcon(new ImageIcon(gui.buttonHover[12]));
		tutorials.setPressedIcon(new ImageIcon(gui.buttonPressed[12]));
		tutorials.addActionListener(gui);
		layout.putConstraint(SpringLayout.WEST, tutorials, 170, SpringLayout.WEST, play);
		layout.putConstraint(SpringLayout.NORTH, tutorials, 50, SpringLayout.NORTH, create);
		this.add(tutorials);
		
		this.setVisible(true);
	}

	@Override
	public void paintComponent(Graphics g)  
    {  
      super.paintComponent(g);  
      int x = 430;
      int y = 375;
      if(gui.intro != null) g.drawImage(gui.intro, 7,6,this); 
      if(gui.textFieldImage != null) g.drawImage(gui.textFieldImage, x,y,this);  
      if(gui.textFieldImage != null) g.drawImage(gui.textFieldImage, x,y+50,this);  
      if(gui.sNameLabel != null) g.drawImage(gui.sNameLabel, x-80,y+10,this);  
      if(gui.cNameLabel != null) g.drawImage(gui.cNameLabel, x-70,y+60,this);  
    }  
	
	public JButton getCreate() {
		return create;
	}
	public JButton getPlay() {
		return play;
	}
	public JButton getJoin() {
		return join;
	}
	public JButton getTutorials() {
		return tutorials;
	}

	public JTextField getIpaddr() {
		return ipaddr;
	}
	public JTextField getcName() {
		return cName;
	}

	SpringLayout layout;
	GUI gui;

	private JButton create;
	private JButton play;
	private JButton join;
	private JButton tutorials;
	
	private JTextField ipaddr;
	private JTextField cName;
}
