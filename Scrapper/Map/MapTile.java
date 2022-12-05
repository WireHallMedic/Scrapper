package Scrapper.Map;

import java.awt.*;
import Scrapper.GUI.*;
import Scrapper.Actor.*;

public class MapTile implements MapConstants, GUIConstants, STileObject
{
   private String name;
	private TileBase tileBase;
	private Color fgColor;
	private Color bgColor;
   private int iconIndex;


   public String getName(){return name;}
	public TileBase getTileBase(){return tileBase;}
	public Color getFGColor(){return fgColor;}
	public Color getBGColor(){return bgColor;}
   public int getIconIndex(){return iconIndex;}


   public void setName(String n){name = n;}
	public void setTileBase(TileBase b){tileBase = b; iconIndex = tileBase.iconIndex;}
	public void setFGColor(Color f){fgColor = f;}
	public void setBGColor(Color b){bgColor = b;}
   public void setIconIndex(int i){iconIndex = i;}

   public MapTile(TileBase base)
   {
      name = "";
      setTileBase(base);
      setFGColor(DEFAULT_FOREGROUND_COLOR);
      setBGColor(bgColor = DEFAULT_FLOOR_COLOR);
      if(tileBase == TileBase.NULL || tileBase == TileBase.VOID)
         setBGColor(DEFAULT_BACKGROUND_COLOR);
   }   
   
   // copy constructor
   public MapTile(MapTile that)
   {
      setTileBase(that.getTileBase());
      setFGColor(that.getFGColor());
      setBGColor(that.getBGColor());
   }
   
   // TileBase calls
   public boolean isLowPassable(){return getTileBase().lowPassable;}
   public boolean isHighPassable(){return getTileBase().highPassable;}
   public boolean isLiquid(){return getTileBase().liquid;}
   public boolean isTransparent(){return getTileBase().transparent;}
   
   public boolean canStep(SActor actor)
   {
      if(actor.isFlying() && isHighPassable())
         return true;
      if(actor.isSwimming() && isLiquid())
         return true;
      return isLowPassable();
   }
}