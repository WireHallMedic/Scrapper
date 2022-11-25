package Scrapper.Actor;

import WidlerSuite.*;
import Scrapper.GUI.*;

public class SActor
{
	private UnboundTile sprite;


	public UnboundTile getSprite(){return sprite;}


	public void setSprite(UnboundTile s){sprite = s;}


   public SActor()
   {
      sprite = new UnboundTile(TileManager.x2y2Palette);
   }
   
   // temporary
   public int getIconIndex(){return sprite.getIconIndex();}
   public int getX(){return sprite.getXLoc();}
   public int getY(){return sprite.getYLoc();}
   public void setX(int x){sprite.setXLoc(x);}
   public void setY(int y){sprite.setYLoc(y);}
   
   public static SActor getMock()
   {
      SActor a = new SActor();
      a.sprite.setXLoc(2);
      a.sprite.setYLoc(2);
      a.sprite.setIconIndex('@');
      return a;
   }
}