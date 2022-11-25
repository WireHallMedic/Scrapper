package Scrapper;

import Scrapper.GUI.*;
import Scrapper.Engine.*;
import Scrapper.Actor.*;

public class ScrapperMain
{
   public static final boolean TEST_VERSION = true;
   
   public static void main(String[] args)
   {
      SFrame frame = new SFrame();
      if(TEST_VERSION)
      {
         SEngine.setPlayer(SActor.getMock());
      }
   }

}