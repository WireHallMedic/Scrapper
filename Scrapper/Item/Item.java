package Scrapper.Item;

import WidlerSuite.*;
import Scrapper.GUI.*;
import java.awt.*;

public class Item implements ItemConstants, STileObject, GUIConstants
{
	private String name;
	private QuestItem questItemTag;
	private int iconIndex;
	private Color fgColor;
	private Color bgColor;


	public String getName(){return name;}
	public QuestItem getQuestItemTag(){return questItemTag;}
	public int getIconIndex(){return iconIndex;}
	public Color getFGColor(){return fgColor;}
	public Color getBGColor(){return bgColor;}


	public void setName(String n){name = n;}
	public void setQuestItemTag(QuestItem q){questItemTag = q;}
	public void setIconIndex(int i){iconIndex = i;}
	public void setFGColor(Color f){fgColor = f;}
	public void setBGColor(Color b){bgColor = b;}


   public Item()
   {
      name = "Unknown Item";
      setIconIndex('?');
      setFGColor(DEFAULT_FOREGROUND_COLOR);
      setBGColor(DEFAULT_BACKGROUND_COLOR);
      questItemTag = null;
   }
   
   public Item(String name, int iconIndex)
   {
      this();
      setName(name);
      setIconIndex(iconIndex);
   }
   
   public boolean isQuestItem(){return getQuestItemTag() != null;}
}