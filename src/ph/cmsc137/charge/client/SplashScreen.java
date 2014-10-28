package ph.cmsc137.charge.client;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class SplashScreen extends JFrame {
	public SplashScreen(int type,GUI g){
		this.setAlwaysOnTop(true);
		this.setUndecorated(true);
		Point p = g.getLocation();
		Dimension d = g.getSize();
		
		int x = (int) (p.getX() + d.getWidth()/2 - 150);
		int y = (int) (p.getY() + d.getHeight()/2 - 150);
		this.setLocation(x, y);
		//this.setFocusable(true);
		//this.requestFocus();
		this.setSize(300, 80);
		if(type == 1){//play
			this.getContentPane().add(new JLabel(new ImageIcon("images\\icons\\connected.gif")));
		
		}else if(type == 2){//create
			this.getContentPane().add(new JLabel(new ImageIcon("images\\icons\\created.gif")));
			
		}
		
		this.setVisible(true);
	}
}
