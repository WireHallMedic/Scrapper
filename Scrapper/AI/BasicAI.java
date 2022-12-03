package Scrapper.AI;

import Scrapper.GUI.*;
import Scrapper.Map.*;
import Scrapper.Item.*;
import Scrapper.Actor.*;
import Scrapper.Engine.*;
import Scrapper.Ability.*;
import WidlerSuite.*;
import java.util.logging.*;

public class BasicAI implements AbilityConstants, MapConstants, ItemConstants
{	
	private SActor self;
	private int turnEnergy;
	private Action pendingAction;
	private Direction pendingDirection;
	private Coord pendingTarget;
   private Action lastAction;
   private boolean startOfTurnTaken;


	public SActor getSelf(){return self;}
	public int getTurnEnergy(){return turnEnergy;}
	public Action getPendingAction(){return pendingAction;}
   public Action getLastAction(){return lastAction;}
	public Direction getPendingDirection(){return pendingDirection;}


	public void setSelf(SActor s){self = s;}
	public void setTurnEnergy(int t){turnEnergy = t;}
	public void setPendingAction(Action p){pendingAction = p;}
	public void setPendingDirection(Direction p){pendingDirection = p;}
	public void setPendingTarget(Coord p){setPendingTarget(p.x, p.y);}
   protected void setLastAction(Action a){lastAction = a;}
	public void setPendingTarget(int x, int y){pendingTarget = new Coord(x, y);}


   public BasicAI(SActor s)
   {
      self = s;
      turnEnergy = AbilityConstants.NORMAL_SPEED;
      pendingAction = null;
      pendingDirection = null;
      pendingTarget = null;
      lastAction = null;
      startOfTurnTaken = false;
   }
   
   protected void registerAction()
   {
      lastAction = pendingAction;
   }
   
   
	public Coord getPendingTarget()
   {
      if(pendingTarget == null)
         return null;
      return new Coord(pendingTarget);
   }
   
   // turn energy methods
   public void reduceTurnEnergy(int adj)
   {
      turnEnergy -= adj;
   }
   
   public void increaseTurnEnergy(int adj)
   {
      turnEnergy += adj;
   }
   
   public void chargeTurnEnergy()
   {
      if(!isCharged())
      {
         turnEnergy++;
      }
   }
   
   public boolean isCharged()
   {
      return turnEnergy >= AbilityConstants.FULLY_CHARGED;
   }
   
   
   // planning
   
   // override in child classes
   public void plan()
   {
      pendingAction = Action.PASS_TURN;
   }
   
   public void clearPlan()
   {
      pendingAction = null;
      pendingTarget = null;
      pendingDirection = null;
   }
   
   public boolean hasPlan()
   {
      return(pendingAction == Action.PASS_TURN ||
             pendingAction != null && (pendingTarget != null || pendingDirection != null));
   }
   
   
   public void act()
   {
      boolean tookAction = true;
      switch(pendingAction)
      {
         case PASS_TURN:
            passTurn();
            break;
         case STEP:
            takeStep();
            break;
         case USE_ENVIRONMENT:
            useEnvironment();
            break;
         default: 
            tookAction = false;
      }
      if(tookAction)
      {
         registerAction();
         clearPlan();
         startOfTurnTaken = false;
      }
   }
   
   public void passTurn()
   {
      reduceTurnEnergy(AbilityConstants.NORMAL_SPEED);
   }
   
   public void takeStep()
   {
      int newX = self.getX();
      int newY = self.getY();
      newX += pendingDirection.x;
      newY += pendingDirection.y;
      self.setX(newX);
      self.setY(newY);
      reduceTurnEnergy(AbilityConstants.NORMAL_SPEED);
   }
   
   public void useEnvironment()
   {
      int newX = self.getX();
      int newY = self.getY();
      newX += pendingDirection.x;
      newY += pendingDirection.y;
      if(SEngine.getCurZone().getTile(newX, newY) instanceof DoorTile)
      {
         DoorTile door = (DoorTile)SEngine.getCurZone().getTile(newX, newY);
         // note that trying a locked door still conusmes a turn, successful or not
         if(door.isLocked())
         {
            // player tries to unlock door
            if(self.isPlayer())
            {
               // quest item door
               if(door.requiresQuestItem())
               {
                  QuestItem key = door.getLockedBy();
                  // has key, open door
                  if(self.getInventory().hasQuestItem(key))
                  {
                     door.setLocked(false);
                     door.toggle();
                     InfoPanel.addMessage("Unlocked door with the " + door.getLockedBy().name + ".");
                  }
                  // doesn't have key
                  else
                     InfoPanel.addMessage(door.getLockedBy().name + " required.");
               }
               // no key, just locked
               else
               {
                  InfoPanel.addMessage("The door is locked.");
               }
               
            }
         }
         // unlocked
         else
         {
            door.toggle();
         }
      }
      else if(SEngine.getCurZone().getTile(newX, newY) instanceof ToggleTile)
      {
         ToggleTile tog = (ToggleTile)SEngine.getCurZone().getTile(newX, newY);
         tog.toggle();
      }
      else if(SEngine.getCurZone().getTile(newX, newY) instanceof TerminalTile)
      {
         TerminalTile terminalTile = (TerminalTile)SEngine.getCurZone().getTile(newX, newY);
         SEngine.setTerminalPanelVisible(terminalTile);
      }
      reduceTurnEnergy(AbilityConstants.NORMAL_SPEED);
   }
}