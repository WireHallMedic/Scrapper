package Scrapper.GUI;

import WidlerSuite.*;
import java.awt.*;

public class CharacterPanel extends RogueTilePanel implements GUIConstants
{
   public CharacterPanel(int w, int h, TilePalette p)
   {
      super(w, h, p);
      setSizeMultiplier(DEFAULT_TILE_SIZE_MULTIPLIER);
      int widthPixels = SIDE_PANEL_HEIGHT_TILES * DEFAULT_TILE_SIZE;
      setSize(SIDE_PANEL_WIDTH_TILES * DEFAULT_TILE_SIZE / 2, SIDE_PANEL_HEIGHT_TILES * DEFAULT_TILE_SIZE);
      setBorder();
      write(1, 1, "  Character Panel", SECONDARY_COLOR.getRGB(), DEFAULT_BACKGROUND_COLOR.getRGB(), 18, 1);
      setVisible(true);
      
      
      
      // bars
      int maxBarVal = 16;
      int segments = 10;
      for(int i = 0; i <= maxBarVal; i++)
      {
         int[] arr = GUITools.getBarIcons(i, maxBarVal, segments);
         for(int j = 0; j < segments; j++)
            setTile(1 + j, 2 + i, arr[j], PRIMARY_COLOR, SECONDARY_COLOR);
      }
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
         setTile(0, y, vertBar, PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
      }
      setTile(0, 0, getPalette().flatten(10, 13), PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
      setTile(0, SIDE_PANEL_HEIGHT_TILES - 1, getPalette().flatten(0, 12), PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
   }
   
   public void paint(Graphics g)
   {
      setBorder();
      super.paint(g);
   }
}