package com.github.jannled.b42;

import java.awt.Dimension;

public class Zellhaufen 
{
	private Zelle[][] matrix;
	private Zelle[][] buffer;
	Dimension size;
	
	/**
	 * 
	 * @param width
	 * @param height 
	 * @param generatorThreshold A number from 0 to 1 for the random generator to determine if the cell is alive or not
	 */
	public Zellhaufen(int width, int height, float generatorThreshold)
	{
		this.matrix = new Zelle[height][width];
		for(int x=0; x<width; x++)
		{
			for(int y=0; y<height; y++)
			{
				boolean alive = Math.random()<generatorThreshold;
				matrix[y][x] = new Zelle(alive);
			}
		}
		size = new Dimension(matrix[0].length, matrix.length);
		this.buffer = matrix;
	}
	
	public Dimension getSize()
	{
		return size;
	}
	
	public Zelle getZelle(int x, int y)
	{
		int xpos = x%matrix[0].length;
		int ypos = y%matrix.length;
		
		if(xpos < 0)
		{
			xpos = matrix[0].length + xpos;
		}
		if(ypos < 0)
		{
			ypos = matrix.length + ypos;
		}
		return matrix[ypos][xpos];
	}
	
	public void setZelle(int x, int y, Zelle zelle)
	{
		int xpos = x%buffer[0].length;
		int ypos = y%buffer.length;
		
		if(xpos < 0)
		{
			xpos = buffer[0].length + xpos;
		}
		if(ypos < 0)
		{
			ypos = buffer.length + ypos;
		}
		
		matrix[ypos][xpos] = zelle;
	}
	
	public void swapBuffer()
	{
		matrix = buffer;
		buffer = new Zelle[(int) size.getHeight()][(int) size.getWidth()];
		System.arraycopy(matrix, 0, buffer, 0, matrix.length);
	}
	
	public void update()
	{	
		//Jede Zelle durchgehen
		for(int x=0; x<size.getWidth(); x++)
		{
			for(int y=0; y<size.getHeight(); y++)
			{
				int neighbours = getNeighbours(x, y);
				if(getZelle(x, y).isAlive())
				{
					if(neighbours<2 || neighbours>3)
					{
						getZelle(x, y).setAlive(false);
					}
				}
				else
				{
					if(neighbours == 3)
					{
						getZelle(x, y).setAlive(true);
					}
				}
			}
		}
		
		swapBuffer();
	}
	
	public int getNeighbours(int x, int y)
	{
		int neighbours = 0;
		if(getZelle(x-1, y-1).isAlive()) //Links Oben
		{
			neighbours++;
		}
		if(getZelle(x, y-1).isAlive()) //Mitte Oben
		{
			neighbours++;
		}
		if(getZelle(x+1, y-1).isAlive()) //Rechts Oben
		{
			neighbours++;
		}
		if(getZelle(x+1, y).isAlive()) //Rechts Mitte
		{
			neighbours++;
		}
		if(getZelle(x+1, y+1).isAlive()) //Rechts Unten
		{
			neighbours++;
		}
		if(getZelle(x, y+1).isAlive()) //Mitte Unten
		{
			neighbours++;
		}
		if(getZelle(x-1, y+1).isAlive()) //Links Unten
		{
			neighbours++;
		}
		if(getZelle(x-1, y).isAlive()) //Links Mitte
		{
			neighbours++;
		}
		return neighbours;
	}
}
