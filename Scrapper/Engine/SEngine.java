package Scrapper.Engine;

import Scrapper.Actor.*;
import Scrapper.Map.*;

public class SEngine
{
   private static SActor player = null;
   private static Zone curZone = null;
   
   public static void setPlayer(SActor p){player = p;}
   public static void setCurZone(Zone z){curZone = z;}
   
   public static SActor getPlayer(){return player;}
   public static Zone getCurZone(){return curZone;}
   
}