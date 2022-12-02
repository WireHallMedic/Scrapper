package Scrapper.Item;

import WidlerSuite.*;
import Scrapper.GUI.*;

public class Item
{
	private UnboundTile sprite;
	private String name;
	private boolean questItem;


	public UnboundTile getSprite(){return sprite;}
	public String getName(){return name;}
	public boolean isQuestItem(){return questItem;}


	public void setSprite(UnboundTile s){sprite = s;}
	public void setName(String n){name = n;}
	public void setQuestItem(boolean q){questItem = q;}


   public Item()
   {
      sprite = new UnboundTile(TileManager.x2y2Palette);
      name = "Unknown Item";
      sprite.setIconIndex('?');
      questItem = false;
   }
   
   public Item(String name, int iconIndex)
   {
      this();
      setName(name);
      getSprite().setIconIndex(iconIndex);
   }
}