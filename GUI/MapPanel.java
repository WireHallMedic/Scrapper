package Scrapper.GUI;

import WidlerSuite.*;
import java.awt.*;
import Scrapper.Map.*;
import Scrapper.Actor.*;
import Scrapper.Engine.*;

public class MapPanel extends RogueTilePanel implements GUIConstants
{	
   private Zone zone;


	public Zone getZone(){return zone;}


	public void setZone(Zone z){zone = z;}


   public MapPanel(int w, int h, TilePalette p)
   {
      super(w, h, p);
      setSizeMultiplier(DEFAULT_TILE_SIZE_MULTIPLIER);
      int sizePixels = MAP_PANEL_SIZE_TILES * DEFAULT_TILE_SIZE;
      setSize(sizePixels, sizePixels);
      zone = Zone.getMock();
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
      if(zone == null || player == null)
      {
         for(int x = 0; x < MAP_PANEL_SIZE_TILES; x++)
         for(int y = 0; y < MAP_PANEL_SIZE_TILES; y++)
            setIcon(x, y, ' ');
      }
      else
      {
         int cornerX = player.getX() - MAP_PANEL_CENTER;
         int cornerY = player.getY() - MAP_PANEL_CENTER;
         for(int x = 0; x < MAP_PANEL_SIZE_TILES; x++)
         for(int y = 0; y < MAP_PANEL_SIZE_TILES; y++)
            setIcon(x, y, zone.getTile(x + cornerX, y + cornerY).iconIndex);
         setIcon(player.getX() - cornerX, player.getY() - cornerY, player.getIconIndex());
      }
   }
}