package Scrapper.Ability;

public interface AbilityConstants
{
   public static final int FULLY_CHARGED = 10;
   public static final int SLOW_SPEED = 4;
   public static final int NORMAL_SPEED = 2;
   public static final int FAST_SPEED = 1;
   
   public enum Action
   {
      CONTEXTUAL, PASS_TURN, STEP, USE_ENVIRONMENT, PICK_UP;
   }
}