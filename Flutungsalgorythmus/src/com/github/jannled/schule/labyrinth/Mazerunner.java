package com.github.jannled.schule.labyrinth;

public abstract class Mazerunner 
{
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	Maze maze;
	int xpos;
	int ypos;
	
	public Mazerunner(Maze maze, int xpos, int ypos)
	{
		this.maze = maze;
		this.xpos = xpos;
		this.ypos = ypos;
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
		maze.drawMarker(xpos, ypos);
	}
	
	public int[] getPos()
	{
		return new int[] {xpos, ypos};
	}
}
