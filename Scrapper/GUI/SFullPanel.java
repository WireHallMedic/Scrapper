package Scrapper.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import WidlerSuite.*;
import Scrapper.Engine.*;
import Scrapper.Map.*;

/*
   A standard JPanel so that the RogueTilePanels can stay a specific size
*/

public class SFullPanel extends JPanel implements GUIConstants, ActionListener
{
   private MainGamePanel mainGamePanel;
   private TerminalPanel terminalPanel;
   private InventoryPanel inventoryPanel;
   private TilePalette bigPalette;
   private TilePalette smallPalette;
   private Vector<JPanel> panelList;
   private JPanel curPanel;
   
   public JPanel getCurPanel(){return curPanel;}
   
   public SFullPanel()
   {
      super();
      setSize(DEFAULT_PANEL_WIDTH, DEFAULT_PANEL_HEIGHT);
      setBackground(DEFAULT_BACKGROUND_COLOR);
      setLayout(null);
      bigPalette = TileManager.x2y2Palette;
      smallPalette = TileManager.xy2Palette;
      panelList = new Vector<JPanel>();
      SEngine.register(this);
      
      mainGamePanel = new MainGamePanel(this, bigPalette, smallPalette);
      mainGamePanel.setLocation(0, 0);
      this.add(mainGamePanel);
      panelList.add(mainGamePanel);
      
      terminalPanel = new TerminalPanel(this, smallPalette);
      terminalPanel.setLocation(0, 0);
      this.add(terminalPanel);
      panelList.add(terminalPanel);
      
      inventoryPanel = new InventoryPanel(this, smallPalette);
      inventoryPanel.setLocation(0, 0);
      this.add(inventoryPanel);
      panelList.add(inventoryPanel);
      
      setVisible(mainGamePanel);
      setVisible(true);
   }
   
   public void setMainGamePanelVisible(){setVisible(mainGamePanel);}
   public void setInventoryPanelVisible(){setVisible(inventoryPanel);}
   
   public void setTerminalPanelVisible(TerminalTile terminalTile)
   {
      terminalPanel.set(terminalTile.getMessage());
      setVisible(terminalPanel);
   }
   
   public void setVisible(JPanel panel)
   {
      curPanel = panel;
      for(JPanel panelElement : panelList)
      {
         if(panelElement == curPanel)
            panelElement.setVisible(true);
         else
            panelElement.setVisible(false);
      }
   }
   
   // timer kick
   public void actionPerformed(ActionEvent ae)
   {
      mainGamePanel.actionPerformed(ae);
      terminalPanel.actionPerformed(ae);
      inventoryPanel.actionPerformed(ae);
   }
}