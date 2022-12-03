package Scrapper.Item;

import WidlerSuite.*;
import Scrapper.GUI.*;

public class Item implements ItemConstants
{
	private UnboundTile sprite;
	private String name;
	private QuestItem questItemTag;


	public UnboundTile getSprite(){return sprite;}
	public String getName(){return name;}
	public boolean isQuestItem(){return questItemTag != null;}
	public QuestItem getQuestItemTag(){return questItemTag;}


	public void setSprite(UnboundTile s){sprite = s;}
	public void setName(String n){name = n;}
	public void setQuestItemTag(QuestItem q){questItemTag = q;}


   public Item()
   {
      sprite = new UnboundTile(TileManager.x2y2Palette);
      name = "Unknown Item";
      sprite.setIconIndex('?');
      questItemTag = null;
   }
   
   public Item(String name, int iconIndex)
   {
      this();
      setName(name);
      getSprite().setIconIndex(iconIndex);
   }
}