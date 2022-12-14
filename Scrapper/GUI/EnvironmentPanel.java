package Scrapper.GUI;

import WidlerSuite.*;
import java.util.*;
import java.awt.*;

public class EnvironmentPanel extends RogueTilePanel implements GUIConstants
{
   public EnvironmentPanel(int w, int h, TilePalette p)
   {
      super(w, h, p);
      setSizeMultiplier(DEFAULT_TILE_SIZE_MULTIPLIER);
      int widthPixels = SIDE_PANEL_HEIGHT_TILES * DEFAULT_TILE_SIZE;
      setSize(SIDE_PANEL_WIDTH_TILES * DEFAULT_TILE_SIZE / 2, SIDE_PANEL_HEIGHT_TILES * DEFAULT_TILE_SIZE);
      setBorder();
      write(0, 1, "   Env Panel", SECONDARY_COLOR.getRGB(), DEFAULT_BACKGROUND_COLOR.getRGB(), 18, 1);
      setVisible(true);
   }
   
   public void setBorder()
   {
      int vertBar = getPalette().flatten(3, 11);
      int horizBar = getPalette().flatten(4, 12);
      for(int x = 0; x < SIDE_PANEL_WIDTH_TILES; x++)
      {
         setTile(x, 0, horizBar, PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
         setTile(x, SIDE_PANEL_HEIGHT_TILES - 1, horizBar, PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
      }
      for(int y = 0; y < SIDE_PANEL_HEIGHT_TILES; y++)
      {
         setTile(SIDE_PANEL_WIDTH_TILES - 1, y, vertBar, PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
      }
      setTile(SIDE_PANEL_WIDTH_TILES - 1, 0, getPalette().flatten(15, 11), PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
      setTile(SIDE_PANEL_WIDTH_TILES - 1, SIDE_PANEL_HEIGHT_TILES - 1, getPalette().flatten(4, 11), PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
   }
}