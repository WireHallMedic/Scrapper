package Scrapper.Actor;

import WidlerSuite.*;
import Scrapper.AI.*;
import Scrapper.Map.*;
import Scrapper.GUI.*;
import Scrapper.Item.*;
import Scrapper.Engine.*;
import Scrapper.Ability.*;
import java.awt.*;

public class SActor implements ActorConstants, AbilityConstants, ItemConstants, GUIConstants, MapConstants
{
	protected UnboundTile sprite;
   protected boolean flies;
   protected boolean swims;
   protected BasicAI ai;
   protected int visionRadius;
   protected ShadowFoVRect fov;
   private boolean takingTurn;
   private Inventory inventory;
   private Coord trueLoc;


	public UnboundTile getSprite(){return sprite;}
   public boolean isFlying(){return flies;}
   public boolean isSwimming(){return swims;}
   public BasicAI getAI(){return ai;}
   public int getVisionRadius(){return visionRadius;}
   public Inventory getInventory(){return inventory;}
   public Coord getTrueLoc(){return new Coord(trueLoc);}

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
      setTrueLoc(-1, -1, true);
   }
   
   public void setSprite(int tileIndex, int fgColor, int bgColor)
   {
      sprite = TileManager.x2y2Palette.getUnboundTile(tileIndex, fgColor, 
               bgColor, DEFAULT_TILE_SIZE_MULTIPLIER, UnboundTile.CIRCLE_BACKGROUND);
      sprite.setAffectedByAge(false);
   }
   
   public void setTrueLoc(int x, int y, boolean setSpriteLoc)
   {
      trueLoc = new Coord(x, y);
      if(setSpriteLoc)
      {
         sprite.setXLoc(x);
         sprite.setYLoc(y);
      }
   }
   public void setTrueLoc(Coord c, boolean setSpriteLoc){setTrueLoc(c.x, c.y, setSpriteLoc);}
   public void setLoc(Coord c){setTrueLoc(c.x, c.y, true);}
   public void setLoc(int x, int y){setTrueLoc(x, y, true);}
   
   public int getX(){return getSprite().getXLoc();}
   public int getY(){return getSprite().getYLoc();}
   
   
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
   
   // does a step and sets up the animation
   public void setStep(Direction dir)
   {
      int steps = 5;
      setTrueLoc(dir.x + getX(), dir.y + getY(), true);
      Direction from = dir.opposite();
      MovementScript script = new MovementScript(getSprite());
      script.setOffset(0, 1.0 * from.x, 1.0 * from.y);
      script.setImpulse(0, .2 * dir.x, .2 * dir.y);
      script.setImpulse(5, .2 * from.x, .2 * from.y);
      SEngine.getAnimationManager().addScript(script);
   }
   
   
   public static SActor getMock()
   {
      SActor a = new SActor();
      a.setSprite('@', PRIMARY_COLOR.getRGB(), Color.BLACK.getRGB());
      a.setLoc(2, 2);
      a.setAI(new PlayerAI(a));
      return a;
   }
   
   public static SActor getMockEnemy()
   {
      SActor a = new SActor();
      a.setSprite('e', SECONDARY_COLOR.getRGB(), Color.BLACK.getRGB());
      a.setLoc(3, 2);
      return a;
   }
   
   public static SActor getMockEnemy2()
   {
      SActor a = new SActor();
      a.setSprite('e', TERTIARY_COLOR.getRGB(), Color.BLACK.getRGB());
      a.setLoc(2, 5);
      return a;
   }
}