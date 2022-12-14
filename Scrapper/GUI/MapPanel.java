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
   public static boolean refreshF = false;
   
   public static void updateActors(){refreshF = true;}
   
   public MapPanel(int w, int h, TilePalette p)
   {
      super(w, h, p);
      setSizeMultiplier(DEFAULT_TILE_SIZE_MULTIPLIER);
      int sizePixels = MAP_PANEL_SIZE_TILES * DEFAULT_TILE_SIZE;
      setSize(sizePixels, sizePixels);
      SEngine.setAnimationManager(animationManager);
      setBackground(Color.BLACK);
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
      if(refreshF)
      {
         refreshF = false;
         animationManager.clear();
         for(SActor actor : SEngine.getActorList())
         {
            addNonlocking(actor.getSprite());
            actor.stopAndCenter();
         }
      }
      SActor player = SEngine.getPlayer();
      Zone zone = SEngine.getCurZone();
      Vector<SActor> actorList = SEngine.getActorList();
      
      // cornerTile for unbound tiles
      setCornerTile(player.getX() - MAP_PANEL_CENTER, player.getY() - MAP_PANEL_CENTER);
      
      // blank screen and early exit if not enough info
      if(zone == null || player == null)
      {
         for(int x = 0; x < MAP_PANEL_SIZE_TILES; x++)
         for(int y = 0; y < MAP_PANEL_SIZE_TILES; y++)
            setIcon(x, y, ' ');
         return;
      }
      
      // draw map
      int cornerX = player.getTrueLoc().x - MAP_PANEL_CENTER;
      int cornerY = player.getTrueLoc().y - MAP_PANEL_CENTER;
      for(int x = 0; x < MAP_PANEL_SIZE_TILES; x++)
      for(int y = 0; y < MAP_PANEL_SIZE_TILES; y++)
      {
         STileObject tile;
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
         iconIndex = tile.getIconIndex();
         fgColor = tile.getFGColor();
         bgColor = tile.getBGColor();
         if(item != null)
         {
            iconIndex = item.getIconIndex();
            fgColor = item.getFGColor();
         }
         setTile(x, y, iconIndex, fgColor, bgColor);
      }
      double xScroll = -(player.getSprite().getXOffset());
      double yScroll = -(player.getSprite().getYOffset());
      setScroll(xScroll, yScroll);
   }
}