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
         SActor player = SActor.getMock();
         SEngine.setPlayer(player);
         SEngine.add(player);
         //SEngine.add(SActor.getMockEnemy());
         SEngine.add(SActor.getMockEnemy2());
         SEngine.setCurZone(Zone.getMock());
         
         SEngine engine = new SEngine();
         engine.run();
      }
   }
}