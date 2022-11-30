package Scrapper.GUI;

import java.awt.Color;

public interface GUIConstants
{
   public static final int DEFAULT_BASE_TILE_SIZE = 16;
   public static final int DEFAULT_TILE_SIZE_MULTIPLIER = 2;
   public static final int DEFAULT_TILE_SIZE = DEFAULT_BASE_TILE_SIZE * DEFAULT_TILE_SIZE_MULTIPLIER;
   public static final int TILES_WIDE = 41;  // in x2y2 tiles
   public static final int TILES_HIGH = 25;
   public static final int DEFAULT_PANEL_WIDTH = DEFAULT_TILE_SIZE * TILES_WIDE;
   public static final int DEFAULT_PANEL_HEIGHT = DEFAULT_TILE_SIZE * TILES_HIGH;
   public static final int DEFAULT_FRAME_WIDTH = DEFAULT_PANEL_WIDTH + 20;
   public static final int DEFAULT_FRAME_HEIGHT = DEFAULT_PANEL_HEIGHT + 50;
   
   public static final int MAP_BORDER_PANEL_SIZE_TILES = TILES_HIGH - 4;
   public static final int MAP_BORDER_PANEL_SIZE = DEFAULT_TILE_SIZE * MAP_BORDER_PANEL_SIZE_TILES;
   public static final int MAP_PANEL_SIZE_TILES = MAP_BORDER_PANEL_SIZE_TILES - 2;
   public static final int SIDE_PANEL_WIDTH_TILES = TILES_WIDE - MAP_BORDER_PANEL_SIZE_TILES; // double width due to xy2
   public static final int SIDE_PANEL_HEIGHT_TILES = MAP_BORDER_PANEL_SIZE_TILES;
   public static final int MAP_PANEL_CENTER = (MAP_PANEL_SIZE_TILES / 2);
   public static final int TEXT_PANEL_TILES_WIDE = TILES_WIDE * 2;
   public static final int INFO_PANEL_TILES_HIGH = 4;
   
   public static final Color DEFAULT_BACKGROUND_COLOR = Color.BLACK;
   public static final Color DEFAULT_FOREGROUND_COLOR = Color.WHITE;
   // cyan split-complementary color scheme
   public static final Color PRIMARY_COLOR = new Color(0x00FFFF);       // cyan
   public static final Color SECONDARY_COLOR = new Color(0xFF8000);     // orange
   public static final Color TERTIARY_COLOR = new Color(0xFF0080);      // magenta
   public static final Color DEFAULT_FLOOR_COLOR = new Color(0x101010);
   
   public static final int TIMER_INTERVAL = 1000 / 30;   // milliseconds
}