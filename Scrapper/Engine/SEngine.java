package Scrapper.Engine;

import Scrapper.Actor.*;
import Scrapper.Map.*;
import Scrapper.GUI.*;
import Scrapper.AI.*;
import java.util.*;
import javax.swing.*;

public class SEngine
{
   private static SActor player = null;
   private static Zone curZone = null;
   private static Vector<SActor> actorList = new Vector<SActor>();
   private int curActorIndex = 0;
   private static SFullPanel fullPanel = null;
   
   public static void setPlayer(SActor p){player = p;}
   public static void setCurZone(Zone z){curZone = z;}
   public static void setActorList(Vector<SActor> al){actorList = al;}
   
   public static SActor getPlayer(){return player;}
   public static Zone getCurZone(){return curZone;}
   public static Vector<SActor> getActorList(){return actorList;}
   
   
   // actor methods
   public static void add(SActor actor)
   {
      if(actorList.contains(actor) == false)
      {
         actorList.add(actor);
      }
   }
   
   public static void remove(SActor actor)
   {
      actorList.remove(actor);
   }
   
   public static void clearActorList()
   {
      actorList = new Vector<SActor>();
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
      BasicAI curAI = getActorList().elementAt(curActorIndex).getAI();
      curAI.chargeTurnEnergy();
      if(curAI.isCharged() == false)
         incrementActor();
      else
      {
         curAI.plan();
         if(curAI.hasPlan())
         {
            curAI.act();
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
   public static void setTerminalPanelVisible(){fullPanel.setTerminalPanelVisible();}
}