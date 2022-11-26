package Scrapper.GUI;

import javax.swing.*;
import java.awt.*;

/*
   A standard JPanel so that the RogueTilePanels can stay a specific size
*/

public class SFullPanel extends JPanel implements GUIConstants
{
   private MainGamePanel mainGamePanel;
   
   public SFullPanel()
   {
      super();
      setSize(DEFAULT_PANEL_WIDTH, DEFAULT_PANEL_HEIGHT);
      setBackground(DEFAULT_BACKGROUND_COLOR);
      setLayout(null);
      
      mainGamePanel = new MainGamePanel(this);
      mainGamePanel.setLocation(0, 0);
      this.add(mainGamePanel);
      
      setVisible(true);
   }
}