package com.github.jannled.schule.labyrinth;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;

import javax.swing.JPanel;

public class Maze extends JPanel
{
	private static final long serialVersionUID = -2835529883015833541L;
	private int[][] maze;
	
	private Color[] colors = {Color.WHITE, Color.BLACK, Color.GREEN};
	private int boxWidth = 20;
	private int boxHeight = 20;
	
	public Maze(int width, int height)
	{
		maze = new int[width][height];
	}
	
	public Maze(int[][] maze)
	{
		this.maze = maze;
	}
	
	public Maze(File path)
	{
		String[] text = TextfileReader.readTextFile(path);
		maze = new int[text.length][text[0].toCharArray().length];
		
		for(int x=0; x<maze.length; x++)
		{
			char[] chars = text[x].toCharArray();
			for(int y=0; y<maze[0].length; y++)
			{
				maze[x][y] = Integer.parseInt("" + chars[y]) * -1;
			}
		}
	}
	
	public void drawCursor()
	{
		
	}
	
	public void drawMarker(int x, int y)
	{
		Graphics g = getGraphics();
		g.setColor(Color.BLUE);
		Rectangle r = calcBox(x, y);
		g.drawOval(r.x, r.y, r.width, r.height);
	}
	
	/**
	 * Calculate Box Width and Height
	 * @param x The x pos of the box in the array
	 * @param y The y pos of the box in the array
	 * @return Rectangle of the box
	 */
	public Rectangle calcBox(int x, int y)
	{
		int xpos = x*boxWidth;
		int ypos =  y*boxHeight;
		int width = x*boxWidth+boxWidth;
		int height = y*boxHeight+boxHeight;
		return new Rectangle(xpos, ypos, width, height);
	}
	
	/**
	 * Draw a Box on the specified position
	 * @param g
	 * @param x Xpos of the box in the array
	 * @param y Ypos of the box in the array
	 */
	public void draw(Graphics g, int x, int y, Color c)
	{
		if(maze == null)
			return;	

		g.setColor(c);
		
		Rectangle r = calcBox(x, y);
		g.fillRect(r.x, r.y, r.width, r.height);
	}
	
	@Override
	public void paint(Graphics g) 
	{
		super.paint(g);
		boxWidth = getWidth() / maze.length;
		boxHeight = getHeight() / maze[0].length;
		
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, getWidth(), getHeight());
		
		for(int x=0; x<maze.length; x++)
		{
			for(int y=0; y<maze[0].length; y++)
			{
				draw(g, x, y, colors[maze[y][x] * -1]);
			}
		}
	}
	
	public int[][] getMaze()
	{
		return maze;
	}
}
