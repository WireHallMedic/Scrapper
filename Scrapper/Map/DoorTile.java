package Scrapper.Map;

import Scrapper.Item.*;
import Scrapper.Actor.*;
import java.util.*;


public class DoorTile extends ToggleTile implements ItemConstants
{
	private boolean locked;
	private QuestItem lockedBy;
	private boolean automatic;


	public boolean isLocked(){return locked;}
	public QuestItem getLockedBy(){return lockedBy;}
	public boolean isAutomatic(){return automatic;}


	public void setLocked(boolean l){locked = l;}
	public void setLockedBy(QuestItem l){lockedBy = l;}
	public void setAutomatic(boolean a){automatic = a;}


   public DoorTile()
   {
      super(TileBase.CLOSED_DOOR, TileBase.OPEN_DOOR);
      locked = false;
      lockedBy = null;
   }
   
   @Override
   public void toggle()
   {
      if(!locked)
         super.toggle();
   }
   
   public void open()
   {
      if(!locked)
      {
         if(activeState == aState)
            toggle();
      }
   }
   
   public void close()
   {
      if(!locked)
      {
         if(activeState == bState)
            toggle();
      }
   }
   
   public void forceToggle()
   {
      super.toggle();
   }
   
   public boolean requiresQuestItem()
   {
      return lockedBy != null;
   }
   
   public void attemptToUnlock(SActor a)
   {
   
   }
}