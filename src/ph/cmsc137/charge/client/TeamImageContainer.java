package ph.cmsc137.charge.client;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class TeamImageContainer{
   private static final String lanes[] = {"LEFT", "MIDDLE", "RIGHT" };
   private ArrayList<HashMap<String,ArrayList<Image>>> archerShoot = new ArrayList<HashMap<String,ArrayList<Image>>>();
   private ArrayList<HashMap<String,Image>> archerWalk = new ArrayList<HashMap<String,Image>>();
   private ArrayList<HashMap<String,ArrayList<Image>>> swordsmanSlash = new ArrayList<HashMap<String,ArrayList<Image>>>();
   private ArrayList<HashMap<String,Image>> swordsmanWalk = new ArrayList<HashMap<String,Image>>();
   private ArrayList<HashMap<String,ArrayList<Image>>> spearmanPierce = new ArrayList<HashMap<String,ArrayList<Image>>>();
   private ArrayList<HashMap<String,Image>> spearmanWalk = new ArrayList<HashMap<String,Image>>();
   private ArrayList<HashMap<String,ArrayList<Image>>> mageCast = new ArrayList<HashMap<String,ArrayList<Image>>>();
   private ArrayList<HashMap<String,Image>> mageWalk = new ArrayList<HashMap<String,Image>>();
   private Image base;

   private int archerIndex = 0;
   private int swordsmanIndex = 0;
   private int spearmanIndex = 0;
   private int mageIndex = 0;
   
   
   public TeamImageContainer(int teamNumber){
     
		  
	   for(int i=0;i<3;i++) {
		  for(String lane: lanes) {
	    	  archerShoot.add(new HashMap<String, ArrayList<Image>>());
	    	  archerWalk.add(new HashMap<String, Image>());
	    	  swordsmanSlash.add(new HashMap<String, ArrayList<Image>>());
	    	  swordsmanWalk.add(new HashMap<String, Image>());
	    	  spearmanPierce.add(new HashMap<String, ArrayList<Image>>());
	    	  spearmanWalk.add(new HashMap<String, Image>());
	    	  mageCast.add(new HashMap<String, ArrayList<Image>>());
	    	  mageWalk.add(new HashMap<String, Image>());
	    	  
	    	  archerWalk.get(i).put(lane, loadImage("images/"+teamNumber+"/archer/walk/"+lane.toLowerCase()+"_"+i+".png"));
	    	  swordsmanWalk.get(i).put(lane, loadImage("images/"+teamNumber+"/swordsman/walk/"+lane.toLowerCase()+"_"+i+".png"));
	    	  spearmanWalk.get(i).put(lane, loadImage("images/"+teamNumber+"/spearman/walk/"+lane.toLowerCase()+"_"+i+".png"));
	    	  mageWalk.get(i).put(lane, loadImage("images/"+teamNumber+"/mage/walk/"+lane.toLowerCase()+"_"+i+".png"));
	    	  
	    	  archerShoot.get(i).put(lane,new ArrayList<Image>());
	    	  swordsmanSlash.get(i).put(lane,new ArrayList<Image>());
	    	  spearmanPierce.get(i).put(lane,new ArrayList<Image>());
	    	  mageCast.get(i).put(lane,new ArrayList<Image>());
	    	  
	    	  for(int j=0;j<4;j++) {
	    		  archerShoot.get(i).get(lane).add(loadImage("images/"+teamNumber+"/archer/shoot/"+lane.toLowerCase()+"/"+lane.toLowerCase()+"_"+i+"_"+(j+1)+".png"));
	    		  swordsmanSlash.get(i).get(lane).add(loadImage("images/"+teamNumber+"/swordsman/slash/"+lane.toLowerCase()+"/"+lane.toLowerCase()+"_"+i+"_"+(j+1)+".png"));
	    		  
	    		  mageCast.get(i).get(lane).add(loadImage("images/"+teamNumber+"/mage/cast/"+lane.toLowerCase()+"/"+lane.toLowerCase()+"_"+i+"_"+(j+1)+".png"));
	    		  if(j==3) continue;
	    		  spearmanPierce.get(i).get(lane).add(loadImage("images/"+teamNumber+"/spearman/pierce/"+lane.toLowerCase()+"/"+lane.toLowerCase()+"_"+i+"_"+(j+1)+".png"));
	    	  }
	      }
      }
      base = loadImage("images/"+teamNumber+"/base.png");
   }

   private Image loadImage(String loc) {
      return new ImageIcon(loc).getImage();
   }

   public Image getImageAttack(String type, String lane, int level){
      if(type.equalsIgnoreCase("archer")){
    	  archerIndex=(archerIndex+1)%4;
         return archerShoot.get(level).get(lane.toUpperCase()).get(archerIndex);
         
      }else if(type.equalsIgnoreCase("swordsman")){
    	  swordsmanIndex=(swordsmanIndex+1)%4;
    	  return swordsmanSlash.get(level).get(lane.toUpperCase()).get(swordsmanIndex);
      }else if(type.equalsIgnoreCase("spearman")){
    	  spearmanIndex=(spearmanIndex+1)%3;
    	  return spearmanPierce.get(level).get(lane.toUpperCase()).get(spearmanIndex);
        
      }else{
    	  mageIndex=(mageIndex+1)%4;
         return mageCast.get(level).get(lane.toUpperCase()).get(mageIndex);
         
      }
   }

   public Image getImageWalk(String type, String lane, int level){
      if(type.equalsIgnoreCase("archer")){
         return archerWalk.get(level).get(lane.toUpperCase());
      }else if(type.equalsIgnoreCase("swordsman")){
         return swordsmanWalk.get(level).get(lane.toUpperCase());
      }else if(type.equalsIgnoreCase("spearman")){
         return spearmanWalk.get(level).get(lane.toUpperCase());
      }else{
         return mageWalk.get(level).get(lane.toUpperCase());
      }
   }

   public Image getImageBase(){
      return base;
   }
}
