package Scrapper.GUI;

import java.awt.*;

public interface STileObject
{
   public abstract String getName();
	public abstract int getIconIndex();
	public abstract Color getFGColor();
	public abstract Color getBGColor();


   public abstract void setName(String n);
	public abstract void setIconIndex(int ii);
	public abstract void setFGColor(Color f);
	public abstract void setBGColor(Color b);
}