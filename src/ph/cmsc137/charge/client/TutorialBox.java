package ph.cmsc137.charge.client;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class TutorialBox extends JFrame {
	public TutorialBox(GUI g){
		gui = g;
		Container c = this.getContentPane();
		
		addWindowListener(new WindowAdapter() {
		      @Override
			public void windowOpened(WindowEvent e) {
		    	  setVisible(true);
		    	  setResizable(false);
		    	  setTitle("Tutorial");
		    	  setSize(805,620);
		    	  setLocation(gui.getLocation());
		      }

		      @Override
			public void windowClosing(WindowEvent e) {     
		          setVisible(false);
		          dispose();
		          gui.getUserLogin().getTutorials().setEnabled(true);
		      }
		    });
		    pack();
	    	  ImageIcon ii = new ImageIcon(gui.loadImage("images\\tutorial.jpg"));
	  		  JLabel label = new JLabel();
	  		  label.setIcon(ii);
	  		  c.setBackground(Color.black);
	  		  c.add(label);
	  		  this.setVisible(true);
	}
	
	private GUI gui;
}
