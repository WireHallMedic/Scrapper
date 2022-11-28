package Scrapper.Map;

import Scrapper.GUI.*;
import Scrapper.Actor.*;

public class Zone implements MapConstants, GUIConstants
{
   private MapTile[][] tile;
	private MapTile oobTile;


	public MapTile getOobTile(){return oobTile;}


	public void setOobTile(MapTile o){oobTile = o;}

   public Zone(int width, int height)
   {
      tile = new MapTile[width][height];
      for(int x = 0; x < width; x++)
      for(int y = 0; y < height; y++)
      {
         tile[x][y] = new MapTile(TileBase.CLEAR);
      }
      oobTile = new MapTile(TileBase.NULL);
   }
   
   public int width()
   {
      return tile.length;
   }
   
   public int height()
   {
      return tile[0].length;
   }
   
   public boolean isInBounds(int x, int y)
   {
      return x >= 0 &&
             y >= 0 &&
             x < width() &&
             y < height();
   }
   
   public void setTile(int x, int y, MapTile t)
   {
      if(isInBounds(x, y))
         tile[x][y] = t;
   }
   
   public MapTile getTile(int x, int y)
   {
      if(isInBounds(x, y))
         return tile[x][y];
      return oobTile;
   }
   
   public boolean canStep(SActor actor, int x, int y)
   {
      return getTile(x, y).canStep(actor);
   }
   
   // testing method
   public static Zone getMock()
   {
      int size = 20;
      Zone z = new Zone(size, size);
      for(int i = 0; i < size; i++)
      {
         z.tile[i][0] = new MapTile(TileBase.HIGH_WALL);
         z.tile[i][size - 1] = new MapTile(TileBase.HIGH_WALL);
         z.tile[0][i] = new MapTile(TileBase.HIGH_WALL);
         z.tile[size - 1][i] = new MapTile(TileBase.HIGH_WALL);
      }
      for(int i = 0; i < TileBase.values().length; i++)
      {
         int xIndex = ((i % 7) + 1) * 2;
         int yIndex = ((i / 7) + 2) * 2;
         z.tile[xIndex][yIndex] = new MapTile(TileBase.values()[i]);
      }
      z.tile[5][4].setFGColor(TERTIARY_COLOR);
      z.tile[5][1] = MapTileFactory.getSwitch();
      z.tile[7][1] = MapTileFactory.getDoor();
      z.tile[9][1] = new TerminalTile();
      
      return z;
   }
}