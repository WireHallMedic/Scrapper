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
   
   public static int[] getExitList(Region region, int level)
   {
      Vector<Integer> keyList = new Vector<Integer>();
      switch(region)
      {
         case DOCKS        :
            keyList.add(getPathKey(region, Region.DISTRIBUTION, 0));
            break;
         case DISTRIBUTION :
            if(level == 0)
            {
               keyList.add(getPathKey(region, Region.DOCKS, 0));
               keyList.add(getPathKey(region, Region.TRANSIT, 0));
            }
            else
               keyList.add(getPathKey(region, region, level - 1));
            if(level < ZoneLayout.distributionHeight - 1)
               keyList.add(getPathKey(region, region, level));
            break;
         case TRANSIT      :
            keyList.add(getPathKey(region, Region.DISTRIBUTION, 0));
            keyList.add(getPathKey(region, Region.QUARTERS, 0));
            keyList.add(getPathKey(region, Region.OPERATIONS, 0));
            keyList.add(getPathKey(region, Region.SECURITY, 0));
            keyList.add(getPathKey(region, Region.LABS, 0));
            keyList.add(getPathKey(region, Region.ENGINEERING, 0));
            break;
         case QUARTERS     :
            if(level == 0)
            {
               keyList.add(getPathKey(region, Region.TRANSIT, 0));
            }
            else
               keyList.add(getPathKey(region, region, level - 1));
            if(level < ZoneLayout.quartersHeight - 1)
               keyList.add(getPathKey(region, region, level));
            break;
         case SECURITY     :
            if(level == 0)
            {
               keyList.add(getPathKey(region, Region.TRANSIT, 0));
            }
            else
               keyList.add(getPathKey(region, region, level - 1));
            if(level < ZoneLayout.securityHeight - 1)
               keyList.add(getPathKey(region, region, level));
            if(level == ZoneLayout.brigConnectionLevel)
               keyList.add(getPathKey(region, Region.BRIG, level));
            break;
         case ENGINEERING  :
            if(level == 0)
            {
               keyList.add(getPathKey(region, Region.TRANSIT, 0));
            }
            else
               keyList.add(getPathKey(region, region, level - 1));
            if(level < ZoneLayout.engineeringHeight - 1)
               keyList.add(getPathKey(region, region, level));
            if(level == ZoneLayout.reactorConnectionLevel)
               keyList.add(getPathKey(region, Region.REACTOR, level));
            break;
         case LABS         :
            if(level == 0)
            {
               keyList.add(getPathKey(region, Region.TRANSIT, 0));
            }
            else
               keyList.add(getPathKey(region, region, level - 1));
            if(level < ZoneLayout.labsHeight - 1)
               keyList.add(getPathKey(region, region, level));
            if(level == ZoneLayout.secLabConnectionLevel)
               keyList.add(getPathKey(region, Region.SECURE_LABS, level));
            break;
         case OPERATIONS   :
            if(level == 0)
            {
               keyList.add(getPathKey(region, Region.TRANSIT, 0));
            }
            else
               keyList.add(getPathKey(region, region, level - 1));
            if(level < ZoneLayout.opsHeight - 1)
               keyList.add(getPathKey(region, region, level));
            if(level == ZoneLayout.bridgeConnectionLevel)
               keyList.add(getPathKey(region, Region.BRIDGE, level));
            break;
         case BRIG         :
            keyList.add(getPathKey(region, Region.SECURITY, ZoneLayout.brigConnectionLevel));
            break;
         case REACTOR      :
            keyList.add(getPathKey(region, Region.ENGINEERING, ZoneLayout.reactorConnectionLevel));
            break;
         case SECURE_LABS  :
            keyList.add(getPathKey(region, Region.LABS, ZoneLayout.secLabConnectionLevel));
            break;
         case BRIDGE       :
            keyList.add(getPathKey(region, Region.OPERATIONS, ZoneLayout.bridgeConnectionLevel));
            break;
      }
      int[] returnList = new int[keyList.size()];
      for(int i = 0; i < keyList.size(); i++)
         returnList[i] = keyList.elementAt(i);
      return returnList;
   }
   
   
   public static Zone getDummyZone(String name, int[] exitPathList)
   {
      int size = 20;
      Zone z = new Zone(size, size, name);
      
      addBorder(z, new MapTile(TileBase.HIGH_WALL));
      
      for(int i = 0; i < exitPathList.length; i++)
      {
         int x = (2 + (i % 5)) * 2;
         int y = (2 + (i / 5)) * 2;
         z.setTile(x, y, MapTileFactory.getExit(exitPathList[i]));
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
}