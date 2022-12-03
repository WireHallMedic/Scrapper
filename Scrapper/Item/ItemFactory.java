package Scrapper.Item;

import Scrapper.GUI.*;
import java.awt.*;

public class ItemFactory implements ItemConstants, GUIConstants
{
   public static Item getQuestItem(QuestItem questItemConstant)
   {
      Item item = new Item(String questItemConstant.name, QUEST_ITEM_ICON_INDEX));
      item.setQuestItemTag(questItemConstant);
   }
}