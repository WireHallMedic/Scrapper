package Scrapper.GUI;

import java.awt.*;

public class GUITools implements GUIConstants
{
   public static final int[] BAR_ICONS = {0, flatten(6, 14), flatten(5, 14), flatten(4, 14), flatten(3, 14), 
                                        flatten(2, 14), flatten(1, 14), flatten(0, 14), flatten(11, 13), };
   
   public static int[] getBarIcons(int cur, int max, int segments)
   {
      int[] results = new int[segments];
      double ratio = (double)cur / (double)max;
      double scaledRatio = ratio * segments;
      int partialBarSegment = (int)scaledRatio;
      int partialBarFullness = (int)(((double)scaledRatio - (partialBarSegment)) * BAR_ICONS.length);
      
      for(int i = 0; i < segments; i++)
      {
         if(i + 1 <= (int)scaledRatio)
         {
            results[i] = BAR_ICONS[BAR_ICONS.length - 1];
         }
         else
         {
            results[i] = BAR_ICONS[0];
         }
      }
      if(partialBarSegment < segments)
         results[partialBarSegment] = BAR_ICONS[partialBarFullness];
      return results;
   }
   
   // expects a 16x16 tile palette
   private static int flatten(int x, int y)
   {
      return x + (y * 16);
   }
}