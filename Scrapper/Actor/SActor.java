package Scrapper.Actor;

import WidlerSuite.*;
import Scrapper.AI.*;
import Scrapper.GUI.*;

public class SActor implements ActorConstants
{
	private UnboundTile sprite;
   private boolean flies;
   private boolean swims;
   private BasicAI ai;
   private int visionRadius;


	public UnboundTile getSprite(){return sprite;}
   public boolean isFlying(){return flies;}
   public boolean isSwimming(){return swims;}
   public BasicAI getAI(){return ai;}
   public int getVisionRadius(){return visionRadius;}

	public void setSprite(UnboundTile s){sprite = s;}
   public void setFlying(boolean f){flies = f;}
   public void setSwimming(boolean s){swims = s;}
   public void setAI(BasicAI newAI){ai = newAI;}
   public void setVisionRadius(int v){visionRadius = v;}

   public SActor()
   {
      sprite = new UnboundTile(TileManager.x2y2Palette);
      flies = false;
      swims = false;
      visionRadius = DEFAULT_VISION_RADIUS;
      ai = new BasicAI(this);
   }
   
   
   // temporary
   public int getIconIndex(){return sprite.getIconIndex();}
   public int getX(){return sprite.getXLoc();}
   public int getY(){return sprite.getYLoc();}
   public void setX(int x){sprite.setXLoc(x);}
   public void setY(int y){sprite.setYLoc(y);}
   
   public static SActor getMock()
   {
      SActor a = new SActor();
      a.sprite.setXLoc(2);
      a.sprite.setYLoc(2);
      a.sprite.setIconIndex('@');
      a.sprite.setFGColor(GUIConstants.PRIMARY_COLOR.getRGB());
      a.setAI(new PlayerAI(a));
      return a;
   }
   
   public static SActor getMockEnemy()
   {
      SActor a = new SActor();
      a.sprite.setXLoc(-2);
      a.sprite.setYLoc(2);
      a.sprite.setIconIndex('e');
      a.sprite.setFGColor(GUIConstants.SECONDARY_COLOR.getRGB());
      return a;
   }
   
   public static SActor getMockEnemy2()
   {
      SActor a = new SActor();
      a.sprite.setXLoc(2);
      a.sprite.setYLoc(5);
      a.sprite.setIconIndex('e');
      a.sprite.setFGColor(GUIConstants.TERTIARY_COLOR.getRGB());
      return a;
   }
}