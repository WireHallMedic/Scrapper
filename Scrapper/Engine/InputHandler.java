package Scrapper.Engine;

import java.awt.event.*;
import Scrapper.Map.*;
import Scrapper.AI.*;
import Scrapper.Actor.*;
import Scrapper.Ability.*;

public class InputHandler implements AbilityConstants, MapConstants
{
   public static void handleKeyInput(KeyEvent ke)
   {
      SActor player = SEngine.getPlayer();
      int newX = player.getX();
      int newY = player.getY();
      Direction dir = null;
      switch(ke.getKeyCode())
      {
         case KeyEvent.VK_NUMPAD2 :
         case KeyEvent.VK_DOWN    :  newY++; dir = Direction.SOUTH; break;
         case KeyEvent.VK_NUMPAD8 :
         case KeyEvent.VK_UP      :  newY--; dir = Direction.NORTH; break;
         case KeyEvent.VK_NUMPAD6 :
         case KeyEvent.VK_RIGHT   :  newX++; dir = Direction.EAST; break;
         case KeyEvent.VK_NUMPAD4 :
         case KeyEvent.VK_LEFT    :  newX--; dir = Direction.WEST; break;
         case KeyEvent.VK_NUMPAD7 :  newY--; newX--; dir = Direction.NORTHWEST; break;
         case KeyEvent.VK_NUMPAD9 :  newY--; newX++; dir = Direction.NORTHEAST; break;
         case KeyEvent.VK_NUMPAD1 :  newY++; newX--; dir = Direction.SOUTHWEST; break;
         case KeyEvent.VK_NUMPAD3 :  newY++; newX++; dir = Direction.SOUTHEAST; break;
      }
      if(SEngine.getCurZone().canStep(player, newX, newY))
      {
         BasicAI ai = player.getAI();
         ai.setPendingDirection(dir);
         ai.setPendingAction(Action.STEP);
      }
      else if(SEngine.getCurZone().getTile(newX, newY) instanceof ToggleTile)
      {
         BasicAI ai = player.getAI();
         ai.setPendingDirection(dir);
         ai.setPendingAction(Action.USE_ENVIRONMENT);
      }
   }
}