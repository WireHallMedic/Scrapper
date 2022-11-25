package Scrapper.Engine;

import Scrapper.Actor.*;
import Scrapper.Map.*;

public class SEngine
{
   private static SActor player = null;
   
   public static void setPlayer(SActor p){player = p;}
   
   public static SActor getPlayer(){return player;}
}