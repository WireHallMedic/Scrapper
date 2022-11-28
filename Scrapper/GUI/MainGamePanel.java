package Scrapper.GUI;

import WidlerSuite.*;
import javax.swing.*;
import java.awt.*;

public class MainGamePanel extends JPanel implements GUIConstants
{
   private SFullPanel parent;
   private CharacterPanel characterPanel;
   private InfoPanel infoPanel;
   private MapPanel mapPanel;
   private MapBorderPanel mapBorderPanel;
   private TilePalette bigPalette;
   private TilePalette smallPalette;
   
   public MainGamePanel(SFullPanel p, TilePalette tpBig, TilePalette tpSmall)
   {
      super();
      parent = p;
      setSize(parent.getWidth(), parent.getHeight());
      setLayout(null);
      setBackground(DEFAULT_BACKGROUND_COLOR);
      bigPalette = tpBig;
      smallPalette = tpSmall;
      
      int sidePanelHeight = TILES_HIGH *DEFAULT_TILE_SIZE;
      int sidePanelWidth = SIDE_PANEL_WIDTH_TILES * (DEFAULT_TILE_SIZE / 2);
      
      mapPanel = new MapPanel(MAP_PANEL_SIZE_TILES, MAP_PANEL_SIZE_TILES, bigPalette);
      mapPanel.setLocation(sidePanelWidth + DEFAULT_TILE_SIZE, DEFAULT_TILE_SIZE);
      this.add(mapPanel);
      
      mapBorderPanel = new MapBorderPanel(MAP_BORDER_PANEL_SIZE_TILES, MAP_BORDER_PANEL_SIZE_TILES, tpBig);
      mapBorderPanel.setLocation(sidePanelWidth, 0);
      this.add(mapBorderPanel);
      
      characterPanel = new CharacterPanel(SIDE_PANEL_WIDTH_TILES, SIDE_PANEL_HEIGHT_TILES, tpSmall);
      characterPanel.setSizeMultiplier(DEFAULT_TILE_SIZE_MULTIPLIER);
      characterPanel.setLocation(0, 0);
      characterPanel.setSize(sidePanelWidth, sidePanelHeight);
      this.add(characterPanel);
      characterPanel.setVisible(true);
      
      infoPanel = new InfoPanel(SIDE_PANEL_WIDTH_TILES, SIDE_PANEL_HEIGHT_TILES, tpSmall);
      infoPanel.setSizeMultiplier(DEFAULT_TILE_SIZE_MULTIPLIER);
      infoPanel.setLocation(this.getWidth() - sidePanelWidth, 0);
      infoPanel.setSize(sidePanelWidth, sidePanelHeight);
      this.add(infoPanel);
      infoPanel.setVisible(true);
      
   }
}