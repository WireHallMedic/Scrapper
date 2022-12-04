package Scrapper.GUI;

import WidlerSuite.*;
import java.awt.*;
import java.util.*;
import Scrapper.Map.*;
import Scrapper.Item.*;
import Scrapper.Actor.*;
import Scrapper.Engine.*;

public class MapPanel extends RogueTilePanel implements GUIConstants
{	
   public MapPanel(int w, int h, TilePalette p)
   {
      super(w, h, p);
      setSizeMultiplier(DEFAULT_TILE_SIZE_MULTIPLIER);
      int sizePixels = MAP_PANEL_SIZE_TILES * DEFAULT_TILE_SIZE;
      setSize(sizePixels, sizePixels);
      setVisible(true);
   }
   
   @Override
   public void paint(Graphics g)
   {
      update();
      super.paint(g);
   }
   
   public void update()
   {
      SActor player = SEngine.getPlayer();
      Zone zone = SEngine.getCurZone();
      Vector<SActor> actorList = SEngine.getActorList();
      
      // blank screen and early exit if not enough info
      if(zone == null || player == null)
      {
         for(int x = 0; x < MAP_PANEL_SIZE_TILES; x++)
         for(int y = 0; y < MAP_PANEL_SIZE_TILES; y++)
            setIcon(x, y, ' ');
         return;
      }
      
      // draw map
      int cornerX = player.getX() - MAP_PANEL_CENTER;
      int cornerY = player.getY() - MAP_PANEL_CENTER;
      for(int x = 0; x < MAP_PANEL_SIZE_TILES; x++)
      for(int y = 0; y < MAP_PANEL_SIZE_TILES; y++)
      {
         MapTile tile;
         int iconIndex;
         Color fgColor;
         Color bgColor;
         Item item = null;
         if(player.canSee(x + cornerX, y + cornerY))
         {
            // map
            tile = zone.getTile(x + cornerX, y + cornerY);

            // item
            if(zone.isItemAt(x + cornerX, y + cornerY))
            {
               item = zone.getItemAt(x + cornerX, y + cornerY);
            }
         }
         else
         {
            tile = zone.getOOBTile();
         }
         iconIndex = tile.getTileBase().iconIndex;
         fgColor = tile.getFGColor();
         bgColor = tile.getBGColor();
         if(item != null)
         {
            iconIndex = item.getSprite().getIconIndex();
            fgColor = new Color(item.getSprite().getFGColor());
         }
         setTile(x, y, iconIndex, fgColor, bgColor);
      }
      
      // actors
      for(SActor actor : actorList)
      {
         UnboundTile actorTile = actor.getSprite();
         if(player.canSee(actor.getX(), actor.getY()))
         {
            setTile(actor.getX() - cornerX, actor.getY() - cornerY, actorTile.getIconIndex(), actorTile.getFGColor(), actorTile.getBGColor());
         }
      }
   }
}