package Scrapper.Engine;

import Scrapper.Map.*;
import Scrapper.Actor.*;
import java.util.*;

public class ZonePackage
{
	private Zone zone;
	private Vector<SActor> actorList;


	public Zone getZone(){return zone;}
	public Vector<SActor> getActorList(){return actorList;}


	public void setZone(Zone z){zone = z;}
	public void setActorList(Vector<SActor> a){actorList = a;}


   public ZonePackage(Zone z, Vector<SActor> al)
   {
      zone = z;
      actorList = al;
   }
   
   public ZonePackage(Zone z)
   {
      this(z, new Vector<SActor>());
   }
}