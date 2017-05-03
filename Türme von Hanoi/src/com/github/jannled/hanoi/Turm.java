package com.github.jannled.hanoi;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

public class Turm extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private LinkedList<Klotz> turm = new LinkedList<Klotz>();
	
	public Turm()
	{
		
	}
	
	public void repaint()
	{
		
	}
	
	public void add(Klotz k)
	{
		turm.add(0, k);
	}
	
	public Klotz takeKlotz()
	{
		return turm.pop();
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 300, 500);
		
		g.setColor(Color.BLUE);
		
		int stack = 0;
		for(Klotz k : turm)
		{
			k.drawSelf(g, stack);
			stack++;
		}
	}
}
