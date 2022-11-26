package Scrapper.Map;

import Scrapper.GUI.*;

public class MapTileFactory implements MapConstants, GUIConstants
{
   public static MapTile getSwitch()
   {
      MapTile tile = new ToggleTile(TileBase.UNFLIPPED_SWITCH, TileBase.FLIPPED_SWITCH);
      return tile;
   }
   
   public static MapTile getDoor()
   {
      MapTile tile = new ToggleTile(TileBase.CLOSED_DOOR, TileBase.OPEN_DOOR);
      return tile;
   }
}