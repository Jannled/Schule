package com.github.jannled.schule.labyrinth;

public abstract class Mazerunner 
{
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	int[] start;
	
	Maze maze;
	int orientation;
	int xpos;
	int ypos;
	
	int value = 0;
	
	public Mazerunner(Maze maze, int xpos, int ypos)
	{
		this.maze = maze;
		this.xpos = xpos;
		this.ypos = ypos;
		this.start = new int[] {xpos, ypos};
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
	
	/**
	 * Gets the surroundings of the current position
	 * @return The Array containing all surrounding blocks, order: NORTH, EAST, SOUTH, WEST
	 */
	public int[] area()
	{
		return area(xpos, ypos);
	}
	
	/**
	 * Gets the surroundings of the current position
	 * @param xpos The x coord to get the area
	 * @param ypos The y coord to get the area
	 * @return The Array containing all surrounding blocks, order: NORTH, EAST, SOUTH, WEST
	 */
	public int[] area(int xpos, int ypos)
	{
		int[] area = new int[4];
		try 
		{
			area[NORTH] = maze.get(xpos, ypos-1);
			area[EAST] = maze.get(xpos+1, ypos);
			area[SOUTH] = maze.get(xpos, ypos+1);
			area[WEST] = maze.get(xpos-1, ypos);
		} catch (ArrayIndexOutOfBoundsException e) 
		{
			System.err.println("Habe die Border berührt");
			return area;
		}
		return area;
	}
	
	/**
	 * Mark the shortest way out
	 * @param xpos The place to start backtracing
	 * @param ypos The place to start backtracing
	 */
	public void backtrace(int xpos, int ypos)
	{
		int xtrace = xpos;
		int ytrace = ypos;
		
		while(xtrace!=start[0] && ytrace!=start[2])
		{
			int[] area = area(xtrace, ytrace);
			int min = 0;
			int iorientation = 0;
			
			for(int i=0; i<4; i++)
			{
				//Checken ob das Feld eine Wand und sonst zum niedrigsten Feld gehen
				if(area[i] > 0 && area[i] < min)
				{
					min = area[i];
					iorientation = i;
				}
			}
			//Bewege zum kleinsten Feld
			if(iorientation == NORTH)
			{
				ytrace++;
			}
			else if(iorientation == EAST)
			{
				xtrace++;
			}
			else if(iorientation == SOUTH)
			{
				ytrace--;
			}
			else if(iorientation == WEST)
			{
				xtrace--;
			}
		}
		System.out.println("Schnellste Route gefunden");
	}
	
	/**
	 * Wait for 20 milliseconds
	 */
	public void pause()
	{
		pause(20);
	}
	
	/**
	 * Wait for the desired time
	 * @param millis The time in milliseconds to wait
	 */
	public void pause(long millis)
	{
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			System.err.println("Interrupted Exception!");
			e.printStackTrace();
		}
	}
}
