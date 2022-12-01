package Scrapper.Map;

import Scrapper.GUI.*;

public class MapTileFactory implements MapConstants, GUIConstants
{
   public static SwitchTile getSwitch(int eid)
   {
      SwitchTile tile = new SwitchTile(eid);
      return tile;
   }
   
   public static DoorTile getDoor()
   {
      DoorTile tile = new DoorTile();
      return tile;
   }
   
   public static TerminalTile getTerminal(String msg)
   {
      TerminalTile tile = new TerminalTile();
      tile.setMessage(msg);
      return tile;
   }
   
   public static ExitTile getExit(int exitPath)
   {
      return new ExitTile(exitPath);
   }
}