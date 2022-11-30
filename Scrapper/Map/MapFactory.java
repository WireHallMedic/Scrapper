package Scrapper.Map;

import java.util.*;
import Scrapper.Engine.*;

public class MapFactory implements MapConstants
{
   public static Zone getDummyZone(String name, int[] exitPathList)
   {
      int size = 20;
      Zone z = new Zone(size, size);
      
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
      int docksDistro0 = SEngine.getUniqueID();
      int distro0Distro1 = SEngine.getUniqueID();
      int distro1Distro2 = SEngine.getUniqueID();
      int distro2transit = SEngine.getUniqueID();
      int transitQuarters0 = SEngine.getUniqueID();
      int transitOps0 = SEngine.getUniqueID();
      int transitSecurity0 = SEngine.getUniqueID();
      int transitLabs0 = SEngine.getUniqueID();
      int transitEng0 = SEngine.getUniqueID();
      int quarters0Quarters1 = SEngine.getUniqueID();
      int quarters1Quarters2 = SEngine.getUniqueID();
      int ops0Ops1 = SEngine.getUniqueID();
      int ops1Ops2 = SEngine.getUniqueID();
      int ops2Bridge = SEngine.getUniqueID();
      int eng0Eng1 = SEngine.getUniqueID();
      int eng1Eng2 = SEngine.getUniqueID();
      int eng2Reactor = SEngine.getUniqueID();
      int labs0Labs1 = SEngine.getUniqueID();
      int labs1Labs2 = SEngine.getUniqueID();
      int labs2SecLabs = SEngine.getUniqueID();
      int sec0Sec1 = SEngine.getUniqueID();
      int sec1Sec2 = SEngine.getUniqueID();
      int sec2Brig = SEngine.getUniqueID();
      
      int[] arr = {-1};
      int[] arr2 = {-1, -1};
      int[] arr6 = {-1, -1, -1, -1, -1, -1};
      arr[0] = docksDistro0;
      list.add(getDummyZone("Docks", arr));
      arr2[0] = docksDistro0;
      arr2[1] = distro0Distro1;
      list.add(getDummyZone("Distro 0", arr2));
      arr2[0] = distro0Distro1;
      arr2[1] = distro1Distro2;
      list.add(getDummyZone("Distro 1", arr2));
      arr2[0] = distro1Distro2;
      arr2[1] = distro2transit;
      list.add(getDummyZone("Distro 2", arr2));
      arr2[0] = transitQuarters0;
      arr2[1] = quarters0Quarters1;
      list.add(getDummyZone("Quarters 0", arr2));
      arr2[0] = quarters0Quarters1;
      arr2[1] = quarters1Quarters2;
      list.add(getDummyZone("Quarters 1", arr2));
      arr[0] = quarters1Quarters2;
      list.add(getDummyZone("Quarters 2", arr));
      arr6[0] = distro2transit;
      arr6[1] = transitQuarters0;
      arr6[2] = transitOps0;
      arr6[3] = transitSecurity0;
      arr6[4] = transitLabs0;
      arr6[5] = transitEng0;
      list.add(getDummyZone("Transit", arr6));
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