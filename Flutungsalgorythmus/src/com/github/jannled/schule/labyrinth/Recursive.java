package com.github.jannled.schule.labyrinth;

public class Recursive extends Mazerunner
{
	public Recursive(Maze maze, int xpos, int ypos) 
	{
		super(maze, xpos, ypos);
	}

	@Override
	public boolean start() 
	{
		fillArea();
		return false;
	}

	public void fillArea()
	{
		int[] area = area();
		maze.set(xpos, ypos, value);
		
		value++;
		maze.repaint();
		for(int i=0; i<4; i++)
		{
			if(area[i] == -2)
			{
				System.out.println("Ziel gefunden!");
				return;
			}
			else if(area[i] >= 0)
			{
				orientation = i;
				gehe();
				fillArea();
			}
		}
	}
}
