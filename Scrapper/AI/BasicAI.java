package Scrapper.AI;

import Scrapper.Actor.*;

public class BasicAI
{	
   private SActor self;


	public SActor getSelf(){return self;}


	public void setSelf(SActor s){self = s;}


   public BasicAI(SActor s)
   {
      self = s;
   }
   
   
}