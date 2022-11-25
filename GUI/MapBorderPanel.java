package Scrapper.GUI;

import WidlerSuite.*;
import java.awt.*;

public class MapBorderPanel extends RogueTilePanel implements GUIConstants
{
   public MapBorderPanel(int w, int h, TilePalette p)
   {
      super(w, h, p);
      setSizeMultiplier(DEFAULT_TILE_SIZE_MULTIPLIER);
      int sizePixels = MAP_BORDER_PANEL_SIZE_TILES * DEFAULT_TILE_SIZE;
      setSize(sizePixels, sizePixels);
      setBorder();
      setBackground(Color.BLUE);
      setVisible(true);
   }
   
   public void setBorder()
   {
      int vertBar = getPalette().flatten(3, 11);
      int hoirzBar = getPalette().flatten(4, 12);
      int wneIntersection = getPalette().flatten(1, 12);
      int wseIntersection = getPalette().flatten(2, 12);
      int edgeIndex =  MAP_BORDER_PANEL_SIZE_TILES - 1;
      for(int i = 0; i < MAP_BORDER_PANEL_SIZE_TILES; i++)
      {
         setTile(0, i, vertBar, PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
         setTile(edgeIndex, i, vertBar, PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
         setTile(i, 0, hoirzBar, PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
         setTile(i,edgeIndex, hoirzBar, PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
      }
      setTile(0, 0, wseIntersection, PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
      setTile(0, edgeIndex, wneIntersection, PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
      setTile(edgeIndex, 0, wseIntersection, PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
      setTile(edgeIndex, edgeIndex, wneIntersection, PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
   }
}