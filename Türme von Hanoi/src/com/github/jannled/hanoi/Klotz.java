package com.github.jannled.hanoi;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Klotz extends JPanel
{
	private static final long serialVersionUID = 4224548994801960173L;
	
	private int number;

	public Klotz(int number)
	{
		this.number = number;
	}
	
	@Override
	public void paint(Graphics g) 
	{
		super.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 30*number, 10*number);
	}
}
