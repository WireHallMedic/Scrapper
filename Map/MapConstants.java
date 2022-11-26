package Scrapper.Map;

public interface MapConstants
{
   public enum TileBase
   {
      NULL              (' ', false, false, false, false),
      VOID              (' ', true, false, true, false),
      CLEAR             (250, true, true, true, false),      // centered dot
      HIGH_WALL         ('#', false, false, false, false),
      LOW_WALL          ('=', true, false, true, false),
      SHALLOW_LIQUID    ('~', true, false, true, true),
      DEEP_LIQUID       (247, true, false, true, true),     // approximate sign
      BARS              (':', true, false, false, false),
      CLOSED_DOOR       ('|', false, false, false, false),
      OPEN_DOOR         ('/', true, true, true, false),
      UNFLIPPED_SWITCH  ('!', false, false, false, false),
      FLIPPED_SWITCH    (173, true, true, true, false),      // inverted !
      TERMINAL          (234, false, false, true, false);    // omega
      
      public boolean transparent;
      public boolean lowPassable;
      public boolean highPassable;
      public boolean isLiquid;
      public int iconIndex;
      
      private TileBase(int ii, boolean t, boolean lp, boolean hp, boolean l)
      {
         iconIndex = ii;
         transparent = t;
         lowPassable = lp;
         highPassable = hp;
         isLiquid = l;
      }
   }
}