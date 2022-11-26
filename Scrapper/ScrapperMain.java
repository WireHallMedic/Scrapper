package Scrapper;

import Scrapper.Map.*;
import Scrapper.GUI.*;
import Scrapper.Engine.*;
import Scrapper.Actor.*;
import java.util.logging.*;

public class ScrapperMain
{
   public static final boolean TEST_VERSION = true;
   
   public static void main(String[] args)
   {
      SFrame frame = new SFrame();
      if(TEST_VERSION)
      {
         Logger.getGlobal().info("Running in test mode");
         SEngine.setPlayer(SActor.getMock());
         SEngine.setCurZone(Zone.getMock());
      }
   }
}