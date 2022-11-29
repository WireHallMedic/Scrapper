package Scrapper.Map;

public class ExitTile extends MapTile implements MapConstants
{
   private int pathNum;


	public int getPathNum(){return pathNum;}


	public void setPathNum(int p){pathNum = p;}

   public ExitTile(int pn)
   {
      super(TileBase.EXIT);
      pathNum = pn;
   }
}