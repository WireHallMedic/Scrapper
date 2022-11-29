package Scrapper.Map;

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
      z.setTile(1, 1, terminal);
      
      z.postProcess();
      return z;
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