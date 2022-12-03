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
   private int curActorIndex = 0;
   private static SFullPanel fullPanel = null;
   private static int uniqueIDIndex = 0;
   private static Vector<Zone> zoneList = new Vector<Zone>();
   
   public static SquirrelRNG rng = new SquirrelRNG();
   
   public static void setPlayer(SActor p){player = p;}
   public static void setCurZone(Zone z){curZone = z;}
   public static void setActorList(Vector<SActor> al){actorList = al;}
   
   public static SActor getPlayer(){return player;}
   public static Zone getCurZone(){return curZone;}
   public static Vector<SActor> getActorList(){return actorList;}
   
   
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
      zoneList.add(z);
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
      Zone destZone = null;
      // find other map with matching exit
      for(Zone z : zoneList)
      {
         if(z != getCurZone() && z.hasExit(pathNum))
         {
            destZone = z;
            break;
         }
      }
      if(destZone != null)
      {
         getPlayer().setLoc(destZone.getExitLoc(pathNum));
         setCurZone(destZone);
         InfoPanel.addMessage("Now entering " + destZone.getName() + ".");
      }
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
}