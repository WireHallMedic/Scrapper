package Scrapper.Map;

import Scrapper.Engine.*;
import java.util.*;

public class MapFactoryTools implements MapConstants
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
            exitList.add(new ExitInfo(getPathKey(region, Region.DISTRIBUTION, 0), "Exit to " + Region.DISTRIBUTION.name + " Level 1"));
            exitList.add(new ExitInfo(getPathKey(region, Region.QUARTERS, 0), "Exit to " + Region.QUARTERS.name + " Level 1"));
            exitList.add(new ExitInfo(getPathKey(region, Region.OPERATIONS, 0), "Exit to " + Region.OPERATIONS.name + " Level 1"));
            exitList.add(new ExitInfo(getPathKey(region, Region.SECURITY, 0), "Exit to " + Region.SECURITY.name + " Level 1"));
            exitList.add(new ExitInfo(getPathKey(region, Region.LABS, 0), "Exit to " + Region.LABS.name + " Level 1"));
            exitList.add(new ExitInfo(getPathKey(region, Region.ENGINEERING, 0), "Exit to " + Region.ENGINEERING.name + " Level 1"));
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
            exitList.add(new ExitInfo(getPathKey(region, Region.SECURITY, ZoneLayout.brigConnectionLevel), 
               "Exit to " + Region.SECURITY.name + " Level " + (ZoneLayout.brigConnectionLevel + 1)));
            break;
         case REACTOR      :
            exitList.add(new ExitInfo(getPathKey(region, Region.ENGINEERING, ZoneLayout.reactorConnectionLevel), 
               "Exit to " + Region.ENGINEERING.name + " Level " + (ZoneLayout.reactorConnectionLevel + 1)));
            break;
         case SECURE_LABS  :
            exitList.add(new ExitInfo(getPathKey(region, Region.LABS, ZoneLayout.secLabConnectionLevel), 
               "Exit to " + Region.LABS.name + " Level " + (ZoneLayout.secLabConnectionLevel + 1)));
            break;
         case BRIDGE       :
            exitList.add(new ExitInfo(getPathKey(region, Region.OPERATIONS, ZoneLayout.bridgeConnectionLevel), 
               "Exit to " + Region.OPERATIONS.name + " Level " + (ZoneLayout.bridgeConnectionLevel + 1)));
            break;
      }
      return exitList;
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
}