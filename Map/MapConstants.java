package Scrapper.Map;

public interface MapConstants
{
   public enum TileBase
   {
      NULL              (' ', false, false, false),
      VOID              (' ', true, false, true),
      CLEAR             (250, true, true, true),   // centered dot
      HIGH_WALL         ('#', false, false, false),
      LOW_WALL          ('=', true, false, true),
      SHALLOW_LIQUID    ('~', true, false, true),
      DEEP_LIQUID       (247, true, false, true),  // approximate sign
      BARS              (':', true, false, false),
      CLOSED_DOOR       ('|', false, false, false),
      OPEN_DOOR         ('_', true, true, true),
      UNFLIPPED_SWITCH  ('\\', false, false, false),
      FLIPPED_SWITCH    ('/', true, true, true),
      TERMINAL          (234, false, false, true);  // omega
      
      public boolean transparent;
      public boolean lowPassable;
      public boolean highPassable;
      public int iconIndex;
      
      private TileBase(int ii, boolean t, boolean lp, boolean hp)
      {
         iconIndex = ii;
         transparent = t;
         lowPassable = lp;
         highPassable = hp;
      }
   }
}