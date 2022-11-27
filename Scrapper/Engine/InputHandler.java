package Scrapper.Engine;

import java.awt.event.*;
import Scrapper.Map.*;
import Scrapper.AI.*;
import Scrapper.GUI.*;
import Scrapper.Actor.*;
import Scrapper.Ability.*;

public class InputHandler implements AbilityConstants, MapConstants
{
   public static void handleKeyInput(KeyEvent ke)
   {
      SActor player = SEngine.getPlayer();
      BasicAI ai = player.getAI();
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
         case KeyEvent.VK_ESCAPE  :  cancelPlayerAction(true); break;
         case KeyEvent.VK_U       :  ai.setPendingAction(Action.USE_ENVIRONMENT); break;
      }
      // use environment
      if(ai.getPendingAction() == Action.USE_ENVIRONMENT)
      {
         if(SEngine.getCurZone().getTile(newX, newY) instanceof ToggleTile)
         {
            ai.setPendingDirection(dir);
         }
         else
         {
            InfoPanel.addMessage("Nothing to use at that location.");
            cancelPlayerAction(false);
         }
         return;
      }
      
      // contextual
      if(ai.getPendingAction() == null || ai.getPendingAction() == Action.CONTEXTUAL)
      {
         // step
         if(SEngine.getCurZone().canStep(player, newX, newY))
         {
            ai.setPendingDirection(dir);
            ai.setPendingAction(Action.STEP);
         }
         // use environment
         else if(SEngine.getCurZone().getTile(newX, newY) instanceof ToggleTile)
         {
            ai.setPendingDirection(dir);
            ai.setPendingAction(Action.USE_ENVIRONMENT);
         }
         return;
      }
   }
   
   private static void cancelPlayerAction(boolean showMessage)
   {
      BasicAI ai = SEngine.getPlayer().getAI();
      if(ai.getPendingAction() != null || 
         ai.getPendingDirection() != null || 
         ai.getPendingTarget() != null)
      {
         ai.clearPlan();
         if(showMessage)
            InfoPanel.addMessage("Player action cancelled.");
      }
   }
}