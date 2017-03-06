package com.github.jannled.b42;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Canvas extends JPanel 
{
	private static final long serialVersionUID = 1804718425395732731L;
	
	private Zellhaufen zellhaufen;
	private int cellWidth;
	private int cellHeight;
	
	public Canvas(Zellhaufen zellhaufen)
	{
		this.zellhaufen = zellhaufen;
	}
	
	@Override
	public void paint(Graphics g) 
	{
		super.paint(g);
		Dimension cellsSize = zellhaufen.getSize();
		cellWidth = (int) (getWidth() / cellsSize.getWidth());
		cellHeight = (int) (getHeight() / cellsSize.getHeight());
		
		for(int x=0; x<cellsSize.getWidth(); x++)
		{
			for(int y=0; y<cellsSize.getHeight(); y++)
			{
				if(zellhaufen.getZelle(x, y).isAlive())
				{
					g.setColor(Color.BLACK);
				}
				else
				{
					g.setColor(Color.RED);
				}
				g.fillRect(x*cellWidth, y*cellHeight, cellWidth, cellHeight);
			}
		}
	}
}
