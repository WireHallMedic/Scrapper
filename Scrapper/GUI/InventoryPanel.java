package Scrapper.GUI;

import Scrapper.Actor.*;
import Scrapper. Item.*;
import Scrapper.Engine.*;
import WidlerSuite.*;
import java.util.*;

public class InventoryPanel extends TextPanel
{
   public InventoryPanel(SFullPanel par, TilePalette pal)
   {
      super(par, pal);
   }
   
   @Override
   public void setVisible(boolean v)
   {
      if(v)
         populate();
      super.setVisible();
   }
   
   public void populate()
   {
      Inventory inv = SEngine.getPlayer().getInventory();
      Vector<Item> itemList = inv.getItemList();
      Vector<Item> questItemList = inv.getQuestItemList();
      write(0, 0, "Inventory", 30, 1);
      write(30, 0, "Important Items", 20, 1);
      clear();
      for(int i = 0; i < itemList.size() && i < 30; i++)
         writeItem(0, 1 + i, itemList.elementAt(i));
      for(int i = 0; i < questItemList.size() && i < 30; i++)
         writeItem(0, 1 + i, questItemList.elementAt(i));
   }
   
   private void writeItem(int x, int y, Item item)
   {
      UnboundTile sprite = item.getSprite();
      setTile(x, y, sprite.getIconIndex(), sprite.getFGColor(), sprite.getBGColor());
      write(x + 2, y, item.getName(), sprite.getFGColor(), sprite.getBGColor());
   }
}