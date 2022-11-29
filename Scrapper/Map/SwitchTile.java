package Scrapper.Map;

public class SwitchTile extends ToggleTile
{
   private int eventID;
	private boolean multipleToggles;


	public int getEventID(){return eventID;}
	public boolean isMultipleToggles(){return multipleToggles;}


	public void setEventID(int e){eventID = e;}
	public void setMultipleToggles(boolean m){multipleToggles = m;}

   public SwitchTile()
   {
      this(-1);
   }
   
   public SwitchTile(int eid)
   {
      super(TileBase.UNFLIPPED_SWITCH, TileBase.FLIPPED_SWITCH);
      eventID = eid;
      multipleToggles = true;
   }
   
   @Override
   public void toggle()
   {
      if(activeState == aState || multipleToggles)
         super.toggle();
   }
}