package Scrapper.Map;

import java.util.*;
import Scrapper.Engine.*;

public class MapFactory implements MapConstants
{
   private static final int[][] pathKeyArr = generatePathKeys();
   
   private static int[][] generatePathKeys()
   {
      int len = Region.values().length;
      int[][] keyArr = new int[len][len];
      for(int x = 0; x < len; x++)
      for(int y = 0; y < len; y++)
      {
         keyArr[x][y] = SEngine.getUniqueID();
      }
      return keyArr;
   }
   
   // returns an int to be used as the pathNum for exit tiles
   public static int getPathKey(Region a, Region b, int lowerFloorLevel)
   {
      int x = Math.min(a.ordinal(), b.ordinal());
      int y = Math.max(a.ordinal(), b.ordinal());
      return (pathKeyArr[x][y] * 10) + lowerFloorLevel;
   }
   
   public static Vector<ExitInfo> getExitList(Region region, int level)
   {
      Vector<ExitInfo> exitList = new Vector<ExitInfo>();
      switch(region)
      {
         case DOCKS        :
            exitList.add(new ExitInfo(getPathKey(region, Region.DISTRIBUTION, 0), "Exit to Distrobution Level 1"));
            break;
         case DISTRIBUTION :
            if(level == 0)
            {
               exitList.add(new ExitInfo(getPathKey(region, Region.DOCKS, 0), "Exit to " + Region.DOCKS.name + " Level 1"));
               exitList.add(new ExitInfo(getPathKey(region, Region.TRANSIT, 0), "Exit to Transit Level"));
            }
            else
            {
               exitList.add(new ExitInfo(getPathKey(region, region, level - 1), "Exit to " + region.name + " Level " + level));
            }
            if(level < ZoneLayout.distributionHeight - 1)
            {
               exitList.add(new ExitInfo(getPathKey(region, region, level), "Exit to " + region.name + " Level " + (level + 1)));
            }
            break;
         case TRANSIT      :
            exitList.add(new ExitInfo(getPathKey(region, Region.DISTRIBUTION, 0), "Exit to Distrobution Level 1"));
            exitList.add(new ExitInfo(getPathKey(region, Region.QUARTERS, 0), "Exit to Quarters Level 1"));
            exitList.add(new ExitInfo(getPathKey(region, Region.OPERATIONS, 0), "Exit to Operations Level 1"));
            exitList.add(new ExitInfo(getPathKey(region, Region.SECURITY, 0), "Exit to Security Level 1"));
            exitList.add(new ExitInfo(getPathKey(region, Region.LABS, 0), "Exit to Labs Level 1"));
            exitList.add(new ExitInfo(getPathKey(region, Region.ENGINEERING, 0), "Exit to Engineering Level 1"));
            break;
         case QUARTERS     :
            if(level == 0)
            {
               exitList.add(new ExitInfo(getPathKey(region, Region.TRANSIT, 0), "Exit to Transit Level"));
            }
            else
            {
               exitList.add(new ExitInfo(getPathKey(region, region, level - 1), "Exit to " + region.name + " Level " + level));
            }
            if(level < ZoneLayout.quartersHeight - 1)
            {
               exitList.add(new ExitInfo(getPathKey(region, region, level), "Exit to " + region.name + " Level " + (level + 1)));
            }
            break;
         case SECURITY     :
            if(level == 0)
            {
               exitList.add(new ExitInfo(getPathKey(region, Region.TRANSIT, 0), "Exit to Transit Level"));
            }
            else
            {
               exitList.add(new ExitInfo(getPathKey(region, region, level - 1), "Exit to " + region.name + " Level " + level));
            }
            if(level < ZoneLayout.securityHeight - 1)
            {
               exitList.add(new ExitInfo(getPathKey(region, region, level), "Exit to " + region.name + " Level " + (level + 1)));
            }
            if(level == ZoneLayout.brigConnectionLevel)
            {
               exitList.add(new ExitInfo(getPathKey(region, Region.BRIG, level), "Exit to " + Region.BRIG.name));
            }
            break;
         case ENGINEERING  :
            if(level == 0)
            {
               exitList.add(new ExitInfo(getPathKey(region, Region.TRANSIT, 0), "Exit to Transit Level"));
            }
            else
            {
               exitList.add(new ExitInfo(getPathKey(region, region, level - 1), "Exit to " + region.name + " Level " + level));
            }
            if(level < ZoneLayout.engineeringHeight - 1)
            {
               exitList.add(new ExitInfo(getPathKey(region, region, level), "Exit to " + region.name + " Level " + (level + 1)));
            }
            if(level == ZoneLayout.reactorConnectionLevel)
            {
               exitList.add(new ExitInfo(getPathKey(region, Region.REACTOR, level), "Exit to " + Region.REACTOR.name));
            }
            break;
         case LABS         :
            if(level == 0)
            {
               exitList.add(new ExitInfo(getPathKey(region, Region.TRANSIT, 0), "Exit to Transit Level"));
            }
            else
            {
               exitList.add(new ExitInfo(getPathKey(region, region, level - 1), "Exit to " + region.name + " Level " + level));
            }
            if(level < ZoneLayout.labsHeight - 1)
            {
               exitList.add(new ExitInfo(getPathKey(region, region, level), "Exit to " + region.name + " Level " + (level + 1)));
            }
            if(level == ZoneLayout.secLabConnectionLevel)
            {
               exitList.add(new ExitInfo(getPathKey(region, Region.SECURE_LABS, level), "Exit to " + Region.SECURE_LABS.name));
            }
            break;
         case OPERATIONS   :
            if(level == 0)
            {
               exitList.add(new ExitInfo(getPathKey(region, Region.TRANSIT, 0), "Exit to Transit Level"));
            }
            else
            {
               exitList.add(new ExitInfo(getPathKey(region, region, level - 1), "Exit to " + region.name + " Level " + level));
            }
            if(level < ZoneLayout.securityHeight - 1)
            {
               exitList.add(new ExitInfo(getPathKey(region, region, level), "Exit to " + region.name + " Level " + (level + 1)));
            }
            if(level == ZoneLayout.bridgeConnectionLevel)
            {
               exitList.add(new ExitInfo(getPathKey(region, Region.BRIDGE, level), "Exit to " + Region.BRIDGE.name));
            }
            break;
         case BRIG         :
            exitList.add(new ExitInfo(getPathKey(region, Region.SECURITY, ZoneLayout.brigConnectionLevel), "Exit to " + Region.SECURITY.name +
               " Level " + (ZoneLayout.brigConnectionLevel + 1)));
            break;
         case REACTOR      :
            exitList.add(new ExitInfo(getPathKey(region, Region.ENGINEERING, ZoneLayout.reactorConnectionLevel), "Exit to " + Region.ENGINEERING.name +
               " Level " + (ZoneLayout.reactorConnectionLevel + 1)));
            break;
         case SECURE_LABS  :
            exitList.add(new ExitInfo(getPathKey(region, Region.LABS, ZoneLayout.secLabConnectionLevel), "Exit to " + Region.LABS.name +
               " Level " + (ZoneLayout.secLabConnectionLevel + 1)));
            break;
         case BRIDGE       :
            exitList.add(new ExitInfo(getPathKey(region, Region.OPERATIONS, ZoneLayout.bridgeConnectionLevel), "Exit to " + Region.OPERATIONS.name +
               " Level " + (ZoneLayout.bridgeConnectionLevel + 1)));
            break;
      }
      return exitList;
   }
   
   
   public static Zone getDummyZone(String name, Vector<ExitInfo> exitPathList)
   {
      int size = 20;
      Zone z = new Zone(size, size, name);
      
      addBorder(z, new MapTile(TileBase.HIGH_WALL));
      
      for(int i = 0; i < exitPathList.size(); i++)
      {
         int x = (2 + (i % 5)) * 2;
         int y = (2 + (i / 5)) * 2;
         addExit(z, x, y, exitPathList.elementAt(i));
      }
      MapTile terminal = MapTileFactory.getTerminal(name);
      z.setTile(1, 0, terminal);
      
      z.postProcess();
      return z;
   }
   
