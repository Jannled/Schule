package com.github.jannled.b42;

public class Zelle 
{
	private boolean alive;
	
	public Zelle(boolean alive)
	{
		this.alive = alive;
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	
	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}
}
