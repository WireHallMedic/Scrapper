package Scrapper.Map;

import java.util.*;
import Scrapper.Item.*;
import Scrapper.Engine.*;

public class MapFactory implements MapConstants, ItemConstants
{

   public static Vector<Zone> getDummyZoneList()
   {
      Vector<Zone> list = new Vector<Zone>();
      list.add(getDummyZone("Docks", MapFactoryTools.getExitList(Region.DOCKS, 0)));
      for(int i = 0; i < ZoneLayout.labsHeight; i++)
         list.add(getDummyZone("Labs Level " + (i + 1), MapFactoryTools.getExitList(Region.LABS, i)));
      for(int i = 0; i < ZoneLayout.quartersHeight; i++)
         list.add(getDummyZone("Quarters Level " + (i + 1), MapFactoryTools.getExitList(Region.QUARTERS, i)));
      for(int i = 0; i < ZoneLayout.securityHeight; i++)
         list.add(getDummyZone("Security Level " + (i + 1), MapFactoryTools.getExitList(Region.SECURITY, i)));
      for(int i = 0; i < ZoneLayout.engineeringHeight; i++)
         list.add(getDummyZone("Engineering Level " + (i + 1), MapFactoryTools.getExitList(Region.ENGINEERING, i)));
      for(int i = 0; i < ZoneLayout.distributionHeight; i++)
         list.add(getDummyZone("Distribution Level " + (i + 1), MapFactoryTools.getExitList(Region.DISTRIBUTION, i)));
      for(int i = 0; i < ZoneLayout.opsHeight; i++)
         list.add(getDummyZone("Operations Level " + (i + 1), MapFactoryTools.getExitList(Region.OPERATIONS, i)));
      list.add(getDummyZone("Transit Level", MapFactoryTools.getExitList(Region.TRANSIT, 0)));
      list.add(getDummyZone("The Brig", MapFactoryTools.getExitList(Region.BRIG, 0)));
      list.add(getDummyZone("The Bridge", MapFactoryTools.getExitList(Region.BRIDGE, 0)));
      list.add(getDummyZone("The Secure Labs", MapFactoryTools.getExitList(Region.SECURE_LABS, 0)));
      list.add(getDummyZone("The Reactor", MapFactoryTools.getExitList(Region.REACTOR, 0)));
      
      return list;
   }
   
   public static Zone getTestingZone()
   {
      int size = 20;
      Zone z = new Zone(size, size, "Testing Zone");
      
      MapFactoryTools.addBorder(z, new MapTile(TileBase.HIGH_WALL));
      
      DoorTile door = MapTileFactory.getDoor();
      z.setTile(1, 4, door);
      door = MapTileFactory.getDoor();
      door.setAutomatic(true);
      z.setTile(3, 4, door);
      door = MapTileFactory.getDoor();
      door.setAutomatic(true);
      door.setLocked(true);
      z.setTile(5, 4, door);
      door = MapTileFactory.getDoor();
      door.setLocked(true);
      door.setLockedBy(QuestItem.SECURITY_KEYCARD);
      z.setTile(7, 4, door);
      door = MapTileFactory.getDoor();
      door.setLocked(true);
      door.setLockedBy(QuestItem.OPERATIONS_KEYCARD);
      z.setTile(9, 4, door);
      z.setItemAt(1, 1, ItemFactory.getQuestItem(QuestItem.SECURITY_KEYCARD));
      z.setItemAt(1, 2, ItemFactory.getMockItem());
      z.setItemAt(1, 3, ItemFactory.getMockItem());
      z.setItemAt(1, 4, ItemFactory.getMockItem());
      MapTile terminal = MapTileFactory.getTerminal("Just a terminal.");
      z.setTile(1, 0, terminal);
      
      z.postProcess();
      return z;
   }
   
   
   public static Zone getDummyZone(String name, Vector<ExitInfo> exitPathList)
   {
      int size = 20;
      Zone z = new Zone(size, size, name);
      
      MapFactoryTools.addBorder(z, new MapTile(TileBase.HIGH_WALL));
      
      for(int i = 0; i < exitPathList.size(); i++)
      {
         int x = (2 + (i % 5)) * 2;
         int y = (2 + (i / 5)) * 2;
         MapFactoryTools.addExit(z, x, y, exitPathList.elementAt(i));
      }
      MapTile terminal = MapTileFactory.getTerminal(name);
      z.setTile(1, 0, terminal);
      
      z.postProcess();
      return z;
   }
   
}