package Scrapper.GUI;

import WidlerSuite.*;


public class TextPanel extends RogueTilePanel implements GUIConstants
{
   protected SFullPanel parent;
   
   public TextPanel(SFullPanel par, TilePalette pal)
   {
      super(TEXT_PANEL_TILES_WIDE, TILES_HIGH, pal);
      parent = par;
      setSizeMultiplier(DEFAULT_TILE_SIZE_MULTIPLIER);
      setSize(parent.getWidth(), parent.getHeight());
      setVisible(true);
   }
   
   public void clear()
   {
      for(int x = 0; x < TEXT_PANEL_TILES_WIDE; x++)
      for(int y = 0; y < TILES_HIGH; y++)
      {
         setTile(x, y, ' ', DEFAULT_FOREGROUND_COLOR.getRGB(), DEFAULT_BACKGROUND_COLOR.getRGB());
      }
   }
}