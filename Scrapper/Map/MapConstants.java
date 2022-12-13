package Scrapper.Map;

public interface MapConstants
{
   public enum TileBase
   {
      NULL              (' ', "", false, false, false, false),
      VOID              (' ', "Pit", true, false, true, false),
      CLEAR             (250, "Clear", true, true, true, false),     // centered dot
      LOW_DIFFICULT     (',', "Rubble", true, true, true, false),
      HIGH_WALL         ('#', "Wall", false, false, false, false),
      LOW_WALL          ('=', "Low Wall", true, false, true, false),
      SHALLOW_LIQUID    ('~', "Shallow Water", true, false, true, true),
      DEEP_LIQUID       (247, "Deep Water", true, false, true, true),     // approximate sign
      BARS              (':', "Bars", true, false, false, false),
      CLOSED_DOOR       ('|', "Door", false, false, false, false),
      OPEN_DOOR         ('/', "Door", true, true, true, false),
      UNFLIPPED_SWITCH  ('!', "Switch", true, false, false, false),
      FLIPPED_SWITCH    (173, "Switch", true, false, false, false),   // inverted !
      TERMINAL          (234, "Terminal", false, false, true, false),   // omega
      EXIT              ('&', "Exit", true, true, true, false);    
      
      public String name;
      public int iconIndex;
      public boolean transparent;
      public boolean lowPassable;
      public boolean highPassable;
      public boolean liquid;
      
      private TileBase(int ii, String n, boolean t, boolean lp, boolean hp, boolean l)
      {
         name = n;
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
      
      public Direction opposite()
      {
         switch(this)
         {
            case NORTH     : return SOUTH;
            case NORTHEAST : return SOUTHWEST;
            case EAST      : return WEST;
            case SOUTHEAST : return NORTHWEST;
            case SOUTH     : return NORTH;
            case SOUTHWEST : return NORTHEAST;
            case WEST      : return EAST;
            case NORTHWEST : return SOUTHEAST;
            case ORIGIN    : return ORIGIN;
         }  
         return null;
      }
   }
   
   public enum Region
   {
      DOCKS          ("Docks"),
      DISTRIBUTION   ("Distribution"),
      TRANSIT        ("Transit"),
      QUARTERS       ("Quarters"),
      SECURITY       ("Security"),
      ENGINEERING    ("Engineering"),
      LABS           ("Labs"),
      OPERATIONS     ("Operations"),
      BRIG           ("Security - Brig"),
      REACTOR        ("Engineering - Reactor"),
      SECURE_LABS    ("Labs - Secure Labs"),
      BRIDGE         ("Operations - Bridge"),
      SPECIAL        ("Special");
      
      public String name;
      
      private Region(String n)
      {
         name = n;
      }
   }
}