package Scrapper.Engine;

import Scrapper.Actor.*;
import Scrapper.Map.*;
import Scrapper.GUI.*;
import Scrapper.AI.*;
import java.util.*;
import javax.swing.*;
import java.util.logging.*;
import WidlerSuite.*;

public class SEngine
{
   private static SActor player = null;
   private static Zone curZone = null;
   private static Vector<SActor> actorList = new Vector<SActor>();
   private static int curActorIndex = 0;
   private static SFullPanel fullPanel = null;
   private static int uniqueIDIndex = 0;
   private static Vector<ZonePackage> zonePackList = new Vector<ZonePackage>();
   private static TileAnimationManager animationManager = null;
   
   public static SquirrelRNG rng = new SquirrelRNG();
   
   public static void setPlayer(SActor p){player = p;}
   public static void setCurZone(Zone z){curZone = z;}
   public static void setActorList(Vector<SActor> al){actorList = al;}
   public static void setAnimationManager(TileAnimationManager am){animationManager = am;}
   
   public static SActor getPlayer(){return player;}
   public static Zone getCurZone(){return curZone;}
   public static Vector<SActor> getActorList(){return actorList;}
   public static TileAnimationManager getAnimationManager(){return animationManager;}
   
   
   public static int getUniqueID()
   {
      uniqueIDIndex++;
      return uniqueIDIndex;
   }
   
   // actor methods
   public static void add(SActor actor)
   {
      if(actorList.contains(actor) == false)
      {
         actorList.add(actor);
      }
   }
   
   public static void add(Zone z)
   {
      zonePackList.add(new ZonePackage(z));
   }
   
   public static void remove(SActor actor)
   {
      actorList.remove(actor);
   }
   
   public static void clearActorList()
   {
      actorList = new Vector<SActor>();
   }
   
   public static boolean isActorAt(int x, int y)
   {
      return getActorAt(x, y) != null;
   }
   
   public static SActor getActorAt(int x, int y)
   {
      for(int i = 0; i < actorList.size(); i++)
      {
         if(actorList.elementAt(i).getX() == x &&
            actorList.elementAt(i).getY() == y)
            return actorList.elementAt(i);
      }
      return null;
   }
   
   public static void resolveStepTaken(SActor actor)
   {
      if(actor == getPlayer())
      {
         MapTile tile = getCurZone().getTile(actor.getX(), actor.getY());
         
         if(tile instanceof ExitTile)
         {
            ExitTile exitTile = (ExitTile)tile;
            useExit(exitTile.getPathNum());
         }
      }
   }
   
   public static void useExit(int pathNum)
   {
      ZonePackage destZonePack = null;
      Zone destZone = null;
      // find other map with matching exit
      for(ZonePackage zp : zonePackList)
      {
         Zone z = zp.getZone();
         if(z != getCurZone() && z.hasExit(pathNum))
         {
            destZone = z;
            destZonePack = zp;
            break;
         }
      }
      if(destZonePack != null)
      {
         Coord entranceLoc = destZone.getExitLoc(pathNum);
         changeZone(destZonePack, entranceLoc);
      }
   }
   
   public static void changeZone(ZonePackage destZonePack, Coord destLoc)
   {
      Zone destZone = destZonePack.getZone();
      getPlayer().setLoc(destLoc);
      actorList.remove(getPlayer());
      setActorList(destZonePack.getActorList());
      actorList.add(getPlayer());
      setCurZone(destZonePack.getZone());
      MapPanel.updateActors();
      curActorIndex = 0;
      InfoPanel.addMessage("Now entering " + destZonePack.getZone().getName() + ".");
   }
   
   public void run()
   {
      while(true)
      {
         if(getActorList() != null && getActorList().size() > 0)
         {
            processCurActor();
         }
      }
   }
   
   // charge the current actor, and either let them act or move on
   private void processCurActor()
   {
      SActor curActor = getActorList().elementAt(curActorIndex);
      BasicAI curAI = curActor.getAI();
      curAI.chargeTurnEnergy();
      if(curAI.isCharged() == false)
         incrementActor();
      else
      {
         curActor.startTurn();
         curAI.plan();
         if(curAI.hasPlan())
         {
            curAI.act();
            curActor.endTurn();
            curZone.updateAutomaticDoors();
            incrementActor();
         }
      }
   }
   
   private void incrementActor()
   {
      curActorIndex++;
      if(curActorIndex >= getActorList().size())
         curActorIndex = 0;
   }
   
   
   // panel switching methods
   public static void register(SFullPanel fp)
   {
      fullPanel = fp;
   }
   
   public static JPanel getCurPanel()
   {
      return fullPanel.getCurPanel();
   }
   
   public static void setMainGamePanelVisible(){fullPanel.setMainGamePanelVisible();}
   public static void setTerminalPanelVisible(TerminalTile terminalTile){fullPanel.setTerminalPanelVisible(terminalTile);}
   public static void setInventoryPanelVisible(){fullPanel.setInventoryPanelVisible();}
}