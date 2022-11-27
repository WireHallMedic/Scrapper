package Scrapper.GUI;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import Scrapper.Engine.*;
import Scrapper.Actor.*;
import Scrapper.Ability.*;
import Scrapper.Map.*;
import Scrapper.AI.*;

public class SFrame extends JFrame implements GUIConstants, MapConstants, AbilityConstants, ActionListener, KeyListener
{
   private SFullPanel fullPanel;
   private JPanel innerPanel;       // deal with draw area, not full frame
   private javax.swing.Timer timer;
   
   public SFrame()
   {
      super();
      setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLayout(new GridLayout(1, 1));
      
      innerPanel = new JPanel();
      innerPanel.setLayout(null);
      innerPanel.setBackground(DEFAULT_BACKGROUND_COLOR);
      this.add(innerPanel);
      
      // initialize tile palettes
      TileManager.loadPalettes("Scrapper/GUI/WSFont_16x16.png", "Scrapper/GUI/WSFont_8x16.png");
      
      fullPanel = new SFullPanel();
      innerPanel.add(fullPanel);
      
      timer = new javax.swing.Timer(TIMER_INTERVAL, this);
      timer.start();
      
      this.addKeyListener(this);
      
      setVisible(true);
      this.repaint();
   }
   
   // timer kick
   public void actionPerformed(ActionEvent ae)
   {
      innerPanel.repaint();
   }
   
   @Override
   public void paint(Graphics g)
   {
      centerPanels();
      super.paint(g);
   }
   
   public void centerPanels()
   {
      int xInset = (innerPanel.getWidth() - fullPanel.getWidth()) / 2;
      int yInset = (innerPanel.getHeight() - fullPanel.getHeight()) / 2;
      fullPanel.setLocation(xInset, yInset);
   }
   
   public void keyReleased(KeyEvent ke){}
   public void keyTyped(KeyEvent ke){}
   public void keyPressed(KeyEvent ke)
   {
      InputHandler.handleKeyInput(ke);
   }
}