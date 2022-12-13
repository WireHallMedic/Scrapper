package Scrapper.Actor;

import WidlerSuite.*;
import Scrapper.AI.*;
import Scrapper.GUI.*;
import Scrapper.Item.*;
import Scrapper.Engine.*;
import Scrapper.Ability.*;
import java.awt.*;

public class SActor implements ActorConstants, AbilityConstants, ItemConstants, GUIConstants
{
	protected UnboundTile sprite;
   protected boolean flies;
   protected boolean swims;
   protected BasicAI ai;
   protected int visionRadius;
   protected ShadowFoVRect fov;
   private boolean takingTurn;
   private Inventory inventory;


	public UnboundTile getSprite(){return sprite;}
   public boolean isFlying(){return flies;}
   public boolean isSwimming(){return swims;}
   public BasicAI getAI(){return ai;}
   public int getVisionRadius(){return visionRadius;}
   public Inventory getInventory(){return inventory;}

	public void setSprite(UnboundTile s){sprite = s;}
   public void setFlying(boolean f){flies = f;}
   public void setSwimming(boolean s){swims = s;}
   public void setAI(BasicAI newAI){ai = newAI;}
   public void setVisionRadius(int v){visionRadius = v;}
   public void setInventory(Inventory i){inventory = i;}

   public SActor()
   {
      setSprite('?', DEFAULT_FOREGROUND_COLOR.getRGB(), DEFAULT_BACKGROUND_COLOR.getRGB());
      flies = false;
      swims = false;
      visionRadius = DEFAULT_VISION_RADIUS;
      boolean[][] dummyVisionArray = {{false}};
      fov = new ShadowFoVRect(dummyVisionArray);
      ai = new BasicAI(this);
      takingTurn = false;
      inventory = new Inventory(this);
   }
   
   public void setSprite(int tileIndex, int fgColor, int bgColor)
   {
      sprite = TileManager.x2y2Palette.getUnboundTile(tileIndex, fgColor, 
               bgColor, DEFAULT_TILE_SIZE_MULTIPLIER, UnboundTile.CIRCLE_BACKGROUND);
   }
   
   public boolean isPlayer()
   {
      return this == SEngine.getPlayer();
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
   
   public void setStep(
   
   
   // temporary
   public int getX(){return sprite.getXLoc();}
   public int getY(){return sprite.getYLoc();}
   public void setX(int x){sprite.setXLoc(x);}
   public void setY(int y){sprite.setYLoc(y);}
   public void setLoc(Coord c){setX(c.x); setY(c.y);}
   
   public static SActor getMock()
   {
      SActor a = new SActor();
      a.setSprite('@', PRIMARY_COLOR.getRGB(), Color.BLACK.getRGB());
      a.sprite.setXLoc(2);
      a.sprite.setYLoc(2);
      a.setAI(new PlayerAI(a));
      return a;
   }
   
   public static SActor getMockEnemy()
   {
      SActor a = new SActor();
      a.setSprite('e', SECONDARY_COLOR.getRGB(), Color.BLACK.getRGB());
      a.sprite.setXLoc(-2);
      a.sprite.setYLoc(2);
      return a;
   }
   
   public static SActor getMockEnemy2()
   {
      SActor a = new SActor();
      a.setSprite('e', TERTIARY_COLOR.getRGB(), Color.BLACK.getRGB());
      a.sprite.setXLoc(2);
      a.sprite.setYLoc(5);
      return a;
   }
}