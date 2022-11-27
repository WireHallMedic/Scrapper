package Scrapper.AI;

import Scrapper.Map.*;
import Scrapper.Actor.*;
import Scrapper.Engine.*;
import Scrapper.Ability.*;
import WidlerSuite.*;
import java.util.logging.*;

public class BasicAI implements AbilityConstants, MapConstants
{	
	private SActor self;
	private int turnEnergy;
	private Action pendingAction;
	private Direction pendingDirection;
	private Coord pendingTarget;


	public SActor getSelf(){return self;}
	public int getTurnEnergy(){return turnEnergy;}
	public Action getPendingAction(){return pendingAction;}
	public Direction getPendingDirection(){return pendingDirection;}
	public Coord getPendingTarget(){return new Coord(pendingTarget);}


	public void setSelf(SActor s){self = s;}
	public void setTurnEnergy(int t){turnEnergy = t;}
	public void setPendingAction(Action p){pendingAction = p;}
	public void setPendingDirection(Direction p){pendingDirection = p;}
	public void setPendingTarget(Coord p){setPendingTarget(p.x, p.y);}
	public void setPendingTarget(int x, int y){pendingTarget = new Coord(x, y);}


   public BasicAI(SActor s)
   {
      self = s;
      turnEnergy = AbilityConstants.NORMAL_SPEED;
      pendingAction = null;
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
         turnEnergy++;
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
            Logger.getGlobal().info("Passing turn");
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
         clearPlan();
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
      ToggleTile tog = (ToggleTile)SEngine.getCurZone().getTile(newX, newY);
      tog.toggle();
      reduceTurnEnergy(AbilityConstants.NORMAL_SPEED);
   }
}