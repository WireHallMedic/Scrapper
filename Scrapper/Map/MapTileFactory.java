package Scrapper.Map;

import Scrapper.GUI.*;

public class MapTileFactory implements MapConstants, GUIConstants
{
   public static MapTile getSwitch(int eid)
   {
      MapTile tile = new SwitchTile(eid);
      return tile;
   }
   
   public static MapTile getDoor()
   {
      MapTile tile = new DoorTile();
      return tile;
   }
}