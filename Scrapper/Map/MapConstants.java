package Scrapper.Map;

public interface MapConstants
{
   public enum TileBase
   {
      NULL              (' ', false, false, false, false),
      VOID              (' ', true, false, true, false),
      CLEAR             (250, true, true, true, false),     // centered dot
      LOW_DIFFICULT     (',', true, true, true, false),
      HIGH_WALL         ('#', false, false, false, false),
      LOW_WALL          ('=', true, false, true, false),
      SHALLOW_LIQUID    ('~', true, false, true, true),
      DEEP_LIQUID       (247, true, false, true, true),     // approximate sign
      BARS              (':', true, false, false, false),
      CLOSED_DOOR       ('|', false, false, false, false),
      OPEN_DOOR         ('/', true, true, true, false),
      UNFLIPPED_SWITCH  ('!', true, false, false, false),
      FLIPPED_SWITCH    (173, true, false, false, false),   // inverted !
      TERMINAL          (234, false, false, true, false),   // omega
      EXIT              ('&', true, true, true, false);    
            
      public int iconIndex;
      public boolean transparent;
      public boolean lowPassable;
      public boolean highPassable;
      public boolean liquid;
      
      private TileBase(int ii, boolean t, boolean lp, boolean hp, boolean l)
      {
         iconIndex = ii;
         transparent = t;
         lowPassable = lp;
         highPassable = hp;
         liquid = l;
      }
   }
   
   public enum Direction
   {
      NORTH       (0, -1),
      NORTHEAST   (1, -1),
      EAST        (1, 0),
      SOUTHEAST   (1, 1),
      SOUTH       (0, 1),
      SOUTHWEST   (-1, 1),
      WEST        (-1, 0),
      NORTHWEST   (-1, -1),
      ORIGIN      (0, 0);
      
      public int x;
      public int y;
      
      private Direction(int _x, int _y)
      {
         x = _x;
         y = _y;
      }
   }
   
   public enum Region
   {
      SECURITY       ("Security"),
      QUARTERS       ("Quarters"),
      ENGINEERING    ("Engineering"),
      LABS           ("Labs"),
      DISTROBUTION   ("Distrobution"),
      BRIDGE         ("Bridge"),
      TRANSIT        ("Transit"),
      DOCKS          ("Docks");
      
      public String name;
      
      private Region(String n)
      {
         name = n;
      }
   }
}