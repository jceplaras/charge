package ph.cmsc137.charge.client;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class ServerListBox extends JFrame implements Runnable{
	private GUI gui;
	
	public ServerListBox(GUI g) throws Exception{
		 gui = g;
		
			multiSocket.joinGroup(address); 
		  multiSocket.setSoTimeout(100);
		  
		  this.setSize(400, 300);
		  this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		  addWindowListener(new WindowAdapter() {
		      @Override
			public void windowOpened(WindowEvent e) {
		    	  setVisible(true);
		    	  setSize(400,300);
		    	  setLocation(gui.getLocation());
		      }

		      @Override
			public void windowClosing(WindowEvent e) {     
		          setVisible(false);
		          dispose();
		          gui.getUserLogin().getJoin().setEnabled(true);
		      }
		    });
		    pack();
		  jScrollPane1 = new JScrollPane();
	      serverBox = new JTable();
	      jScrollPane1.add(serverBox);
	      jScrollPane1.setViewportView(serverBox);
	      jScrollPane1.setPreferredSize(new Dimension(300, 200));
	      serverBox.setModel(jTable1Model);
	      serverBox.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
	      serverBox.setRowSelectionAllowed(true);
	      
	      jScrollPane1.getViewport().setBackground(Color.black);
	      
	      serverBox.setBackground(Color.black);
	      serverBox.setForeground(Color.yellow);
	      
	      JTableHeader serverHeader = serverBox.getTableHeader();
	      serverHeader.setBackground(Color.black);
	      serverHeader.setForeground(Color.yellow);
	      this.setTitle("Server List");
	      
	      //serverBox.enableInputMethods(false);
	      //serverBox.setEnabled(false);
	      
	      listSelectionModel = serverBox.getSelectionModel();
	      listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
	      serverBox.setSelectionModel(listSelectionModel);
		  this.add(jScrollPane1);
		  this.setVisible(true);
		  t = new Thread(this);
		  t.start();
	}
	
	
	
	public void printMap(Map mp) {
		   // Collection c = mp.values();
		    
		    Iterator itr =mp.entrySet().iterator();
		    while (itr.hasNext()) {
		        Map.Entry pairs = (Map.Entry)itr.next();
		        System.out.println(pairs.getKey() + " = " + pairs.getValue());
		        //System.out.println(itr.next());
		        Integer val = (Integer)pairs.getValue();
		        String key = (String)pairs.getKey();
		        if(val<=0){
		        	serverMap.remove(key);
		        	
		        	jTable1Model.removeRow(rowIndexMap.get(key));
		        	int rowDeleted=rowIndexMap.get(key);
		        	
		        	for(String key1 :rowIndexMap.keySet()){
		        		if(rowIndexMap.get(key1)>rowDeleted){
		        			int value = rowIndexMap.get(key1)-1;
		        			rowIndexMap.put(key1, value);
		        		}
		        	}
		        	
		        	rowIndexMap.remove(key);
		        	
		        	
		        	//serverTimeOut--;
		        }
		        else{
		        	serverMap.put(key, val-1);
		        }
		        
		    	itr.remove(); // avoids a ConcurrentModificationException
		    }
		}
	@Override
	public void run() {
		while(true) {
			Object o = serverMap.clone();
		   
		   printMap((Map)o);
		   
		   byte[] multiBuf = new byte[256];
			multiPacket=new DatagramPacket(multiBuf, multiBuf.length);
			try {
				multiSocket.receive(multiPacket);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			
			 String received = new String(multiPacket.getData(), 0, multiPacket.getLength());
	         received=received.trim();
			 if(received.startsWith("CHARGESERVEREXISTS")) {
				System.out.println(multiPacket.getAddress());
				String temp[]=received.split(" ");
				String key = temp[1]+" - "+multiPacket.getAddress().toString().substring(1);
				if(!serverMap.containsKey(key)){	
					serverMap.put(key, serverTimeOut);
					jTable1Model.addRow(new String[]{temp[1], multiPacket.getAddress().toString().substring(1)});
					rowIndexMap.put(key, jTable1Model.getRowCount()-1);
				}
				else{
					serverMap.put(key, serverTimeOut);
				}
				
			}
		}
	   
		
	}

	
	
	class SharedListSelectionHandler implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) { 
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();
	
			int firstIndex = e.getFirstIndex();
			int lastIndex = e.getLastIndex();
			boolean isAdjusting = e.getValueIsAdjusting(); 
			
			//ipaddr.setText("Event for indexes " + firstIndex + " - " + lastIndex + "; isAdjusting is " + isAdjusting + "; selected indexes:");

			
			if (!lsm.isSelectionEmpty()) 
			{
				//Find out which indexes are selected.
				int minIndex = lsm.getMinSelectionIndex();
				int maxIndex = lsm.getMaxSelectionIndex();
					for (int i = minIndex; i <= maxIndex; i++) {
						if (lsm.isSelectedIndex(i)) {
							gui.getUserLogin().getIpaddr().setText(serverBox.getValueAt(i, 1).toString());
						}
					}
			}
			
			
			gui.getUserLogin().getIpaddr().setCaretPosition(gui.getUserLogin().getIpaddr().getDocument().getLength());
		}
	}
	//ServerBox
	   private JTable serverBox;
	   private JScrollPane jScrollPane1;
	   private DefaultTableModel jTable1Model= new DefaultTableModel(new String[] {"Server Name", "Ip Address"}, 0) {
		   @Override
		public boolean isCellEditable(int rowIndex, int colIndex) {
			   return false;
		   }
	   };
	   ListSelectionModel listSelectionModel;  
	   // private JButton addRow;
	   
	   //test   
	 //Multicast Server Listing
	 	MulticastSocket multiSocket = new MulticastSocket(4446);
	    InetAddress address = InetAddress.getByName("230.0.0.1");
	    DatagramPacket multiPacket;
	    private HashMap<String, Integer> serverMap = new HashMap<String, Integer>(); 
	    private HashMap<String, Integer> rowIndexMap = new HashMap<String, Integer>(); 
	    private int serverTimeOut=10;
	    //private int threadCounter=0;
	    
	    private Thread t;
}
