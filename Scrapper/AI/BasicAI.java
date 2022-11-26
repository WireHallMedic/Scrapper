package Scrapper.AI;

import Scrapper.Actor.*;
import Scrapper.Ability.*;

public class BasicAI
{	
   private SActor self;
   private int turnEnergy;


	public SActor getSelf(){return self;}
   public int getTurnEnergy(){return turnEnergy;}


	public void setSelf(SActor s){self = s;}
   public void setTurnEnergy(int te){turnEnergy = te;}


   public BasicAI(SActor s)
   {
      self = s;
      turnEnergy = AbilityConstants.NORMAL_SPEED;
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
   
   
   
   public void act()
   {
      passTurn();
   }
   
   public void passTurn()
   {
      reduceTurnEnergy(AbilityConstants.NORMAL_SPEED);
   }
}