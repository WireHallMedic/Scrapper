package Scrapper;

import Scrapper.Map.*;
import Scrapper.GUI.*;
import Scrapper.Engine.*;
import Scrapper.Actor.*;
import java.util.logging.*;

import java.util.*;

public class ScrapperMain
{
   public static final boolean TEST_VERSION = true;
   
   public static void main(String[] args)
   {
      SFrame frame = new SFrame();
      SEngine engine = new SEngine();
      if(TEST_VERSION)
      {
         Logger.getGlobal().info("Running in test mode");
         SActor player = SActor.getMock();
         SEngine.setPlayer(player);
         /*
         Zone z1 = Zone.getMock();
         Zone z2 = Zone.getMock2();
         Zone z3 = Zone.getMock3();
         SEngine.add(z1);
         SEngine.add(z2);
         SEngine.add(z3);*/
         Vector<Zone> zoneList = MapFactory.getDummyZoneList();
         for(Zone z : zoneList)
            SEngine.add(z);
         SEngine.add(player);
         //SEngine.add(SActor.getMockEnemy());
         //SEngine.add(SActor.getMockEnemy2());
         
         //SEngine.setCurZone(zoneList.elementAt(0));
         SEngine.setCurZone(MapFactory.getTestingZone());
         
      }
      engine.run();
   }
}