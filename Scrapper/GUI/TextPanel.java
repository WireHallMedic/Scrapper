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
}