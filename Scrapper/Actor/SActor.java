package Scrapper.Actor;

import WidlerSuite.*;
import Scrapper.AI.*;
import Scrapper.GUI.*;
import Scrapper.Engine.*;
import Scrapper.Ability.*;

public class SActor implements ActorConstants, AbilityConstants
{
	protected UnboundTile sprite;
   protected boolean flies;
   protected boolean swims;
   protected BasicAI ai;
   protected int visionRadius;
   protected ShadowFoVRect fov;
   private boolean takingTurn;


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
      boolean[][] dummyVisionArray = {{false}};
      fov = new ShadowFoVRect(dummyVisionArray);
      ai = new BasicAI(this);
      takingTurn = false;
   }
   
   // beginning of turn stuff
   public void startTurn()
   {
      if(!takingTurn)
      {
         takingTurn = true;
         updateFoV();
      }
   }
   
   // end of turn stuff
   public void endTurn()
   {
      takingTurn = false;
      if(getAI().getLastAction() == Action.STEP)
      {
         SEngine.resolveStepTaken(this);
      }
      updateFoV();
   }
   
   private void updateFoV()
   {
      fov.reset(SEngine.getCurZone().getTransparencyArray(getX(), getY(), getVisionRadius()));
      fov.calcFoV(getVisionRadius(), getVisionRadius(), getVisionRadius());
   }
   
   public boolean canSee(int x, int y)
   {
      return fov.isVisible(x - getX() + getVisionRadius(), y - getY() + getVisionRadius());
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