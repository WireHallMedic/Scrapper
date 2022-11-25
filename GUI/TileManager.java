package Scrapper.GUI;

import WidlerSuite.*;

public class TileManager
{
   public static TilePalette x2y2Palette = null;
   public static TilePalette xy2Palette = null;
   
   public static void loadPalettes(String x2y2FileName, String xy2FileName)
   {
      x2y2Palette = new TilePalette(x2y2FileName, 16, 16);
      xy2Palette = new TilePalette(xy2FileName, 16, 16);
   }
}