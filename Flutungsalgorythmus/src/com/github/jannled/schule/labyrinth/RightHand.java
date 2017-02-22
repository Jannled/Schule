package com.github.jannled.schule.labyrinth;

public class RightHand extends Mazerunner
{	
	public RightHand(Maze maze, int xpos, int ypos) 
	{
		super(maze, xpos, ypos);
	}
	
	@Override
	public void redraw()
	{
		value++;
		if(maze.get(xpos, ypos) == -2)
		{
			return;
		}
		maze.set(xpos, ypos, value);
		maze.repaint();
	}

	private boolean exit;
	
	@Override
	public boolean start() 
	{
		orientation = NORTH;
		
		while(!exit)
		{
			int area[] = area();
			
			//Check if it has reached the exit
			if(maze.get(xpos, ypos) == -2)
			{
				backtrace(xpos, ypos);
				return true;
			}
			
			//Berechne die Blickrichtung für das rechte Feld
			int rechts = orientation+1;
			if(rechts > 3)
				rechts = 0;
			
			while(area[rechts] == -1)
			{
				rechts--;
				if(rechts<0)
					rechts=3;
			}
			orientation = rechts;
			gehe();
			redraw();
			pause();
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