package Scrapper.Map;

import java.awt.*;
import Scrapper.GUI.*;
import Scrapper.Actor.*;

public class MapTile implements MapConstants, GUIConstants
{
	private TileBase tileBase;
	private Color fgColor;
	private Color bgColor;


	public TileBase getTileBase(){return tileBase;}
	public Color getFGColor(){return fgColor;}
	public Color getBGColor(){return bgColor;}


	public void setTileBase(TileBase b){tileBase = b;}
	public void setFGColor(Color f){fgColor = f;}
	public void setBGColor(Color b){bgColor = b;}

   public MapTile(TileBase base)
   {
      tileBase = base;
      fgColor = DEFAULT_FOREGROUND_COLOR;
      bgColor = DEFAULT_BACKGROUND_COLOR;
   }   
   
   // copy constructor
   public MapTile(MapTile that)
   {
      this.tileBase = that.getTileBase();
      this.fgColor = that.getFGColor();
      this.bgColor = that.getBGColor();
   }
   
   // TileBase calls
   public boolean isLowPassable(){return tileBase.lowPassable;}
   public boolean isHighPassable(){return tileBase.highPassable;}
   public boolean isLiquid(){return tileBase.liquid;}
   public boolean isTransparent(){return tileBase.transparent;}
   
   public boolean canStep(SActor actor)
   {
      if(actor.isFlying() && isHighPassable())
         return true;
      if(actor.isSwimming() && isLiquid())
         return true;
      return isLowPassable();
   }
}