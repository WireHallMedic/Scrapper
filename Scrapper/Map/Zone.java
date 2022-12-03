package Scrapper.Map;

import Scrapper.GUI.*;
import Scrapper.Actor.*;
import WidlerSuite.*;
import java.util.*;

public class Zone implements MapConstants, GUIConstants
{
   private MapTile[][] tile;
	private MapTile oobTile;
   private Vector<ExitLoc> exitList;
   private Vector<DoorTile> automaticDoorList;
   private String name;


	public MapTile getOOBTile(){return oobTile;}
   public String getName(){return name;}


	public void setOOBTile(MapTile o){oobTile = o;}
   public void setName(String n){name = n;}


   public Zone(int width, int height)
   {
      this(width, height, "Unknown Zone");
   }
   
   public Zone(int width, int height, String n)
   {
      tile = new MapTile[width][height];
      for(int x = 0; x < width; x++)
      for(int y = 0; y < height; y++)
      {
         tile[x][y] = new MapTile(TileBase.CLEAR);
      }
      oobTile = new MapTile(TileBase.NULL);
      exitList = new Vector<ExitLoc>();
      automaticDoorList = new Vector<DoorTile>();
      name = n;
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
   
   public boolean[][] getTransparencyArray(int xLoc, int yLoc, int rad)
   {
      int fullSize = rad + rad + 1;
      boolean arr[][] = new boolean[fullSize][fullSize];
      for(int x = 0; x < fullSize; x++)
      for(int y = 0; y < fullSize; y++)
      {
         arr[x][y] = getTile(x + xLoc - rad, y + yLoc - rad).isTransparent();
      }
      return arr;
   }
   
   public boolean hasExit(int pn)
   {
      for(ExitLoc el : exitList)
      {
         if(el.pathNum == pn)
            return true;
      }
      return false;
   }
   
   public Coord getExitLoc(int pn)
   {
      for(ExitLoc el : exitList)
      {
         if(el.pathNum == pn)
            return el.loc;
      }
      return null;
   }
   
   public void postProcess()
   {
      for(int x = 0; x < width(); x++)
      for(int y = 0; y < height(); y++)
      {
         if(getTile(x, y) instanceof ExitTile)
         {
            ExitTile exitTile = (ExitTile)getTile(x, y);
            exitList.add(new ExitLoc(exitTile.getPathNum(), x, y));
         }
         if(getTile(x, y) instanceof DoorTile)
         {
            DoorTile doorTile = (DoorTile)getTile(x, y);
            if(doorTile.isAutomatic())
               automaticDoorList.add(doorTile);
         }
      }
   }
   
   private class ExitLoc
   {
      public int pathNum;
      public Coord loc;
      
      public ExitLoc(int pn, int x, int y)
      {
         pathNum = pn;
         loc = new Coord(x, y);
      }
   }
   
   // testing methods
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
      
      z.tile[4][2] = new MapTile(TileBase.HIGH_WALL);
      z.tile[5][2] = new MapTile(TileBase.HIGH_WALL);
      z.tile[4][3] = new MapTile(TileBase.HIGH_WALL);
      z.tile[5][3] = new MapTile(TileBase.HIGH_WALL);
      
      z.tile[5][4].setFGColor(TERTIARY_COLOR);
      z.tile[5][1] = MapTileFactory.getSwitch(1);
      z.tile[7][1] = MapTileFactory.getDoor();
      z.tile[9][1] = new TerminalTile();
      z.tile[1][6] = new ExitTile(4);
      z.tile[1][7] = new ExitTile(5);
      z.postProcess();
      return z;
   }
   
   public static Zone getMock2()
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
      z.tile[5][5] = new ExitTile(4);
      
      z.tile[4][2] = new MapTile(TileBase.LOW_WALL);
      z.tile[5][2] = new MapTile(TileBase.LOW_WALL);
      z.tile[4][3] = new MapTile(TileBase.LOW_WALL);
      z.tile[5][3] = new MapTile(TileBase.LOW_WALL);
      
      z.postProcess();
      return z;
   }
   
   public static Zone getMock3()
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
      z.tile[3][3] = new ExitTile(5);
      
      z.tile[4][2] = new MapTile(TileBase.DEEP_LIQUID);
      z.tile[5][2] = new MapTile(TileBase.DEEP_LIQUID);
      z.tile[4][3] = new MapTile(TileBase.DEEP_LIQUID);
      z.tile[5][3] = new MapTile(TileBase.DEEP_LIQUID);
      
      z.postProcess();
      return z;
   }
}