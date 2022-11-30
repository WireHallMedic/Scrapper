package Scrapper.GUI;

import WidlerSuite.*;
import java.util.*;
import java.awt.*;

public class InfoPanel extends RogueTilePanel implements GUIConstants
{
   private static String newMessage = null;
   private static Vector<String> messageList = new Vector<String>();
   private static final int MAX_MESSAGES_DISPLAYED = 3;
   
   public static void addMessage(String msg){newMessage = msg;}
   
   private int writeOriginX = 1;
   private int writeOriginY = 0;
   
   public InfoPanel(int w, int h, TilePalette p)
   {
      super(w, h, p);
      setSizeMultiplier(DEFAULT_TILE_SIZE_MULTIPLIER);
      setSize(TEXT_PANEL_TILES_WIDE * DEFAULT_TILE_SIZE / 2, INFO_PANEL_TILES_HIGH * DEFAULT_TILE_SIZE);
      write(1, 0, "    Info Panel", SECONDARY_COLOR.getRGB(), DEFAULT_BACKGROUND_COLOR.getRGB(), 18, 1);
      setBorder();
      for(int i = 0; i < MAX_MESSAGES_DISPLAYED; i++)
         messageList.add("");
      setVisible(true);
   }
   
   public void setBorder()
   {
      int vertBar = getPalette().flatten(3, 11);
      int horizBar = getPalette().flatten(4, 12);
      for(int x = 0; x < TEXT_PANEL_TILES_WIDE; x++)
      {
         setTile(x, INFO_PANEL_TILES_HIGH - 1, horizBar, PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
      }
      for(int y = 0; y < INFO_PANEL_TILES_HIGH; y++)
      {
         setTile(TEXT_PANEL_TILES_WIDE - 1, y, vertBar, PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
         setTile(0, y, vertBar, PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
      }
      setTile(0, INFO_PANEL_TILES_HIGH - 1, getPalette().flatten(0, 12), PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
      setTile(TEXT_PANEL_TILES_WIDE - 1, INFO_PANEL_TILES_HIGH - 1, getPalette().flatten(9, 13), PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
   }
   
   private void clearMessageBox()
   {
      for(int x = writeOriginX; x < columns() - 1; x++)
      for(int y = writeOriginY; y < rows() - 1; y++)
         setTile(x, y, ' ', DEFAULT_FOREGROUND_COLOR, DEFAULT_BACKGROUND_COLOR);
   }
   
   private void postMessage()
   {
      if(newMessage != null)
      {
         messageList.insertElementAt(newMessage, 0);
         newMessage = null;
         messageList.removeElementAt(MAX_MESSAGES_DISPLAYED);
         clearMessageBox();
         for(int i = 0; i < MAX_MESSAGES_DISPLAYED; i++)
            write(writeOriginX, writeOriginY + i, messageList.elementAt(i), columns() - 2, 1);
      }
   }
   
   @Override
   public void paint(Graphics g)
   {
      postMessage();
      setBorder();
      super.paint(g);
   }
}