package Scrapper.Item;

import Scrapper.GUI.*;
import java.awt.*;

public class ItemFactory implements ItemConstants, GUIConstants
{
   public static Item getMockItem()
   {
      Item item = new Item("Mock Item", '$');
      return item;
   }
   
   public static Item getQuestItem(QuestItem questItemConstant)
   {
      Item item = new Item(questItemConstant.name, QUEST_ITEM_ICON_INDEX);
      item.setQuestItemTag(questItemConstant);
      return item;
   }
}