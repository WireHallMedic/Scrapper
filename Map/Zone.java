package Scrapper.Map;

public class Zone implements MapConstants
{
   private TileBase[][] tile;
	private TileBase oobTile;


	public TileBase getOobTile(){return oobTile;}


	public void setOobTile(TileBase o){oobTile = o;}

   public Zone(int width, int height)
   {
      tile = new TileBase[width][height];
      for(int x = 0; x < width; x++)
      for(int y = 0; y < height; y++)
      {
         tile[x][y] = TileBase.CLEAR;
      }
      oobTile = TileBase.NULL;
   }
   
   public static Zone getMock()
   {
      int size = 15;
      Zone z = new Zone(size, size);
      for(int i = 0; i < size; i++)
      {
         z.tile[i][0] = TileBase.HIGH_WALL;
         z.tile[i][size - 1] = TileBase.HIGH_WALL;
         z.tile[0][i] = TileBase.HIGH_WALL;
         z.tile[size - 1][i] = TileBase.HIGH_WALL;
      }
      return z;
   }
   
   public int width()
   {
      return tile.length;
   }
   
   public int height()
   {
      return tile[0].length;
   }
   
   public boolean isInBounds(int x, int y)
   {
      return x >= 0 &&
             y >= 0 &&
             x < width() &&
             y < height();
   }
   
   public void setTile(int x, int y, TileBase t)
   {
      if(isInBounds(x, y))
         tile[x][y] = t;
   }
   
   public TileBase getTile(int x, int y)
   {
      if(isInBounds(x, y))
         return tile[x][y];
      return oobTile;
   }
}