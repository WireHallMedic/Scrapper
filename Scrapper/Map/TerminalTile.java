package Scrapper.Map;

import Scrapper.GUI.*;

public class TerminalTile extends MapTile implements GUIConstants, MapConstants
{
   private String message;
   
   public void setMessage(String msg){message = msg;}
   
   public String getMessage(){return message;}
   
   public TerminalTile()
   {
      super(TileBase.TERMINAL);
      setFGColor(PRIMARY_COLOR);
      setMessage("Terminal");
   } 
}