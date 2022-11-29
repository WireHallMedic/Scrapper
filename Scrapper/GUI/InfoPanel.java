package Scrapper.GUI;

import WidlerSuite.*;
import java.util.*;
import java.awt.*;

public class InfoPanel extends RogueTilePanel implements GUIConstants
{
   private static String newMessage = null;
   private static Vector<String> messageList = new Vector<String>();
   
   public static void addMessage(String msg){newMessage = msg;}
   
   private int writeOriginX = 0;
   private int writeOriginY = 1;
   
   public InfoPanel(int w, int h, TilePalette p)
   {
      super(w, h, p);
      setSizeMultiplier(DEFAULT_TILE_SIZE_MULTIPLIER);
      int widthPixels = SIDE_PANEL_HEIGHT_TILES * DEFAULT_TILE_SIZE;
      setSize(SIDE_PANEL_WIDTH_TILES * DEFAULT_TILE_SIZE / 2, SIDE_PANEL_HEIGHT_TILES * DEFAULT_TILE_SIZE);
      setBorder();
      write(0, 1, "    Info Panel", SECONDARY_COLOR.getRGB(), DEFAULT_BACKGROUND_COLOR.getRGB(), 18, 1);
      setVisible(true);
   }
   
   public void setBorder()
   {
      int vertBar = getPalette().flatten(3, 11);
      int horizBar = getPalette().flatten(4, 12);
      for(int x = 0; x < SIDE_PANEL_WIDTH_TILES; x++)
      {
         setTile(x, 0, horizBar, PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
         setTile(x, SIDE_PANEL_HEIGHT_TILES - 1, horizBar, PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
      }
      for(int y = 0; y < SIDE_PANEL_HEIGHT_TILES; y++)
      {
         setTile(SIDE_PANEL_WIDTH_TILES - 1, y, vertBar, PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
      }
      setTile(SIDE_PANEL_WIDTH_TILES - 1, 0, getPalette().flatten(15, 11), PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
      setTile(SIDE_PANEL_WIDTH_TILES - 1, SIDE_PANEL_HEIGHT_TILES - 1, getPalette().flatten(9, 13), PRIMARY_COLOR, DEFAULT_BACKGROUND_COLOR);
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
         while(messageList.size() > 5)
            messageList.removeElementAt(5);
         String compiledString = "";
         for(String str : messageList)
         {
            compiledString += str + "\n\n";
         }
         clearMessageBox();
         write(writeOriginX, writeOriginY, compiledString, columns() - 1, rows() - 1);
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