package com.github.jannled.hanoi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JPanel;

public class Turm extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private LinkedList<Klotz> turm = new LinkedList<Klotz>();
	
	public Turm()
	{
		setLayout(new GridLayout(0, 1));
	}
	
	public void repaint()
	{
		System.out.println("repainting");
		try {
			for(Klotz k : turm)
			{
				k.repaint();
			}
		} catch(NullPointerException e)
		{
			System.err.println("Stack is empty");
		}
		
	}
	
	public void add(Klotz k)
	{
		turm.add(k);
	}
	
	@Override
	public void paint(Graphics g) 
	{
		g.setColor(Color.CYAN);
		g.drawRect(0, 0, 20, 20);
		super.paint(g);
	}
}
