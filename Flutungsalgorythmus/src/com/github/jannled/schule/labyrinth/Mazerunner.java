package com.github.jannled.schule.labyrinth;

public abstract class Mazerunner 
{
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	Maze maze;
	int orientation;
	int xpos;
	int ypos;
	
	public Mazerunner(Maze maze, int xpos, int ypos)
	{
		this.maze = maze;
		this.xpos = xpos;
		this.ypos = ypos;
		this.orientation = NORTH;
	}
	
	/**
	 * Gets started by the super class
	 * @param maze
	 * @param x
	 * @param y
	 * @return
	 */
	public abstract boolean start();
	
	public void redraw()
	{
		maze.repaint();
	}
	
	public int[] getPos()
	{
		return new int[] {xpos, ypos};
	}
	
	public void dreheLinks()
	{
		orientation--;
		while(orientation<0)
		{
			orientation = orientation + 4;
		}
	}
	
	public void dreheRechts()
	{
		orientation++;
		while(orientation>3)
		{
			orientation = orientation - 4;
		}
	}
	
	public void gehe()
	{
		if(orientation == NORTH)
		{
			ypos = ypos+1;
		}
		else if(orientation == EAST)
		{
			xpos = xpos+1;
		}
		else if(orientation == SOUTH)
		{
			ypos = ypos-1;
		}
		else if(orientation == WEST)
		{
			xpos = xpos-1;
		}
	}
}
