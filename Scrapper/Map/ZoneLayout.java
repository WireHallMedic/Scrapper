package Scrapper.Map;

import java.util.*;
import Scrapper.Engine.*;

public class ZoneLayout
{
   public static int engineeringHeight;
   public static int labsHeight;
   public static int quartersHeight;
   public static int securityHeight;
   public static int distributionHeight = 3;
   public static int opsHeight = 3;
   public static int reactorConnectionLevel;
   public static int brigConnectionLevel;
   public static int secLabConnectionLevel;
   public static int bridgeConnectionLevel;
   private static boolean hasBeenSet = set();
   
   public static boolean set()
   {
     Vector<Integer> heightList = new Vector<Integer>();
     heightList.add(3);
     heightList.add(4);
     heightList.add(4);
     heightList.add(5);
     
     int roll = SEngine.rng.nextInt() % heightList.size();
     engineeringHeight = heightList.elementAt(roll);
     heightList.removeElementAt(roll);
     
     roll = SEngine.rng.nextInt() % heightList.size();
     labsHeight = heightList.elementAt(roll);
     heightList.removeElementAt(roll);
     
     roll = SEngine.rng.nextInt() % heightList.size();
     quartersHeight = heightList.elementAt(roll);
     heightList.removeElementAt(roll);
     
     securityHeight = heightList.elementAt(0);
     
     reactorConnectionLevel = SEngine.rng.nextInt() % engineeringHeight;
     brigConnectionLevel = SEngine.rng.nextInt() % securityHeight;
     secLabConnectionLevel = SEngine.rng.nextInt() % labsHeight;
     bridgeConnectionLevel = SEngine.rng.nextInt() % opsHeight;
     
     return true;
   }
}