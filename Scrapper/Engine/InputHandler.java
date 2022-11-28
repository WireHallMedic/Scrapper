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
      if(SEngine.getCurPanel() instanceof MainGamePanel)
         handleMainGanePanelKeyInput(ke);
      if(SEngine.getCurPanel() instanceof TerminalPanel)
         handleTerminalPanelKeyInput(ke);
   }
   
   private static void handleMainGanePanelKeyInput(KeyEvent ke)
   {
      SActor player = SEngine.getPlayer();
      BasicAI ai = player.getAI();
      int newX = player.getX();
      int newY = player.getY();
      Direction dir = null;
      // non-returning input
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
      
      // cancel player choice
      if(ke.getKeyCode() == KeyEvent.VK_ESCAPE)
      {
         cancelPlayerAction(true);
         return;
      }
      
      // set use environment
      if(ke.getKeyCode() == KeyEvent.VK_U)
      {
         setUseEnvironment();
         return;
      }
      
      // use environment follow-up
      if(ai.getPendingAction() == Action.USE_ENVIRONMENT && dir != null)
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
      if((ai.getPendingAction() == null || ai.getPendingAction() == Action.CONTEXTUAL) && 
         dir != null)
      {
         // step
         if(SEngine.getCurZone().canStep(player, newX, newY))
         {
            ai.setPendingDirection(dir);
            ai.setPendingAction(Action.STEP);
         }
         // use environment
         else if(SEngine.getCurZone().getTile(newX, newY) instanceof ToggleTile ||
                 SEngine.getCurZone().getTile(newX, newY) instanceof TerminalTile)
         {
            ai.setPendingDirection(dir);
            ai.setPendingAction(Action.USE_ENVIRONMENT);
         }
         return;
      }
   }
   
   private static void handleTerminalPanelKeyInput(KeyEvent ke)
   {
      // cancel player choice
      if(ke.getKeyCode() == KeyEvent.VK_ESCAPE ||
         ke.getKeyCode() == KeyEvent.VK_ENTER ||
         ke.getKeyCode() == KeyEvent.VK_SPACE)
      {
         SEngine.setMainGamePanelVisible();
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
   
   private static void goToMainGameScreen()
   {
      SEngine.setMainGamePanelVisible();
   }
   
   private static void setUseEnvironment()
   {
      BasicAI ai = SEngine.getPlayer().getAI();
      ai.setPendingAction(Action.USE_ENVIRONMENT);
      InfoPanel.addMessage("Choose a direction.");
   }
   

}