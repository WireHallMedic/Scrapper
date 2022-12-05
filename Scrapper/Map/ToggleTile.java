package Scrapper.Map;

import java.awt.*;

public class ToggleTile extends MapTile implements MapConstants
{
   protected MapTile aState;
   protected MapTile bState;
   protected MapTile activeState;
   
   public TileBase getTileBase(){return activeState.getTileBase();}
	public Color getFGColor(){return activeState.getFGColor();}
	public Color getBGColor(){return activeState.getBGColor();}
   
   public ToggleTile(TileBase aTB, TileBase bTB)
   {
      super(aTB);
      aState = new MapTile(aTB);
      bState = new MapTile(bTB);
      activeState = aState;
   }
   
   public ToggleTile(MapTile aMT, MapTile bMT)
   {
      super(aMT);
      aState = new MapTile(aMT);
      bState = new MapTile(bMT);
      activeState = aState;
   }
   
   public void toggle()
   {
      if(activeState == aState)
         activeState = bState;
      else
         activeState = aState;
   }
   
   @Override
   public int getIconIndex()
   {
      return activeState.getIconIndex();
   }
}