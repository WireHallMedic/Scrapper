package Scrapper.Item;

import java.util.*;
import Scrapper.Actor.*;

public class Inventory implements ItemConstants
{
   private SActor self;
   private Vector<Item> itemList;
   private Vector<Item> questItemList;
   
   public Inventory(SActor s)
   {
      self = s;
      itemList = new Vector<Item>();
      questItemList = new Vector<Item>();
   }
   
   public void add(Item i)
   {
      if(i.isQuestItem())
         questItemList.add(i);
      else
         itemList.add(i);
   }
   
   public boolean hasQuestItem(QuestItem qi)
   {
      for(Item item : questItemList)
      {
         if(item.getQuestItemTag() == qi)
            return true;
      }
      return false;
   }
   
}