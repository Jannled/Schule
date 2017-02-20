package com.github.jannled.schule.labyrinth;

public class RightHand extends Mazerunner
{
	int value = 0;
	
	public RightHand(Maze maze, int xpos, int ypos) 
	{
		super(maze, xpos, ypos);
	}
	
	@Override
	public void redraw()
	{
		value++;
		maze.set(xpos, ypos, value);
		maze.repaint();
	}

	private boolean exit;
	
	public boolean start() 
	{
		orientation = NORTH;
		
		while(!exit)
		{
			int area[] = new int[4];
			
			//Check if it has reached the exit
			if(maze.get(xpos, ypos) == -2)
			{
				return true;
			}
			
			try 
			{
				area[NORTH] = maze.get(xpos, ypos-1);
				area[EAST] = maze.get(xpos+1, ypos);
				area[SOUTH] = maze.get(xpos, ypos+1);
				area[WEST] = maze.get(xpos-1, ypos);
			} catch (ArrayIndexOutOfBoundsException e) 
			{
				System.err.println("Habe die Border berührt");
				continue;
			}
			
			//Berechne die Blickrichtung für das rechte Feld
			int rechts = orientation+1;
			if(rechts > 3)
				rechts = 0;
			
			while(area[rechts] == -1)
			{
				rechts++;
			}
			gehe();
			redraw();
		}
		return false;
	}
	
	public void gehe()
	{
		if(orientation == NORTH)
		{
			ypos = ypos-1;
		}
		else if(orientation == EAST)
		{
			xpos = xpos+1;
		}
		else if(orientation == SOUTH)
		{
			ypos = ypos+1;
		}
		else if(orientation == WEST)
		{
			xpos = xpos-1;
		}
	}
}