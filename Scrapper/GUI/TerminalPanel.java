package Scrapper.GUI;

import WidlerSuite.*;


public class TerminalPanel extends TextPanel implements GUIConstants
{
   public TerminalPanel(SFullPanel par, TilePalette pal)
   {
      super(par, pal);
      write(0, 0, "Terminal Panel", 20, 1);
   }
}