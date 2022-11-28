package Scrapper.GUI;

import javax.swing.*;
import java.awt.*;
import WidlerSuite.*;

/*
   A standard JPanel so that the RogueTilePanels can stay a specific size
*/

public class SFullPanel extends JPanel implements GUIConstants
{
   private MainGamePanel mainGamePanel;
   private TilePalette bigPalette;
   private TilePalette smallPalette;
   
   public SFullPanel()
   {
      super();
      setSize(DEFAULT_PANEL_WIDTH, DEFAULT_PANEL_HEIGHT);
      setBackground(DEFAULT_BACKGROUND_COLOR);
      setLayout(null);
      bigPalette = TileManager.x2y2Palette;
      smallPalette = TileManager.xy2Palette;
      
      mainGamePanel = new MainGamePanel(this, bigPalette, smallPalette);
      mainGamePanel.setLocation(0, 0);
      this.add(mainGamePanel);
      
      setVisible(true);
   }
}