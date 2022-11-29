package Scrapper.Map;


public class DoorTile extends ToggleTile
{
	private boolean locked;


	public boolean isLocked(){return locked;}


	public void setLocked(boolean l){locked = l;}


   public DoorTile()
   {
      super(TileBase.CLOSED_DOOR, TileBase.OPEN_DOOR);
      locked = false;
   }
   
   @Override
   public void toggle()
   {
      if(!locked)
         super.toggle();
   }
   
   public void forceToggle()
   {
      super.toggle();
   }
}