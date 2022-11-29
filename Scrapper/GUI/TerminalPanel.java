package Scrapper.GUI;

import WidlerSuite.*;
import Scrapper.Map.*;


public class TerminalPanel extends TextPanel implements GUIConstants
{
   public TerminalPanel(SFullPanel par, TilePalette pal)
   {
      super(par, pal);
      write(0, 0, "Terminal Panel", 20, 1);
      for(int x = 0; x < columns(); x++)
      for(int y = 0; y < rows(); y++)
         setFGColor(x, y, PRIMARY_COLOR.getRGB());
   }
   
   public void set(String str)
   {
      for(int x = 0; x < columns(); x++)
      for(int y = 0; y < rows(); y++)
         setIcon(x, y, ' ');
      write(0, 0, str, columns(), rows());
   }
}