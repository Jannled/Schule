package com.github.jannled.hanoi;

import java.awt.Graphics;

public class Klotz
{	
	private int number;

	/**
	 * 
	 * @param number Die kleinste Zahl ist der größte Klotz
	 */
	public Klotz(int number)
	{
		this.number = number;
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public void drawSelf(Graphics g, int stack)
	{
		int x = 0;
		int y = 20*stack;
		int width = 100/getNumber();
		int height = 20;
		g.fillRect(x, y, width, height);
	}
}