   public static Vector<Zone> getDummyZoneList()
   {
      Vector<Zone> list = new Vector<Zone>();
      list.add(getDummyZone("Docks", getExitList(Region.DOCKS, 0)));
      for(int i = 0; i < ZoneLayout.labsHeight; i++)
         list.add(getDummyZone("Labs Level " + (i + 1), getExitList(Region.LABS, i)));
      for(int i = 0; i < ZoneLayout.quartersHeight; i++)
         list.add(getDummyZone("Quarters Level " + (i + 1), getExitList(Region.QUARTERS, i)));
      for(int i = 0; i < ZoneLayout.securityHeight; i++)
         list.add(getDummyZone("Security Level " + (i + 1), getExitList(Region.SECURITY, i)));
      for(int i = 0; i < ZoneLayout.engineeringHeight; i++)
         list.add(getDummyZone("Engineering Level " + (i + 1), getExitList(Region.ENGINEERING, i)));
      for(int i = 0; i < ZoneLayout.distributionHeight; i++)
         list.add(getDummyZone("Distribution Level " + (i + 1), getExitList(Region.DISTRIBUTION, i)));
      for(int i = 0; i < ZoneLayout.opsHeight; i++)
         list.add(getDummyZone("Operations Level " + (i + 1), getExitList(Region.OPERATIONS, i)));
      list.add(getDummyZone("Transit Level", getExitList(Region.TRANSIT, 0)));
      list.add(getDummyZone("The Brig", getExitList(Region.BRIG, 0)));
      list.add(getDummyZone("The Bridge", getExitList(Region.BRIDGE, 0)));
      list.add(getDummyZone("The Secure Labs", getExitList(Region.SECURE_LABS, 0)));
      list.add(getDummyZone("The Reactor", getExitList(Region.REACTOR, 0)));
      
      return list;
   }
   
   public static void addBorder(Zone zone, MapTile borderTile)
   {
      for(int x = 0; x < zone.width(); x++)
      {
         zone.setTile(x, 0, new MapTile(borderTile));
         zone.setTile(x, zone.width() - 1, new MapTile(borderTile));
      }
      for(int y = 0; y < zone.height(); y++)
      {
         zone.setTile(0, y, new MapTile(borderTile));
         zone.setTile(zone.height() - 1, y, new MapTile(borderTile));
      }
   }
   
   public static void addExit(Zone z, int x, int y, ExitInfo ei)
   {
      ExitTile exitTile = (ExitTile)MapTileFactory.getExit(ei.index);
      exitTile.setName(ei.name);
      z.setTile(x, y, exitTile);
   }
   
   
   private static class ExitInfo
   {
      public int index;
      public String name;
      
      public ExitInfo(int i, String n)
      {
         index = i;
         name = n;
      }
   }
}